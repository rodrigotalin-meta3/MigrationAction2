filetype:java
package br.com.meta3.java.scaffold.api.dtos;

import jakarta.validation.constraints.NotBlank;

/**
 * DTO for wrapping a legacy message text in API responses.
 */
public class MessageDTO {

    @NotBlank
    private String texto;

    // Default constructor for serialization/deserialization
    public MessageDTO() {
    }

    /**
     * Constructs a MessageDTO with the given legacy message text.
     *
     * @param texto the legacy message text
     */
    public MessageDTO(String texto) {
        this.texto = texto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
               "texto='" + texto + '\'' +
               '}';
    }

    // TODO: (REVIEW) Ensure JSON property name 'texto' aligns with frontend expectations and tests
}