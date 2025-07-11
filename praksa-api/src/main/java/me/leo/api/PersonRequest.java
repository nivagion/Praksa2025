package me.leo.api;

public record PersonRequest(String name) {}
/*
[ JSON Request ]
        ⬇
PersonRequest (DTO)
     ⬇
             PersonService.save(name)
     ⬇
             PersonRepositoryImpl.save(...)
     ⬇
             DbPersonRepository.save(...)
     ⬇
DbPerson ➝ Person (mapirano)
     ⬆
PersonService returns Person
     ⬆
             ApiPersonMapper.toResponse(...)
     ⬆
PersonResponse ➝ [ JSON Response ]

 */