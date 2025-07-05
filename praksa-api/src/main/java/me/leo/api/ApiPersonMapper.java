package me.leo.api;

import me.leo.core.Person;
import org.springframework.stereotype.Component;

@Component
public class ApiPersonMapper {

    public Person toModel(final PersonRequest personRequest) {

        return new Person(null, personRequest.name());
    }

    public PersonResponse toResponse(final Person person) {

        return new PersonResponse(person.getId(), person.getName());
    }

}