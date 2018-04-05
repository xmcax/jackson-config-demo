package pl.adamnowicki.jacksonconfigdemo.web.person;

import java.time.LocalDate;

public class Person {
    private final String name;
    private final LocalDate dateOfBirthday;

    public Person(String name, LocalDate dateOfBirthday) {
        this.name = name;
        this.dateOfBirthday = dateOfBirthday;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirthday() {
        return dateOfBirthday;
    }
}
