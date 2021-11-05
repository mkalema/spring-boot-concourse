package com.gremo.springbootconcourse.books.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request){
        Map<String, Object> fieldError = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError error: fieldErrors){
            fieldError.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("isSuccess", false);
        map.put("data", null);
        map.put("status", HttpStatus.BAD_REQUEST);
        map.put("fieldError", fieldError);
        return handleExceptionInternal(ex, map, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NotFoundException.class})
    protected ResponseEntity<?> handleNotFoundException(NotFoundException ex,
                                                             WebRequest request){

        return handleExceptionInternal(ex, new CustomError(ex.getMessage()), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
