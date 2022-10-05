package com.schmitlein.DTO;

import com.schmitlein.entidades.AdministradoresEvento;
import java.util.Set;
import javax.validation.constraints.*;

public class PublicacionDTO {

    private Long id;
    
    @NotEmpty
    @Size(min = 2, message = "El titulo de la publicacion no cumple con la cantidad de caracteres que se necesitan(MÍN 2)")
    private String titulo;

    @NotEmpty
    @Size(min = 10, message = "El contenido de la publicacion no cumple con la cantidad de caracteres que se necesitan(MÍN 10)")
    private String contenido;

    @NotEmpty
    @Size(min = 5, max= 140, message = "La descripcion de la publicacion no cumple con la cantidad de caracteres que se necesitan(MÍN 5)")
    private String descripcion;
    
    private Set<AdministradoresEvento> Administradores;

    public PublicacionDTO(Long id, String titulo, String contenido, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.contenido = contenido;
        this.descripcion = descripcion;
    }

    public PublicacionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<AdministradoresEvento> getAdministradores() {
        return Administradores;
    }

    public void setAdministradores(Set<AdministradoresEvento> Administradores) {
        this.Administradores = Administradores;
    }
    
}
