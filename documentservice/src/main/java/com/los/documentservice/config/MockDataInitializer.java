package com.los.documentservice.config;

import com.los.documentservice.mock.DocumentMockData;
import com.los.documentservice.mock.DocumentTypeMasterMockData;
import com.los.documentservice.repository.DocumentRepository;
import com.los.documentservice.repository.DocumentTypeMasterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDataInitializer {

    @Bean
    CommandLineRunner seedDocumentMockData(
            DocumentRepository documentRepository,
            DocumentTypeMasterRepository documentTypeRepository) {
        return args -> {
            if (documentTypeRepository.count() == 0) {
                documentTypeRepository.saveAll(DocumentTypeMasterMockData.getDocumentTypes());
            }

            if (documentRepository.count() == 0) {
                documentRepository.saveAll(DocumentMockData.getDocuments());
            }
        };
    }
}
