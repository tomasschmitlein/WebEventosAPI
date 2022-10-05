package com.schmitlein.entidades;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "admEventos")
public class AdministradoresEvento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String nombre;

    @NotNull
    private String email;

    @NotNull
    private String celular;

    //Muchos a uno, porque un evento/boliche va a poder tener muchos admin 
    @ManyToOne(fetch = FetchType.LAZY) //con lazy le decimos que solo nos traiga todo estos valores asociados cuando le pidamos
    @JoinColumn(name = "publicacion_id", nullable = false)
    private Publicacion publicacion;

    public AdministradoresEvento() {
    }

    public AdministradoresEvento(long id, String nombre, String email, String celular, Publicacion publicacion) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
        this.publicacion = publicacion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    @Override
    public String toString() {
        return "AdministradoresEvento{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", celular=" + celular + ", publicacion=" + publicacion + '}';
    }

}
