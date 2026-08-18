package com.los.documentservice.mock;

import com.los.documentservice.model.DocumentTypeMaster;

import java.time.LocalDateTime;
import java.util.List;

public final class DocumentTypeMasterMockData {

    private DocumentTypeMasterMockData() {
    }

    public static List<DocumentTypeMaster> getDocumentTypes() {
        LocalDateTime now = LocalDateTime.now();

        return List.of(
                new DocumentTypeMaster(
                        null, "PAN Card", "KYC",
                        true, false, false, true,
                        now, now, 1001L, 1001L, false
                ),
                new DocumentTypeMaster(
                        null, "Aadhaar Card", "KYC",
                        true, true, true, true,
                        now, now, 1001L, 1001L, false
                ),
                new DocumentTypeMaster(
                        null, "Driving License", "ADDRESS",
                        true, true, true, true,
                        now, now, 1001L, 1001L, false
                )
        );
    }
}
