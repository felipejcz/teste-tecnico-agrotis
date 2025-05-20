package agrotis.com.teste_backend_java.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import agrotis.com.teste_backend_java.exception.AgrotisException;

@RestControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(AgrotisException.class)
    public ProblemDetail handleAgrotisException(AgrotisException e) {
        return e.toProblemDetail();
    }
}
