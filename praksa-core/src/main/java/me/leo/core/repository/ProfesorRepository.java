package me.leo.core.repository;

import me.leo.core.model.Profesor;

import java.util.Optional;

public interface ProfesorRepository {
    Profesor save(Profesor profesor);
    Optional<Profesor> findById(long id);
    void deleteById(long id);
}
