package me.leo.database;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import me.leo.core.Person;
import me.leo.core.PersonRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Component
public class PersonRepositoryImpl implements PersonRepository {

    private final DbPersonRepository dbPersonRepository;
    private final DbPersonMapper dpPersonMapper;

    @Override
    public Person save(final Person person) {
        // savea u database entity i onda vraća toModel tu osobu nazad
        return dpPersonMapper.toModel(dbPersonRepository.save(dpPersonMapper.toEntity(person)));
    }

    @Override
    public Optional<Person> findById(final long id) {

        return dbPersonRepository.findById(id).map(dpPersonMapper::toModel);
    }

}