package com.webflux.reactor.springboot.reactor.app;

import com.webflux.reactor.springboot.reactor.models.Usuario;
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
//				.map(String::toUpperCase)
//				.doOnNext(this::validateNombre)
//				.map(String::toLowerCase)
		Flux<Usuario> nombres = Flux.just("Luis Cervantes",
						"Dayana Perez",
						"Alex Peñaranda",
						"Oswaldo Jimenez",
						"Dora Vallejo",
						"Piedad Bolivar")
				.map(this::toUpperCase)
				.filter(usuario -> filterNombrePorLetras(usuario, "l"))
				.doOnNext(this::validateUsuario)
				.map(this::toLowerCase)
				;
//				.doOnNext(elemento -> System.out.println(elemento));

		// Para suscribirnos al publisher y ver las operaciones que se ejecutan
//		nombres.subscribe(logger::info);
//		nombres.subscribe(logger::info, this::catchError);
//		nombres.subscribe(
//				this::showFromSuscribe,
//				this::catchError,
//				new Runnable() {
//					@Override
//					public void run() {
//						logger.info("Ha finalizado ejecucion del observable con exito!");
//					}
//				}
//		);
		nombres.subscribe(
				this::showFromSuscribeFluxUsuario,
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
		logger.info("validateNombreFromDoOnNext {}", nombre);
	}

	private void validateUsuario(Usuario usuario) {
		if (usuario == null){
			throw new RuntimeException("Nombre no puede estar vacio");
		}
		logger.info("validateUsuarioFromDoOnNext {}", usuario);
	}

	private void catchError(Throwable e) {
		logger.error(e.getMessage());
	}

	private void showFromSuscribe(String nombre){
		logger.info("showFromSuscribe {}", nombre);
	}

	private void showFromSuscribeFluxUsuario(Usuario usuario){
		logger.info("showFromSuscribeFluxUsuario {}", usuario.getNombre());
		logger.info("showFromSuscribeFluxUsuario {}", usuario);
	}

	private Usuario toUpperCase(String nombre) {
		String[] splitNombre = nombre.split(" ");
		return new Usuario(splitNombre[0].toUpperCase(), splitNombre[1].toUpperCase());
	}

	private Usuario toLowerCase(Usuario usuario) {
		usuario.setNombre(usuario.getNombre().toLowerCase());
		return usuario;
	}

	private boolean filterNombrePorLetras(Usuario usuario, String letras) {
		return usuario.getNombre().toLowerCase().contains(letras);
	}
}
