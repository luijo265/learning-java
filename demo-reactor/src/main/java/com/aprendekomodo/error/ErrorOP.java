package com.aprendekomodo.error;

import com.aprendekomodo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

public class ErrorOP {

    private static final Logger logger = LoggerFactory.getLogger(ErrorOP.class);

    public void retry() {

        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .retry(1)
                .doOnNext(x -> logger.info(x.toString()))
                .subscribe();

    }

    public void errorReturn() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorReturn(new Persona(0, "XYZ", 99))
                .subscribe(x -> logger.info(x.toString()));
    }

    public void errorResume() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorResume(e -> Mono.just(new Persona(0, "XYV", 99)))
                .subscribe(x -> logger.info(x.toString()));
    }

    public void errorMap() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .concatWith(Flux.error(new RuntimeException("Un Error")))
                .onErrorMap(e -> new InterruptedException(e.getMessage()))
                .subscribe(x -> logger.info(x.toString()));
    }

}
