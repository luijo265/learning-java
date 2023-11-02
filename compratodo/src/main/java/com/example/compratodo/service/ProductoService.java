package com.example.compratodo.service;

import com.example.compratodo.model.Producto;
import com.example.compratodo.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final Logger logger = LoggerFactory.getLogger(ProductoService.class);

    private final ProductoRepository repository;

    public Mono<Producto> crearProducto(Producto producto){

        return repository.save(producto)
                .onErrorResume(throwable -> {
                    logger.info("error molesto al crear producto", throwable);
                    return Mono.empty();
                });

    }

}
