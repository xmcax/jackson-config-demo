package pl.adamnowicki.jacksonconfigdemo.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfiguration {

    /* Overwrites org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
    in org.springframework.boot:spring-boot-autoconfigure module.
    This disables auto-configuration of ObjectMapper.

    see https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/#howto-customize-the-jackson-objectmapper */
    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        return new Jackson2ObjectMapperBuilder()
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
}
