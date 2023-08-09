package com.aprendekomodo.filtrado;

import com.aprendekomodo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.List;

public class Filtrado {

    private static final Logger logger = LoggerFactory.getLogger(Filtrado.class);

    public void filter() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .filter(persona -> persona.getEdad() > 28)
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

    public void distinct() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .distinct() // es requerido sobreescribir el método equals
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

    public void take() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .take(2) // captura los primeros 2 del arreglo
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

    public void takeLast() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .takeLast(1) // captura los primeros 2 del arreglo
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

    public void skip() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .skip(1) // captura los primeros 2 del arreglo
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

    public void skipLast() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .skipLast(1) // captura los primeros 2 del arreglo
                .subscribe(persona -> logger.info("personaFilter: {}", persona));
    }

}
