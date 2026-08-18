package com.los.applicationservice.mock;

import com.los.applicationservice.model.TaskMaster;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskMockData {

    private static final List<TaskMaster> tasks =
            new ArrayList<>(List.of(

                    new TaskMaster(
                            1L,
                            "KYC_VERIFICATION",
                            "Verify customer KYC documents",
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

                    new TaskMaster(
                            2L,
                            "INCOME_VERIFICATION",
                            "Verify customer income documents",
                            BigDecimal.valueOf(50),
                            LocalDateTime.now().plusDays(4),
                            LocalDateTime.now().plusDays(3),
                            true,
                            LocalDateTime.now(),
                            LocalDateTime.now(),
                            101L,
                            101L,
                            false
                    ),

                    new TaskMaster(
                            3L,
                            "CREDIT_CHECK",
                            "Perform credit bureau verification",
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

                    new TaskMaster(
                            4L,
                            "FINAL_APPROVAL",
                            "Final loan application approval",
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

    private TaskMockData() {
    }

    public static List<TaskMaster> getTasks() {
        return tasks;
    }
}

