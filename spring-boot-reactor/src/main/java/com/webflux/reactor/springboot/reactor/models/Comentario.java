package com.webflux.reactor.springboot.reactor.models;

import java.util.LinkedList;
import java.util.List;

public class Comentario {

    private List<String> comentarios;

    public Comentario(List<String> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario() {
        comentarios = new LinkedList<>();
    }

    public List<String> getComentarios() {
        return comentarios;
    }

    public void addComentario(String comentario) {
        this.comentarios.add(comentario);
    }

    @Override
    public String toString() {
        return "comentarios=" + comentarios;
    }
}
