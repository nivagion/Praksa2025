package me.leo.api.controller;

import lombok.RequiredArgsConstructor;
import me.leo.api.request.KolegijRequest;
import me.leo.api.response.KolegijResponse;
import me.leo.api.mapper.ApiKolegijMapper;
import me.leo.core.model.Kolegij;
import me.leo.core.service.KolegijService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kolegij")
@RequiredArgsConstructor
public class KolegijController {

    private final KolegijService kolegijService;
    private final ApiKolegijMapper apiKolegijMapper;

    @PostMapping
    public ResponseEntity<KolegijResponse> create(@RequestBody KolegijRequest request) {
        Kolegij saved = kolegijService.save(request.name());
        return ResponseEntity.ok(apiKolegijMapper.toResponse(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<KolegijResponse> findById(@PathVariable long id) {
        return ResponseEntity.ok(apiKolegijMapper.toResponse(kolegijService.findById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        kolegijService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
