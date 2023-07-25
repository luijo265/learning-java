package com.webflux.reactor.springboot.reactor.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Flux es un publicador, es decir que cambia de estado y lo siguiente
		// es un observer que reaccione a ese cambio de estado
//		Flux<String> nombres = Flux.just("Luis", "Dayana", "Alex", "")
		Flux<String> nombres = Flux.just("Luis", "Dayana", "Alex")
				.doOnNext(this::validateNombre);
//				.doOnNext(elemento -> System.out.println(elemento));

		// Para suscribirnos al publisher y ver las operaciones que se ejecutan
//		nombres.subscribe(logger::info);
//		nombres.subscribe(logger::info, this::catchError);
		nombres.subscribe(
				logger::info,
				this::catchError,
				new Runnable() {
					@Override
					public void run() {
						logger.info("Ha finalizado ejecucion del observable con exito!");
					}
				}
		);


	}

	private void validateNombre(String nombre) {
		if (nombre.isEmpty()){
			throw new RuntimeException("Nombre no puede estar vacio");
		}
		System.out.println(nombre);
	}

	private void catchError(Throwable e) {
		logger.error(e.getMessage());
	}
}
