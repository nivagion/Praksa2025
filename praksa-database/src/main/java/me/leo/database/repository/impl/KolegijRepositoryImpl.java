package me.leo.database.repository.impl;

import lombok.RequiredArgsConstructor;
import me.leo.core.model.Kolegij;
import me.leo.core.repository.KolegijRepository;
import me.leo.database.mapper.DbKolegijMapper;
import me.leo.database.repository.DbKolegijRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class KolegijRepositoryImpl implements KolegijRepository {

    private final DbKolegijRepository dbKolegijRepository;
    private final DbKolegijMapper dbKolegijMapper;

    @Override
    public Kolegij save(Kolegij kolegij) {
        return dbKolegijMapper.toModel(
                dbKolegijRepository.save(dbKolegijMapper.toEntity(kolegij))
        );
    }

    @Override
    public Optional<Kolegij> findById(long id) {
        return dbKolegijRepository.findById(id).map(dbKolegijMapper::toModel);
    }

    @Override
    public void deleteById(long id) {
        dbKolegijRepository.deleteById(id);
    }
}
