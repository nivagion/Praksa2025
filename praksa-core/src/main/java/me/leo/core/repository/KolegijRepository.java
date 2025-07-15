package me.leo.core.repository;

import me.leo.core.model.Kolegij;

import java.util.Optional;

public interface KolegijRepository {
    Kolegij save(Kolegij kolegij);
    Optional<Kolegij> findById(long id);
    void deleteById(long id);
}
