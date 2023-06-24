package com.design.urlshortener.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoTestConfiguration {

    @Bean
    @Primary
    public MongoTemplate mongoTemplate() {
        return Mockito.mock(MongoTemplate.class);
    }

}