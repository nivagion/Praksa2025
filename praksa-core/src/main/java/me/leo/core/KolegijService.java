package me.leo.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KolegijService {

    private final KolegijRepository kolegijRepository;

    public Kolegij save(String name) {
        return kolegijRepository.save(new Kolegij(null, name));
    }

    public Kolegij findById(long id) {
        return kolegijRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Kolegij not found"));
    }

    public void delete(long id) {
        kolegijRepository.deleteById(id);
    }
}
