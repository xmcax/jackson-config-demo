package pl.adamnowicki.jacksonconfigdemo.web.person;

import org.apache.http.HttpStatus;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.jayway.restassured.RestAssured.when;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @Test
    public void shouldApplyCustomJacksonConfig() {
        when()
            .get(PersonController.PERSON_RESOURCE + PersonController.PERSON_SAMPLE_ENDPOINT)
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("name", CoreMatchers.is(PersonController.NAME))
            .body("date_of_birthday", CoreMatchers.is(PersonController.DATE_OF_BIRTHDAY.format(ISO_DATE)));
    }
}
