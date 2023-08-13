package com.aprendekomodo.webfluxdemo.repo;

import com.aprendekomodo.webfluxdemo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

@Repository
public class PersonaRepoImpl implements IPersonaRepo{

    private static final Logger logger = LoggerFactory.getLogger(PersonaRepoImpl.class);

    @Override
    public Mono<Persona> registrar(Persona persona) {
        logger.info("registrar: {}",persona.toString());
        return Mono.just(persona);
    }

    @Override
    public Mono<Persona> modificar(Persona persona) {
        logger.info("modificar: {}",persona.toString());
        return Mono.just(persona);
    }

    @Override
    public Flux<Persona> listar() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo"));
        personas.add(new Persona(2, "Dorey"));
        personas.add(new Persona(3, "Comicon"));
        return Flux.fromIterable(personas);
    }

    @Override
    public Mono<Persona> listarPorId(Integer id) {
        return Mono.just(new Persona(id, "Luijo"));
    }

    @Override
    public Mono<Void> eliminar(Integer id) {
        return Mono.empty();
    }
}
