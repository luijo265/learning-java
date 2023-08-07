package com.aprendekomodo.operador.transformacion;

import com.aprendekomodo.model.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

public class Transformacion {

    private static final Logger logger = LoggerFactory.getLogger(Transformacion.class);

    public void map() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .map(persona -> {
                    persona.setEdad(persona.getEdad()+10);
                    return persona;
                })
                .subscribe(p -> logger.info("PersonaMap: {}", p));
    }

    public void mapInmutable() {
        Flux<Integer> fx = Flux.range(0, 10);
        Flux<Integer> fxMap = fx.map(numero -> numero + 10);
        // El fx no ve el map realizado previamente, ya en si la funciona devuelve un nuevo flux, no transforma el inicial
        fx.subscribe(numero -> logger.info("numeroFx: {}", numero));
        fxMap.subscribe(numero -> logger.info("numeroFxMap: {}", numero));

    }

    public void flatMap() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> logger.info("PersonaFlatMap: {}", p));
    }

    public void groupBy() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(1, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .groupBy(Persona::getIdPersona)
                .flatMap(idFlux -> idFlux.collectList())
                .subscribe(x -> logger.info(x.toString()));
    }

}
