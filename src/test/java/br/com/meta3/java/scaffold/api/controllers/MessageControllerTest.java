filetype:java
package br.com.meta3.java.scaffold.api.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import br.com.meta3.java.scaffold.domain.services.MessageService;

import static org.mockito.BDDMockito.given;

/**
 * Test for MessageController to verify that the /api/message endpoint
 * returns the legacy message correctly.
 */
@WebMvcTest(MessageController.class)
public class MessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Test
    public void shouldReturnLegacyMessage() throws Exception {
        String key = "anyKey";
        String legacyMessage = "Nó repetido";

        // Arrange: mock MessageService to return the legacy message
        given(messageService.getLegacyMessage(key)).willReturn(legacyMessage);

        // Act & Assert: perform GET and validate response
        mockMvc.perform(get("/api/message")
                .param("key", key)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            // TODO: (REVIEW) Ensure JSON property name 'texto' matches MessageDTO field name.
            .andExpect(jsonPath("$.texto").value(legacyMessage));
    }
}