// Ovako implementirano je zbunjujuće zašto ovo uopće postoji umjesto da PersonController direktno poziva funkcije iz
// PersonRepository. Za neke requestove je potrebno izvršiti neku dodatnu business logiku (za ove očito nije), i ta bi
// logika trebala biti implementirana u Service klasi (i Core modulu), ne u Repository klasi (i Database modulu).
// Također, API i Database moduli ne komuniciraju direktno nego kroz Core modul.

package me.leo.core;

import lombok.RequiredArgsConstructor;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public Person save(final Person person) {

        return personRepository.save(person);
    }

    public Optional<Person> findById(final long id) {

        return personRepository.findById(id);
    }

}
