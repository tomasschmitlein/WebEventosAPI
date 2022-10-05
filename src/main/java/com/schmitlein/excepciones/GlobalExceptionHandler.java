package com.schmitlein.excepciones;

import com.schmitlein.DTO.ErrorDetalles;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//esta clase, controller va a manejar todos nuestras excepciones

@ControllerAdvice//le va a permitir manejar las todas excepciones(handlers), no maneja los errores de 1 controller, sino de tooodos los errores de la app
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    //esta anotacion se va a encargar de manejar las excepciones que se hayan detallado, este metodo se va a encargar de los notFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetalles> manejarResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetalles, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(WebAppException.class)
    public ResponseEntity<ErrorDetalles> manejarWebAppException(WebAppException exception, WebRequest webRequest){
        
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetalles, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetalles> manejarGlobalException(Exception exception, WebRequest webRequest){
        
        ErrorDetalles errorDetalles = new ErrorDetalles(new Date(), exception.getMessage(), webRequest.getDescription(false));
        
        return new ResponseEntity<>(errorDetalles, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //este metodo que se hereda de la clase ResponseEntityExceptionHandler, cuando algo q envie no es valido nos lo va a manejar como una excepcion
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    
        Map<String, String> errores = new HashMap<>();
        
        //este metodo obtiene todos los errores y luego los recorremos 1 x 1
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            
            //obtenemos el nombre del campo que nos esta tirando error
            String nombreCampo = ((FieldError)error).getField();
            
            //obtenemos el mensaje del error de ese campo
            String mensaje = error.getDefaultMessage();
            
            //guardamos el campo y su mensaje en el map, llave-valor
            errores.put(nombreCampo, mensaje);
        });
        
         return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
    }
    
}
