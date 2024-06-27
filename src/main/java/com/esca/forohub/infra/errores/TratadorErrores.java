package com.esca.forohub.infra.errores;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorErrores {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404(EntityNotFoundException e) {
        var error = e.getLocalizedMessage();
        return ResponseEntity.status(404).body(error);
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
