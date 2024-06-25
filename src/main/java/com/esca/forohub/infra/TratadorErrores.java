package com.esca.forohub.infra;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class TratadorErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404() {
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity validacionDatosEntrada(MethodArgumentNotValidException e){
        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();
        return ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity validacionDatosSql (DataIntegrityViolationException e){
        var error=  new DatosErrorSql ( e.getRootCause());
        //var error = e.getRootCause();
        return ResponseEntity.badRequest().body(error);
    }

    public record DatosErrorSql(String error, String mensaje){
        DatosErrorSql(Throwable error){
            this(error.getMessage(), "Cambie el valor de entrada");
        }
    }

    public record DatosErrorValidacion(String campo, String error){
        DatosErrorValidacion(FieldError fieldError){
            this (fieldError.getField(), fieldError.getDefaultMessage());
        }
    }





}
