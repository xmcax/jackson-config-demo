package pl.adamnowicki.jacksonconfigdemo.web.person;

import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * WebMvcTest will pick @Controller, @ControllerAdvice, @JsonComponent, Filter, WebMvcConfigurer
 * and HandlerMethodArgumentResolver
 *
 * see: https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-testing-spring-boot-applications-testing-autoconfigured-mvc-tests
 *
 * To make sure ObjectMapper and MappingJackson2HttpMessageConverter are picked by WebMvcTest,
 * apply one of the following strategies:
 * - set includeFilters in @WebMvcTest:
 *   includeFilters = @Filter(type = ASSSIGNABLE_TYPE, classes = { JacksonConfiguration.class, WebConfiguration.class })
 * - Provide @Bean declarations for ObjectMapper and MappingJackson2HttpMessageConverter in
 *   a class implementing WebMvcConfigurer
 * - Provide @Bean declarations for ObjectMapper and MappingJackson2HttpMessageConverter in
 *   a class imported via @Import from a class implementing WebMvcConfigurer
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = PersonController.class)
public class PersonControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldApplyCustomJacksonConfig() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(PersonController.PERSON_RESOURCE + PersonController.PERSON_SAMPLE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(PersonController.NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_of_birthday", CoreMatchers.is(PersonController.DATE_OF_BIRTHDAY.format(ISO_DATE))));
    }
}