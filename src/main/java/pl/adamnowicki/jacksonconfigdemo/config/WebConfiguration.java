package pl.adamnowicki.jacksonconfigdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Import(JacksonConfiguration.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {
    /*
    Make sure @EnableWebMVC is not present in the application, otherwise entire WebMVC autoconfiguration
    is turned off and this converter needs to be registered manually by extending either:
    WebMvcConfigurerAdapter or WebMvcConfigurationSupport
    and calling:
    configureMessageConverters or extendMessageConverters

    see https://docs.spring.io/spring-boot/docs/1.5.12.RELEASE/reference/htmlsingle/#howto-customize-the-responsebody-rendering
     */
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        return new MappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder.build());
    }
}
