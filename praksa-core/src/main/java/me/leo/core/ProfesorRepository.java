package me.leo.core;

import java.util.Optional;

public interface ProfesorRepository {
    Profesor save(Profesor profesor);
    Optional<Profesor> findById(long id);
    void deleteById(long id);
}
