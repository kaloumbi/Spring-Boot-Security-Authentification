package com.abs.SpringSecurityJWT.myExeptions;


import com.abs.SpringSecurityJWT.dto.GlobalException.DataResponse;
import com.abs.SpringSecurityJWT.enums.CotisationError;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler {

    @ExceptionHandler(MyNotFoundExceptionClass.class)
    public ResponseEntity<DataResponse<String>> handleUserNotFoundException(MyNotFoundExceptionClass ex){
        log.info("Exception capturée: {} ", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new DataResponse<>(CotisationError.NOT_FOUND.getCode(), ex.getMessage(), false, null));
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<DataResponse<String>> handleDatabaseException(DatabaseException ex){
        log.info("Erreur de base de donnée : {} ", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DataResponse<>(CotisationError.INTERNAL_SERVER_ERROR.getCode(), ex.getMessage(), false, null));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<DataResponse<String>> handleDatabaseException(Exception ex){
        log.info("Erreur de base de donnée : {} ", ex.getMessage(), ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new DataResponse<>(CotisationError.INTERNAL_SERVER_ERROR.getCode(), "Erreur interne du serveur !!!", false, null));
    }

}
