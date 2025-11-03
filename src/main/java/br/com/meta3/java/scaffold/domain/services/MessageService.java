filetype:java
package br.com.meta3.java.scaffold.domain.services;

/**
 * Service interface for retrieving legacy messages from the legacy system.
 */
public interface MessageService {

    /**
     * Retrieves a legacy message by its key or identifier.
     *
     * @param key the identifier of the legacy message
     * @return the legacy message text
     */
    String getLegacyMessage(String key);

    // TODO: (REVIEW) Add methods for batch retrieval or parameterized messages if needed.
}