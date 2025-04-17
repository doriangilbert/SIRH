package com.example.sirh_backend.e2e;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class EvaluationReportPdfE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
        mockMvc.perform(post("/evaluations")
                        .contentType("application/json")
                        .content("{\"year\": 2025, \"description\": \"Annual Review\", \"employee\": {\"firstName\": \"John\", \"lastName\": \"Doe\", \"leaveBalance\": 10}}"))
                .andExpect(status().isCreated());
    }

    @Test
    void getEvaluationReportReturnsSuccess() throws Exception {
        mockMvc.perform(get("/evaluations/1/report")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"));
    }
}
