package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public Product bean1() {
        Product product = new Product();
        product.setId(1);
        product.setName("Product 1");
        product.setPrice(10.99);
        product.setDescription("This is product 1");
        return product;
    }

    @Bean
    @Scope("prototype")
    public Product bean2() {
        Product product = new Product();
        product.setId(2);
        product.setName("Product 2");
        product.setPrice(15.99);
        product.setDescription("This is product 2");
        return product;
    }

    @Bean
    @Scope("singleton")
    public Product bean3() {
        Product product = new Product();
        product.setId(3);
        product.setName("Product 3");
        product.setPrice(19.99);
        product.setDescription("This is product 3");
        return product;
    }
}
