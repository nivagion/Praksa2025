package me.leo.api.mapper;

import me.leo.api.request.ProfesorRequest;
import me.leo.api.response.ProfesorResponse;
import me.leo.core.model.Profesor;
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
