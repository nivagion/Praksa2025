package me.leo.database;

import me.leo.core.Person;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
public class DbPersonMapper {

    public Person toModel(@org.jetbrains.annotations.NotNull final DbPerson dbPerson) {

        return new Person(dbPerson.getId(), dbPerson.getName());
    }

    public DbPerson toEntity(final @NotNull Person person) {

        return new DbPerson(person.getId(), person.getName());
    }

}