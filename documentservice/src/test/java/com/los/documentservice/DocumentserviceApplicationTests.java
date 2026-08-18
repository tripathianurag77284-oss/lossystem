package com.los.documentservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.datasource.url=jdbc:h2:mem:documentservice-test")
class DocumentserviceApplicationTests {

    @Test
    void contextLoads() {
    }
}
