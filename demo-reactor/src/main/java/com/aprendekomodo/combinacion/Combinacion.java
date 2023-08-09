package com.aprendekomodo.combinacion;

import com.aprendekomodo.model.Persona;
import com.aprendekomodo.model.Venta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class Combinacion {

    private static final Logger logger = LoggerFactory.getLogger(Combinacion.class);

    public void merge() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        List<Persona> personas2 = new LinkedList<>();
        personas2.add(new Persona(4, "Karl", 32));
        personas2.add(new Persona(5, "Renji", 30));
        personas2.add(new Persona(6, "Markus", 27));

        List<Venta> ventas = new LinkedList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

        Flux.merge(fx1, fx2, fx3)
                .subscribe(persona -> logger.info("personaMerge: {}", persona));

    }

    public void zip() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        List<Persona> personas2 = new LinkedList<>();
        personas2.add(new Persona(4, "Karl", 32));
        personas2.add(new Persona(5, "Renji", 30));
        personas2.add(new Persona(6, "Markus", 27));

        List<Venta> ventas = new LinkedList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

//        Flux.zip(fx1, fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
//            .subscribe(persona -> logger.info("personaZip: {}", persona));

        Flux.zip(fx1, fx2, fx3)
                .subscribe(zip -> logger.info("zip: {}", zip));
    }

    public void zipWith() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        List<Persona> personas2 = new LinkedList<>();
        personas2.add(new Persona(4, "Karl", 32));
        personas2.add(new Persona(5, "Renji", 30));
        personas2.add(new Persona(6, "Markus", 27));

        List<Venta> ventas = new LinkedList<>();
        ventas.add(new Venta(1, LocalDateTime.now()));

        Flux<Persona> fx1 = Flux.fromIterable(personas);
        Flux<Persona> fx2 = Flux.fromIterable(personas2);
        Flux<Venta> fx3 = Flux.fromIterable(ventas);

//        fx1.zipWith(fx2, (p1, p2) -> String.format("Flux1: %s, Flux2: %s", p1, p2))
//                .subscribe(zip -> logger.info("zipWith: {}", zip));

        fx1.zipWith(fx3)
                .subscribe(zip -> logger.info("zipWithVenta: {}", zip));
    }
}
