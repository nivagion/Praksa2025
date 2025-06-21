package me.leo.core;

import me.leo.core.Person;
import java.util.Optional;

public interface PersonRepository {

    Person save(Person person);

    Optional<Person> findById(long id);

}