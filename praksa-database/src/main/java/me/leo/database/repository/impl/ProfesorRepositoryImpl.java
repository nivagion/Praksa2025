package me.leo.database.repository.impl;

import lombok.RequiredArgsConstructor;
import me.leo.core.model.Profesor;
import me.leo.core.repository.ProfesorRepository;
import me.leo.database.mapper.DbProfesorMapper;
import me.leo.database.repository.DbProfesorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProfesorRepositoryImpl implements ProfesorRepository {

    private final DbProfesorRepository dbProfesorRepository;
    private final DbProfesorMapper dbProfesorMapper;

    @Override
    public Profesor save(Profesor profesor) {
        return dbProfesorMapper.toModel(
                dbProfesorRepository.save(dbProfesorMapper.toEntity(profesor))
        );
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
