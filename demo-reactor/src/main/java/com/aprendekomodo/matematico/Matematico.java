package com.aprendekomodo.matematico;

import com.aprendekomodo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Matematico {

    private static final Logger logger = LoggerFactory.getLogger(Matematico.class);

    public void average() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .collect(Collectors.averagingInt(Persona::getEdad))
                .subscribe(p -> logger.info(p.toString()));
    }

    public void count() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .count()
                .subscribe(p -> logger.info("cantidad: "+p));
    }

    public void min() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .collect(Collectors.minBy(Comparator.comparing(Persona::getEdad)))
                .subscribe(p -> logger.info(p.get().toString()));
    }

    public void sum() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .collect(Collectors.summingInt(Persona::getEdad))
                .subscribe(p -> logger.info("suma" + p));
    }

    public void summarizing() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .collect(Collectors.summarizingInt(Persona::getEdad))
                .subscribe(p -> logger.info("resumen: " + p));
    }

}
