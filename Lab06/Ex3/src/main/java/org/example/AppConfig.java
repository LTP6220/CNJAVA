package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.example")
public class AppConfig {
    @Bean
    @Qualifier("plainTextWriter")
    public TextWriter plainTextWriter() {
        return new PlainTextWriter();
    }

    @Bean
    @Qualifier("pdfTextWriter")
    public TextWriter pdfTextWriter() {
        return new PdfTextWriter();
    }

    @Bean
    public TextEditor textEditor() {
        return new TextEditor();
    }
}
