package me.leo.database.mapper;

import me.leo.core.model.Kolegij;
import me.leo.database.model.entity.DbKolegij;
import org.springframework.stereotype.Component;

@Component
public class DbKolegijMapper {

    public Kolegij toModel(DbKolegij entity) {
        return new Kolegij(entity.getId(), entity.getName());
    }

    public DbKolegij toEntity(Kolegij model) {
        return new DbKolegij(model.id(), model.name());
    }
}
