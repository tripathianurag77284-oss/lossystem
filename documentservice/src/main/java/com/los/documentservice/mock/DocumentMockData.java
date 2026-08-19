package com.los.documentservice.mock;

import com.los.documentservice.model.Document;

import java.time.LocalDateTime;
import java.util.List;

public final class DocumentMockData {

    private DocumentMockData() {
    }

    public static List<Document> getDocuments() {
        LocalDateTime now = LocalDateTime.now();

        return List.of(
                new Document(
                        null, 5001L, 1L, "KYC",
                        "https://example.com/uploads/pan.pdf",
                        false, null, "PENDING", "MANUAL",
                        false, null, "PENDING", "MANUAL",
                        true, now, now, null, null,
                        1001L, 1001L, null, null, false,"MANUAL"
                ),
                new Document(
                        null, 5002L, 2L, "KYC",
                        "https://example.com/uploads/aadhaar.pdf",
                        true, "Verified successfully", "VERIFIED", "AUTO",
                        true, "Validated successfully", "VALIDATED", "AUTO",
                        true, now, now, now, now,
                        1001L, 1001L, 2001L, 2002L, false,"MANUAL"
                )
        );
    }
}
