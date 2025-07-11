package me.leo.database;

import me.leo.core.Profesor;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DbProfesorMapper {

    private final DbKolegijMapper dbKolegijMapper;

    public Profesor toModel(DbProfesor entity) {
        return new Profesor(
                entity.getId(),
                entity.getName()
                // core ima samo id i ime a nema kolegij, a dbprofesor ima i kolegij pa mu tu dolje dodajemo NULL
        );                                                 // |
    }                                                      // v

    public DbProfesor toEntity(Profesor model) {
        // domenski model još nema kolegij
        return new DbProfesor(model.id(), model.name(), null);
    }
}
