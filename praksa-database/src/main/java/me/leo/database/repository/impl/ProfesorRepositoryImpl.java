package me.leo.database.repository.impl;

import lombok.RequiredArgsConstructor;
import me.leo.core.model.Profesor;
import me.leo.core.repository.ProfesorRepository;
import me.leo.database.mapper.DbProfesorMapper;
import me.leo.database.model.entity.DbProfesor;
import me.leo.database.repository.DbKolegijRepository;
import me.leo.database.repository.DbProfesorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfesorRepositoryImpl implements ProfesorRepository {

    private final DbProfesorRepository dbProfesorRepository;
    private final DbProfesorMapper dbProfesorMapper;
    private final DbKolegijRepository dbKolegijRepository;

    @Override
    public Profesor save(Profesor profesor) {
        final DbProfesor dbProfesor = dbProfesorMapper.toEntity(profesor);
        dbProfesor.setKolegij(dbKolegijRepository.findById(profesor.kolegijId())
                .orElseThrow(() -> new IllegalArgumentException("Kolegij not found"))); // raspakiravamo Optional
        return dbProfesorMapper.toModel(dbProfesorRepository.save(dbProfesor));
    }

    @Override
    public Optional<Profesor> findById(long id) {
        return dbProfesorRepository.findById(id).map(dbProfesorMapper::toModel);
    }

    @Override
    public void deleteById(long id) {
        dbProfesorRepository.deleteById(id);
    }
}
