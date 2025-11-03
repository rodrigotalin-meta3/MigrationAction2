filetype:java
package br.com.meta3.java.scaffold.application.services;

import org.springframework.stereotype.Service;
import br.com.meta3.java.scaffold.domain.services.MessageService;

/**
 * Implementation of MessageService that returns legacy messages.
 */
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * Returns a legacy message identified by the provided key.
     *
     * @param key the identifier of the legacy message
     * @return the legacy message text
     */
    @Override
    public String getLegacyMessage(String key) {
        // TODO: (REVIEW) Currently returns a hardcoded legacy string; replace with real lookup when available.
        return "Nó repetido";
    }
}