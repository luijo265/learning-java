package com.aprendekomodo.springboot.webflux.app.controllers;

import com.aprendekomodo.springboot.webflux.app.models.dao.ProductoDao;
import com.aprendekomodo.springboot.webflux.app.models.documents.Producto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;

@Controller
public class ProductoController {

    @Autowired
    private ProductoDao dao;

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @GetMapping({"/listar", "/"})
    public String listar(Model model){
        Flux<Producto> productos = dao.findAll()
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                });

        productos.subscribe(producto -> logger.info(producto.getNombre()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "listado de productos");
        return "listar";
    }

    @GetMapping({"/listar-datedriver"})
    public String listarDataDriver(Model model){
        Flux<Producto> productos = dao.findAll()
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                })
                .delayElements(Duration.ofSeconds(1));

        productos.subscribe(producto -> logger.info(producto.getNombre()));

        model.addAttribute("productos",new ReactiveDataDriverContextVariable(productos, 1));
        model.addAttribute("titulo", "listado de productos");
        return "listar";
    }

    @GetMapping({"/listar-full"})
    public String listarFull(Model model){
        Flux<Producto> productos = dao.findAll()
                .map(producto -> {
                    producto.setNombre(producto.getNombre().toUpperCase());
                    return producto;
                }).repeat(1000);

        productos.subscribe(producto -> logger.info(producto.getNombre()));

        model.addAttribute("productos", productos);
        model.addAttribute("titulo", "listado de productos");
        return "listar";
    }

}
