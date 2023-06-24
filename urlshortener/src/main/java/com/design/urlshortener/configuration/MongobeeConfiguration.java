package com.design.urlshortener.configuration;

import com.github.mongobee.Mongobee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!disabled")
public class MongobeeConfiguration {

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Value("${spring.data.mongodb.host}")
    private String host;

    @Value("${spring.data.mongodb.port}")
    private String port;

    @Bean
    public Mongobee mongobee() {
        Mongobee runner = new Mongobee("mongodb://" + host + ":" + port + "/" + databaseName);
        runner.setDbName(databaseName);
        runner.setChangeLogsScanPackage("com.design.urlshortener.migration");

        return runner;
    }
}
