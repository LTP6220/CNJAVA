package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
        @PropertySource("classpath:.properties") // Load properties from application.properties
})
public class AppConfig {
    @Value("${app.title}")
    private String title;

    @Value("${app.version}")
    private String version;

    @Bean
    public MyBean myBean() {
        return new MyBean(title, version);
    }
}
