package com.aprendekomodo.webfluxdemo.repo;

import com.aprendekomodo.webfluxdemo.model.Persona;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonaRepo {

    Flux<Persona> listar();

    Mono<Persona> listarPorId(Integer id);

    Mono<Void> eliminar(Integer id);

    Mono<Persona> registrar(Persona persona);

    Mono<Persona> modificar(Persona persona);

}
