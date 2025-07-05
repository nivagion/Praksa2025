package me.leo.database;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.leo.core.Person;
import me.leo.core.PersonRepository;
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