package com.aprendekomodo.condicional;

import com.aprendekomodo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class Condicional {

    private static final Logger logger = LoggerFactory.getLogger(Condicional.class);

    public void defaultIfEmpty() {

        Mono.just(new Persona(0, "Luijo", 29))
//        Mono.empty()
//        Flux.empty()
                .defaultIfEmpty(new Persona(0, "default", 99))
                .subscribe(x -> logger.info(x.toString()));

    }

    public void takeUntil() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .takeUntil(p -> p.getEdad() < 31)
                .subscribe(p -> logger.info(p.toString()));

    }

    public void timeout() throws InterruptedException {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .delayElements(Duration.ofSeconds(1))
//                .delayElements(Duration.ofSeconds(3))
                .timeout(Duration.ofSeconds(2))
                .subscribe(p -> logger.info(p.toString()));

        Thread.sleep(10000);

    }

}
