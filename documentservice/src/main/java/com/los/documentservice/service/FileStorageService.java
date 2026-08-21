package com.los.documentservice.service;

import com.los.documentservice.exception.UnsupportedDocumentMediaTypeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import java.util.Locale;
import java.util.Set;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("application/pdf", "image/png");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(".pdf", ".png");

    private final Path storageDirectory;
    private final Path workingDirectory;

    public FileStorageService(@Value("${document.storage.directory:docs}") String storageDirectory) {
        this.workingDirectory = Path.of("").toAbsolutePath().normalize();
        Path configuredPath = Path.of(storageDirectory);
        this.storageDirectory = (configuredPath.isAbsolute()
                ? configuredPath
                : workingDirectory.resolve(configuredPath)).normalize();
        try {
            Files.createDirectories(this.storageDirectory);
        } catch (IOException exception) {
            throw new IllegalStateException("Could not create document storage directory", exception);
        }
    }

    public String store(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("A non-empty document file is required");
        }

        String originalName = StringUtils.cleanPath(
                file.getOriginalFilename() == null ? "document" : file.getOriginalFilename());
        String safeName = Path.of(originalName).getFileName().toString()
                .replaceAll("[^a-zA-Z0-9._-]", "_");
        if (safeName.isBlank() || safeName.equals(".") || safeName.equals("..")) {
            safeName = "document";
        }

        validateMediaType(file, safeName);

        Path target = storageDirectory.resolve(UUID.randomUUID() + "-" + safeName).normalize();
        if (!target.startsWith(storageDirectory)) {
            throw new IllegalArgumentException("Invalid document filename");
        }

        try {
            Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
            return workingDirectory.getRoot().equals(target.getRoot())
                    ? workingDirectory.relativize(target).toString()
                    : target.toString();
        } catch (IOException | IllegalArgumentException exception) {
            throw new IllegalStateException("Could not store document file", exception);
        }
    }

    private void validateMediaType(MultipartFile file, String filename) {
        String contentType = file.getContentType();
        String lowerFilename = filename.toLowerCase(Locale.ROOT);
        boolean allowedExtension = ALLOWED_EXTENSIONS.stream().anyMatch(lowerFilename::endsWith);
        boolean allowedContentType = contentType == null
                || contentType.isBlank()
                || "application/octet-stream".equalsIgnoreCase(contentType)
                || ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase(Locale.ROOT));

        if (!allowedExtension || !allowedContentType) {
            throw new UnsupportedDocumentMediaTypeException("Only PDF and PNG documents are supported");
        }
    }

    public void delete(String storedPath) {
        if (storedPath == null || storedPath.isBlank()) {
            return;
        }

        try {
            Path path = Path.of(storedPath);
            Path target = (path.isAbsolute() ? path : workingDirectory.resolve(path)).normalize();
            if (target.startsWith(storageDirectory)) {
                Files.deleteIfExists(target);
            }
        } catch (IOException exception) {
            throw new IllegalStateException("Could not delete document file", exception);
        }
    }
}
