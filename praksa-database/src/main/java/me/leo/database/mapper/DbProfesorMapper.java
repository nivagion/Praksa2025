package me.leo.database.mapper;

import me.leo.core.model.Profesor;
import me.leo.database.repository.DbKolegijRepository;
import me.leo.database.model.entity.DbKolegij;
import me.leo.database.model.entity.DbProfesor;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DbProfesorMapper {

    private final DbKolegijRepository dbKolegijRepository;

    public Profesor toModel(DbProfesor entity) {
        return new Profesor(
                entity.getId(),
                entity.getName(),
                entity.getKolegij().getId()
        );
    }

    public DbProfesor toEntity(Profesor model) {
        DbKolegij kolegij = dbKolegijRepository.findById(model.kolegijId())
                .orElseThrow(() -> new IllegalArgumentException("Kolegij not found"));

        return new DbProfesor(model.id(), model.name(), kolegij);
    }
}

