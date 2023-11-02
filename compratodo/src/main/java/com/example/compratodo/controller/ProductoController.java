package com.example.compratodo.controller;

import com.example.compratodo.model.Producto;
import com.example.compratodo.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService service;

    @PostMapping
    public Mono<ResponseEntity<String>> crearProducto(@RequestBody Producto producto){
        return service.crearProducto(producto)
                .flatMap(productoResponse -> Mono.just(ResponseEntity.status(HttpStatus.CREATED).body("Producto creado")))
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Producto no creado creado")));
    }

}
