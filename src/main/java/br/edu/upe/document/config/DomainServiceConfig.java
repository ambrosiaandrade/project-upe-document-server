package br.edu.upe.document.config;

import br.edu.upe.document.domain.port.output.OutputPortDocument;
import br.edu.upe.document.domain.service.DocumentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for domain services.
 * This class explicitly defines how domain services are instantiated,
 * ensuring that the domain layer remains free of Spring dependencies.
 */
@Configuration
public class DomainServiceConfig {

    @Bean
    public DocumentService documentService(final OutputPortDocument outputPortDocument) {
        return new DocumentService(outputPortDocument);
    }

}