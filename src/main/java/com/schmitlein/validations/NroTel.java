package com.schmitlein.validations;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NroTelefonicoValidacion.class)// futura clase que contendra la logica de la validacion;
@Target({ElementType.METHOD, ElementType.FIELD})//destino de nuestra validacion a metodos o campos;
@Retention(RetentionPolicy.RUNTIME)//cuando se va a chequear si la anotacion se cumple o no se cumple, en este caso, en ejecucion, cuando el usuario llena el campo;
public @interface NroTel {

    //definir la caracteristica de telefono por defecto
    public String value() default "3434";

    //definir mensaje de error 
    String message() default "Numero de telefono invalido, la caracteristica debe empezar con '3434...'";

    //definir los grupos
    Class<?>[] groups() default {}; // es para ir validando el formulario x pasos y x grupos, se pueden determinar los grupos, por ej un grupo lo forman cuadro txt nombre y apellido, otro grupo email y telefono, etc;

    //definir los payloads
    Class<? extends Payload>[] payload() default {};//contiene los datos que pueden ser almacenados o actualizados, se usa mucho para metadatos;

}
