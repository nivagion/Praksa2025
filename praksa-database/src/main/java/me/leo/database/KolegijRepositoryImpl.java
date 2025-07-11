package me.leo.database;

import lombok.RequiredArgsConstructor;
import me.leo.core.Kolegij;
import me.leo.core.KolegijRepository;
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
