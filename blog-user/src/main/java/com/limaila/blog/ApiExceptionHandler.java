package com.limaila.blog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * 当@RequestParam参数为required=true，没传参数就抛出MissingServletRequestParameterException异常
     * @param ex
     * @return
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    public String onMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
       return ex.getMessage();
    }
}
