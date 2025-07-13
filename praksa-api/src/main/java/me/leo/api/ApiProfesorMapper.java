package me.leo.api;

import me.leo.core.Profesor;
import org.springframework.stereotype.Component;

@Component
public class ApiProfesorMapper {

    public ProfesorResponse toResponse(Profesor profesor) {
        return new ProfesorResponse(profesor.id(), profesor.name());
    }

    public Profesor toModel(ProfesorRequest request) {
        return new Profesor(null, request.name(), request.kolegijId());
    }
}
