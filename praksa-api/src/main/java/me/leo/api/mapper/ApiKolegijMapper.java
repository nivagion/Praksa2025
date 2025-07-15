package me.leo.api.mapper;

import me.leo.api.request.KolegijRequest;
import me.leo.api.response.KolegijResponse;
import me.leo.core.model.Kolegij;
import org.springframework.stereotype.Component;

@Component
public class ApiKolegijMapper {

    public KolegijResponse toResponse(Kolegij kolegij) {
        return new KolegijResponse(kolegij.id(), kolegij.name());
    }

    public Kolegij toModel(KolegijRequest request) {
        return new Kolegij(null, request.name());
    }
}
