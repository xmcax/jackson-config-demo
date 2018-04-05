package pl.adamnowicki.jacksonconfigdemo.web.person;

import pl.adamnowicki.jacksonconfigdemo.config.JacksonConfiguration;
import pl.adamnowicki.jacksonconfigdemo.config.WebConfiguration;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class PersonControllerUnitTest {

    private MockMvc mockMvc;

    @Before
    public void init() {
        JacksonConfiguration jacksonConfiguration = new JacksonConfiguration();
        Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder = jacksonConfiguration.jackson2ObjectMapperBuilder();

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter =
                new WebConfiguration().mappingJackson2HttpMessageConverter(jackson2ObjectMapperBuilder);

        mockMvc = MockMvcBuilders.standaloneSetup(new PersonController())
                .setMessageConverters(mappingJackson2HttpMessageConverter)
                .build();
    }
    @Test
    public void shouldApplyCustomJacksonConfig() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get(PersonController.PERSON_RESOURCE + PersonController.PERSON_SAMPLE_ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(PersonController.NAME)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.date_of_birthday", CoreMatchers.is(PersonController.DATE_OF_BIRTHDAY.format(ISO_DATE))));
    }

}