package me.leo.api;

import me.leo.core.Kolegij;
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
