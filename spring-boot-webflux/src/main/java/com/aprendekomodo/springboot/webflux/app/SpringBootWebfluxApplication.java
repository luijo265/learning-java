package com.aprendekomodo.springboot.webflux.app;

import com.aprendekomodo.springboot.webflux.app.models.dao.ProductoDao;
import com.aprendekomodo.springboot.webflux.app.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class SpringBootWebfluxApplication implements CommandLineRunner {

	@Autowired
	private ProductoDao dao;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	private static final Logger logger = LoggerFactory.getLogger(SpringBootWebfluxApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWebfluxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		mongoTemplate.dropCollection("productos").subscribe();

		Flux.just(new Producto("TV One", 456.89), new Producto("Pony Station", 500.0))
				.map(producto -> {
					producto.setCreateAt(new Date());
					return producto;
				})
				.flatMap(producto -> dao.save(producto))
				.subscribe(producto -> logger.info("Insert: "+producto.getId()+", nombre: "+producto.getNombre()));

	}
}
