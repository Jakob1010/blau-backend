package com.blau.integration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class LogEndpointIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCreateLog() throws Exception {
        // Create a LogDto to send in the request body
        String logDtoJson = "{ \"timestamp\": \"2023-08-15T12:34:56\" }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/logs")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(logDtoJson))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testGetLogById() throws Exception {
        // Replace {id} with a valid Log ID
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/logs/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAllLogs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/logs"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

