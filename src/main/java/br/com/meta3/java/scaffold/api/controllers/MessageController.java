filetype:java
package br.com.meta3.java.scaffold.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.meta3.java.scaffold.api.dtos.MessageDTO;
import br.com.meta3.java.scaffold.domain.services.MessageService;
import jakarta.validation.constraints.NotBlank;

/**
 * REST controller for retrieving legacy messages.
 */
@Validated  // Enable method-level validation
@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * GET  /message?key={key} : Retrieve a legacy message identified by the provided key.
     *
     * @param key the identifier of the legacy message; must not be blank
     * @return MessageDTO containing the legacy message text
     */
    @GetMapping("/message")
    public MessageDTO getMessage(
            @RequestParam("key")
            @NotBlank(message = "Key parameter must not be blank") String key) {
        // TODO: (REVIEW) Consider retrieving 'key' from authentication context if appropriate
        String text = messageService.getLegacyMessage(key);
        return new MessageDTO(text);
    }
}