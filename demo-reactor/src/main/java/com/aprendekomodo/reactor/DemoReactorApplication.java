package com.aprendekomodo.reactor;

import com.aprendekomodo.combinacion.Combinacion;
import com.aprendekomodo.filtrado.Filtrado;
import com.aprendekomodo.model.Persona;
import com.aprendekomodo.operador.creacion.Creacion;
import com.aprendekomodo.operador.transformacion.Transformacion;
import io.reactivex.rxjava3.core.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class DemoReactorApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DemoReactorApplication.class);

	public void reactor() {
		Mono.just(new Persona(1, "Luijo", 32))
				// doOnNext proceso de depuracion para observar que ocurre, el suscribe es para
				// recuperar la informacion final
				.doOnNext(p -> {
					// logica adicional
					logger.info("[ReactorOnNext] persona: {}", p);
				})
				.subscribe(p -> logger.info("[Reactor] persona: {}", p));
	}

	public void rxjava() {
		Observable.just(new Persona(1, "Caro", 30))
				.doOnNext(p -> logger.info("[RxJavaOnNext] persona: {}", p))
				.subscribe(p -> logger.info("[RxJava] persona: {}", p));
	}

	public void mono() {
		Mono.just(new Persona(1, "Bonlax", 27))
				.subscribe(p -> logger.info("PersonaMono {}", p));
	}

	public void flux() {
		List<Persona> personas = new LinkedList<>();
		personas.add(new Persona(1, "Luijo", 32));
		personas.add(new Persona(2, "Caro", 30));
		personas.add(new Persona(3, "Bonlax", 27));

		Flux.fromIterable(personas)
				.subscribe(p -> logger.info("PersonaFlux: {}", p));

	}

	public void fluxMono() {
		List<Persona> personas = new LinkedList<>();
		personas.add(new Persona(1, "Luijo", 32));
		personas.add(new Persona(2, "Caro", 30));
		personas.add(new Persona(3, "Bonlax", 27));

		Flux<Persona> fluxPersonas = Flux.fromIterable(personas);
		fluxPersonas.collectList().subscribe(p -> logger.info("PersonaFlux: {}", p));
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*reactor();
		rxjava();*/

//		mono();
//		flux();

//		fluxMono();

//		Creacion appCreacion = new Creacion();
//		appCreacion.range();
//		appCreacion.repeat();


//		Transformacion transformacion = new Transformacion();
//		transformacion.map();
//		transformacion.mapInmutable();
//		transformacion.flatMap();
//		transformacion.groupBy();

//		Filtrado filtrado = new Filtrado();
//		filtrado.filter();
//		filtrado.distinct();
//		filtrado.take();
//		filtrado.takeLast();
//		filtrado.skip();
//		filtrado.skipLast();

		Combinacion combiancion = new Combinacion();
//		combiancion.merge();
//		combiancion.zip();
		combiancion.zipWith();

	}
}
