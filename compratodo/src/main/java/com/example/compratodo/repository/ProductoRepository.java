package com.example.compratodo.repository;

import com.example.compratodo.model.Producto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends R2dbcRepository<Producto, String> {


}
