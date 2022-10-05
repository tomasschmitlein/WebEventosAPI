package com.schmitlein.DTO;

import com.schmitlein.validations.NroTel;
import javax.validation.constraints.*;

public class AdminEventosDTO {
    
    private long id;
    
    @NotEmpty(message = "El campo nombre no puede estar VACIO!")
    private String nombre;
    
    @Email(message = "No respeto el formato de email, por favor vuelva a escribirlo")
    @NotEmpty(message = "El email es obligatorio")
    private String email;
    
    @NotEmpty
    @Size(max = 15, message = "Supero el limite de caracteres disponibles(15")
    @NroTel
    private String celular;

    public AdminEventosDTO() {
    }

    public AdminEventosDTO(long id, String nombre, String email, String celular) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.celular = celular;
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

    @Override
    public String toString() {
        return "AdminEventosDTO{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", celular=" + celular + '}';
    }
    
    
    
}
