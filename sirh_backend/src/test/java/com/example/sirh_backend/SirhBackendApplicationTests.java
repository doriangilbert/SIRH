package com.example.sirh_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class SirhBackendApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> SirhBackendApplication.main(new String[] {}));
    }

}
