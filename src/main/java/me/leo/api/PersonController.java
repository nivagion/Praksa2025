package me.leo.api;

import person.app.api.mapper.ApiPersonMapper;
import person.app.api.request.PersonRequest;
import person.app.api.response.PersonResponse;
import person.app.core.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class AppInstallationController {
//a
    private final ApiPersonMapper apiPersonMapper;
    private final PersonService personService;

    // POST endpoint na /person
    @PostMapping
    public ResponseEntity<PersonResponse> create(@RequestBody final PersonRequest personRequest) {

        return ResponseEntity.ok(apiPersonMapper.toResponse(personService.save(apiPersonMapper.toModel(personRequest))));
    }

    // GET endpoint na /person/{personId}
    @GetMapping("/{personId}")
    public ResponseEntity<PersonResponse> get(@PathVariable final long personId) {

        return ResponseEntity.of(personService.findById(personId).map(apiPersonMapper::toResponse));
    }

}