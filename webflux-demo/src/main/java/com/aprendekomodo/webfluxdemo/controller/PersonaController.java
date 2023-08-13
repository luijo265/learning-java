package com.aprendekomodo.webfluxdemo.controller;

import com.aprendekomodo.webfluxdemo.model.Persona;
import com.aprendekomodo.webfluxdemo.repo.IPersonaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private IPersonaRepo repo;

    @GetMapping
    public Flux<Persona> listar() {
        return repo.listar();
    }

    @GetMapping("/{id}")
    public Mono<Persona> listarPorId(@PathVariable("id") Integer id) {
        return repo.listarPorId(id);
    }

    @PostMapping
    public Mono<Persona> registrar(@RequestBody Persona persona) {
        return repo.registrar(persona);
    }

    @PutMapping
    public Mono<Persona> modificar(@RequestBody Persona persona) {
        return repo.modificar(persona);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> eliminar(@PathVariable("id") Integer id) {
        return repo.listarPorId(id)
                .flatMap(persona -> repo.eliminar(id));
    }

}
