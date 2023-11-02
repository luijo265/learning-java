package com.example.compratodo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Data
public class Producto {

    @Id
    private String serial;
    private String nombre;
    private String precio;

}
