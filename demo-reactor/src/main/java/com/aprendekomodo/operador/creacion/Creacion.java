package com.aprendekomodo.operador.creacion;

import com.aprendekomodo.model.Persona;
import io.reactivex.rxjava3.core.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

public class Creacion {

    private static final Logger logger = LoggerFactory.getLogger(Creacion.class);

    public void justFrom() {
        Mono.just(new Persona(1, "Luijo", 32));
//        Flux.fromIterable(collection)
    }

    public void empty(){
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public void range(){
        Flux.range(0, 3)
                .doOnNext(index -> logger.info("index: {}", index))
                .subscribe();
    }

    public void repeat() {
        List<Persona> personas = new LinkedList<>();
        personas.add(new Persona(1, "Luijo", 32));
        personas.add(new Persona(2, "Caro", 30));
        personas.add(new Persona(3, "Bonlax", 27));

        Flux.fromIterable(personas)
                .repeat(2) // repite despues de la primera iteracion. Es decir ahora son 3 veces
                .subscribe(p -> logger.info("PersonaRepeat: {}", p));

    }
    
}
