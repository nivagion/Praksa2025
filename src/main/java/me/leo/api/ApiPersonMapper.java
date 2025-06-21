import org.springframework.stereotype.Component;
import person.app.core.model.Person;
import person.app.api.request.PersonRequest;
import person.app.api.response.PersonResponse;

@Component
public class ApiPersonMapper {

    public Person toModel(final PersonRequest personRequest) {

        return new Person(null, personRequest.name());
    }

    public PersonResponse toResponse(final Person person) {

        return new PersonResponse(person.getId(), person.getName());
    }

}