package com.los.applicationservice.mock;

import com.los.applicationservice.model.TaskStageMaster;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskStageMockData {

    private static final List<TaskStageMaster> stages =
            new ArrayList<>(List.of(

                    new TaskStageMaster(
                            101L,
                            "DOCUMENT_COLLECTION",
                            BigDecimal.valueOf(10),
                            LocalDateTime.now().plusDays(1),
                            LocalDateTime.now().plusHours(12),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            101L,
                            101L,
                            false
                    ),

                    new TaskStageMaster(
                            102L,
                            "KYC_VERIFICATION",
                            BigDecimal.valueOf(25),
                            LocalDateTime.now().plusDays(2),
                            LocalDateTime.now().plusDays(1),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            101L,
                            101L,
                            false
                    ),

                    new TaskStageMaster(
                            103L,
                            "INCOME_VERIFICATION",
                            BigDecimal.valueOf(50),
                            LocalDateTime.now().plusDays(4),
                            LocalDateTime.now().plusDays(3),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            102L,
                            102L,
                            false
                    ),

                    new TaskStageMaster(
                            104L,
                            "CREDIT_ASSESSMENT",
                            BigDecimal.valueOf(75),
                            LocalDateTime.now().plusDays(5),
                            LocalDateTime.now().plusDays(4),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            102L,
                            102L,
                            false
                    ),

                    new TaskStageMaster(
                            105L,
                            "FINAL_APPROVAL",
                            BigDecimal.valueOf(100),
                            LocalDateTime.now().plusDays(7),
                            LocalDateTime.now().plusDays(6),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            103L,
                            103L,
                            false
                    )
            ));

    private TaskStageMockData() {
    }

    public static List<TaskStageMaster> getStages() {
        return stages;
    }
}

