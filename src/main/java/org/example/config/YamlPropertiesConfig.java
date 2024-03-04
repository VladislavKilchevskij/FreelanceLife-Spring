package org.example.config;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Objects;

@Configuration
public class YamlPropertiesConfig {

    private static final String YAML_PROPERTIES_FILE = "application.yml";

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource(YAML_PROPERTIES_FILE));
        propertySourcesPlaceholderConfigurer.setProperties(Objects.requireNonNull(yaml.getObject()));
        return propertySourcesPlaceholderConfigurer;
    }
}
