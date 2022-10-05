package com.schmitlein.entidades;

import java.util.*;
import javax.persistence.*;

@Entity
//no se pueden repetir los username y los email
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"username"}),
    @UniqueConstraint(columnNames = {"email"})})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nombre;

    private String username;

    private String email;

    private String password;

    //eagger porque nos va a traer siempre todos los roles que tenga el usuario
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //joinTable --> voy a unir las tablas rol y usuario en una nva "usuarios_roles"
    //joinColumns --> voy a unir columnas en esa tabla, en la cual el "usuario_id" va a estar referenciado al id de esta tabla Usuario
    //inverseJoinColumn --> joincolumns voy a unir la columna en la cual "rol_id" va esta referenciado al "id" de la tabla Rol
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    public Usuario() {
    }
    

}
