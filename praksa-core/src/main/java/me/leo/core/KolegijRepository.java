package me.leo.core;

import java.util.Optional;

public interface KolegijRepository {
    Kolegij save(Kolegij kolegij);
    Optional<Kolegij> findById(long id);
    void deleteById(long id);
}
