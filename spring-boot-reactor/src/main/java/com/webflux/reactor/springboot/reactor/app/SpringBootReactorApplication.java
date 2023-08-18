package com.webflux.reactor.springboot.reactor.app;

import com.webflux.reactor.springboot.reactor.models.Comentario;
import com.webflux.reactor.springboot.reactor.models.Usuario;
import com.webflux.reactor.springboot.reactor.models.UsuarioComentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SpringBootReactorApplication implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootReactorApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ejemploInternvalDesdeCreate();
	}

	public void ejemploInternvalDesdeCreate() {
		Flux.create(emitter -> {
					Timer time = new Timer();
					time.schedule(new TimerTask() {

						private Integer contador = 0;
						@Override
						public void run() {
							emitter.next(++contador);
							if (contador == 5) {
								time.cancel();
								emitter.complete();
							}
							if (contador == 4) {
								time.cancel();
								emitter.error(new InterruptedException("Error contador 4"));
							}
						}
					}, 1000, 1000);
				})
//				.doOnComplete(() -> logger.info("Hemos terminado"))
				.subscribe(
						next -> logger.info(next.toString()),
						error -> logger.info(error.getMessage()),
						() -> logger.info("Hemos terminado")
				);
		// El complete solo se ejecuta cuando todo el flujo finaliza completamente bien
	}

	public void ejemploInternvalInfinito() throws InterruptedException {

		CountDownLatch latch = new CountDownLatch(1);

		Flux.interval(Duration.ofSeconds(1))
				.doOnTerminate(latch::countDown)
				.flatMap(i -> {
					if(i >= 5){
						return Flux.error(new InterruptedException("Solo hasta 5!"));
					}
					return Flux.just(i);
				})
				.map(i -> "Hola "+i)
				.retry(1)
				.subscribe(s -> logger.info(s), e -> logger.error(e.getMessage()));

		latch.await();
	}

	public void ejemploDelayElements() throws InterruptedException {
		Flux<Integer> rango = Flux.range(1, 12)
				.delayElements(Duration.ofSeconds(1))
				.doOnNext(i -> logger.info(i.toString()));

		rango.subscribe();
//		rango.blockLast();

		Thread.sleep(13000);

	}
	public void ejemploInterval() {
		Flux<Integer> rango = Flux.range(1, 12);
		Flux<Long> retraso = Flux.interval(Duration.ofSeconds(1));

		rango.zipWith(retraso, (rangoSource, retrasoSource) -> rangoSource)
				.doOnNext(i -> logger.info(i.toString()))
				.blockLast();
//				.subscribe();
	}

	public void ejemploZipWithRango() {
		Flux.just(1,2,3,4)
				.map(i -> i*2)
				.zipWith(Flux.range(0, 4), (uno, dos) ->
						String.format("Primer flux: %d, Segundo flux: %d", uno, dos))
				.subscribe(texto -> logger.info(texto));
	}

	public void ejemploUsuarioComentariosZipWithForma2() {

		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> crearUsuario());
		Mono<Comentario> comentarioUsuarioMono = Mono.fromCallable(() -> crearComentario());

		Mono<UsuarioComentario> usuarioConComentarioMono = usuarioMono.zipWith(comentarioUsuarioMono)
				.map(tuple -> {
					Usuario usuario = tuple.getT1();
					Comentario comentario = tuple.getT2();
					return new UsuarioComentario(usuario, comentario);
				});

		usuarioConComentarioMono.subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));

	}

	public void ejemploUsuarioComentariosZipWith() {

		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> crearUsuario());
		Mono<Comentario> comentarioUsuarioMono = Mono.fromCallable(() -> crearComentario());

		Mono<UsuarioComentario> usuarioConComentarioMono = usuarioMono.zipWith(comentarioUsuarioMono,
						(usuario, comentarioUsuario) -> new UsuarioComentario(usuario, comentarioUsuario));

		usuarioConComentarioMono.subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));

	}

	public void ejemploUsuarioComentariosFlatMap() {

		Mono<Usuario> usuarioMono = Mono.fromCallable(() -> crearUsuario());
		Mono<Comentario> comentarioUsuarioMono = Mono.fromCallable(() -> crearComentario());

		usuarioMono.flatMap(usuario ->
				comentarioUsuarioMono.map(comentario -> new UsuarioComentario(usuario, comentario)))
				.subscribe(usuarioComentario -> logger.info(usuarioComentario.toString()));

	}

	public Comentario crearComentario() {
		Comentario comentario = new Comentario();
		comentario.addComentario("Hola menor, tu tu tu");
		comentario.addComentario("Recoge la basura");
		comentario.addComentario("Pasa por las cachapas");
		return comentario;
	}

	public Usuario crearUsuario() {
		return new Usuario("Luis", "Marvel");
	}

	public void ejemploIterable() throws Exception {

		// Flux es un publicador, es decir que cambia de estado y lo siguiente
		// es un observer que reaccione a ese cambio de estado
//		Flux<String> nombres = Flux.just("Luis", "Dayana", "Alex", "")
//				.map(String::toUpperCase)
//				.doOnNext(this::validateNombre)
//				.map(String::toLowerCase)

		List<String> usuariosList = new ArrayList<>();
		usuariosList.add("Luis Cervantes");
		usuariosList.add("Dayana Perez");
		usuariosList.add("Alex Peñaranda");
		usuariosList.add("Oswaldo Jimenez");
		usuariosList.add("Dora Vallejo");
		usuariosList.add("Piedad Bolivar");

		/*
		Flux<String> nombres = Flux.just(
					"Luis Cervantes",
						"Dayana Perez",
						"Alex Peñaranda",
						"Oswaldo Jimenez",
						"Dora Vallejo",
						"Piedad Bolivar"); // Al separar aquí, ya nombres es un flux diferente y el suscribe llega hasta el ;
		// hasta aqui nombres es inmutable
		*/

		Flux<String> nombres = Flux.fromIterable(usuariosList);

		Flux<Usuario> usuarios = nombres.map(this::toUpperCase)
				.filter(usuario -> filterNombrePorLetras(usuario, "l"))
				.doOnNext(this::validateUsuario)
				.map(this::toLowerCase)
				;
//				.doOnNext(elemento -> System.out.println(elemento));

		// Para suscribirnos al publisher y ver las operaciones que se ejecutan
//		nombres.subscribe(logger::info);
//		nombres.subscribe(logger::info, this::catchError);
		nombres.subscribe(
				this::showFromSuscribe,
				this::catchError,
				new Runnable() {
					@Override
					public void run() {
						logger.info("Ha finalizado ejecucion del observable con exito!");
					}
				}
		);
		usuarios.subscribe(
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

	public void ejemploToCollectList() throws Exception {

		List<Usuario> usuariosList = new ArrayList<>();
		usuariosList.add(new Usuario("Luis", "Cervantes"));
		usuariosList.add(new Usuario("Dayana", "Perez"));
		usuariosList.add(new Usuario("Alex", "Peñaranda"));
		usuariosList.add(new Usuario("Oswaldo", "Jimenez"));
		usuariosList.add(new Usuario("Dora", "Vallejo"));
		usuariosList.add(new Usuario("Piedad", "Bolivar"));

		Flux.fromIterable(usuariosList)
				.collectList()
				.subscribe(listUsuario -> {
					listUsuario.forEach(item ->logger.info(item.toString()));
				});
	}

	public void ejemploToString() throws Exception {

		List<Usuario> usuariosList = new ArrayList<>();
		usuariosList.add(new Usuario("Luis", "Cervantes"));
		usuariosList.add(new Usuario("Dayana", "Perez"));
		usuariosList.add(new Usuario("Alex", "Peñaranda"));
		usuariosList.add(new Usuario("Oswaldo", "Jimenez"));
		usuariosList.add(new Usuario("Dora", "Vallejo"));
		usuariosList.add(new Usuario("Piedad", "Bolivar"));

		Flux.fromIterable(usuariosList)
				.map(usuario ->
						usuario.getApellido().toUpperCase()
							.concat(" ")
							.concat(usuario.getNombre().toUpperCase())
				)
				.flatMap(nombre -> {
					if (nombre.toLowerCase().contains("j")){
						return Mono.just(nombre);
					}
					return Mono.empty();
				})
				.map(String::toLowerCase)
				.subscribe(
						this::showFromSuscribe
				);


	}

	public void ejemploFlatMap() throws Exception {

		List<String> usuariosList = new ArrayList<>();
		usuariosList.add("Luis Cervantes");
		usuariosList.add("Dayana Perez");
		usuariosList.add("Alex Peñaranda");
		usuariosList.add("Oswaldo Jimenez");
		usuariosList.add("Dora Vallejo");
		usuariosList.add("Piedad Bolivar");

		Flux.fromIterable(usuariosList)
				.map(this::toUpperCase)
				.flatMap(usuario -> {
					if (filterNombrePorLetras(usuario, "l")){
						return Mono.just(usuario);
					}
					return Mono.empty();
				})
				.map(this::toLowerCase)
				.subscribe(
						this::showFromSuscribeFluxUsuario
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
