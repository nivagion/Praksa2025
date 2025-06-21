package me.leo.database;

import person.app.core.repository.PersonRepository;
import person.app.core.model.Person;
import person.app.database.mapper.DbPersonMapper;
import person.app.database.repository.DbPersonRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PersonRepositoryImpl implements PersonRepository {

    private final DbPersonRepository dbPersonRepository;
    private final DbPersonMapper dpPersonMapper;

    @Override
    public Person save(final Person person) {

        return dpPersonMapper.toModel(dbPersonRepository.save(dpPersonMapper.toEntity(person)));
    }

    @Override
    public Optional<Person> findById(final long id) {

        return dbPersonRepository.findById(id).map(dpPersonMapper::toModel);
    }

}