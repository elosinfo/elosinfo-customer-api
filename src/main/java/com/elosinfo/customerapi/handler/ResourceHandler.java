package com.elosinfo.customerapi.handler;

import com.elosinfo.customerapi.exception.CustomerException;
import com.elosinfo.customerapi.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice //interceptor de exceções
public class ResourceHandler {

    @ExceptionHandler(value = CustomerException.class)
    public ResponseEntity<ErrorResponse> handlerInvalidOperationException(CustomerException customerException){
        ErrorResponse.ErrorResponseBuilder error = ErrorResponse.builder();
        error.httpStatus(customerException.getHttpStatus().value());
        error.message(customerException.getMessage());
        error.timestamp(System.currentTimeMillis());
        error.xtid(customerException.getXtid());
        return ResponseEntity.status(customerException.getHttpStatus()).body(error.build());
    }
}
