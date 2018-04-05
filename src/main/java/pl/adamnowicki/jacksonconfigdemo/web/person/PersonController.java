package pl.adamnowicki.jacksonconfigdemo.web.person;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping(PersonController.PERSON_RESOURCE)
class PersonController {

    static final String NAME = "Adam";
    static final LocalDate DATE_OF_BIRTHDAY = LocalDate.of(1990, 1, 1);
    static final String PERSON_RESOURCE = "/person";
    static final String PERSON_SAMPLE_ENDPOINT = "/sample";

    @GetMapping(PERSON_SAMPLE_ENDPOINT)
    Person getCurrentPerson() {
        return new Person(NAME, DATE_OF_BIRTHDAY);
    }
}
