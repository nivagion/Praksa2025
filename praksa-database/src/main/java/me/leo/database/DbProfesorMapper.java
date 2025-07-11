package me.leo.database;

import me.leo.core.Profesor;
import org.springframework.stereotype.Component;

@Component
public class DbProfesorMapper {

    public Profesor toModel(DbProfesor entity) {
        return new Profesor(entity.getId(), entity.getName());
    }

    public DbProfesor toEntity(Profesor model) {
        return new DbProfesor(model.id(), model.name());
    }
}
