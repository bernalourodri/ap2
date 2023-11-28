package com.ap1.crudap1.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ap1.crudap1.exception.BusinessException;

@ControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse validationerrorHandler(MethodArgumentNotValidException e){
        ValidationErrorResponse errors = new ValidationErrorResponse();

        for (FieldError error : e.getBindingResult().getFieldErrors()){
            errors.addErrorValidation(error.getField(),error.getDefaultMessage());
        }

        return errors;
    }
    


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse businessExceptionHandler(BusinessException e){
        ValidationErrorResponse errors = new ValidationErrorResponse();

        errors.getBusinessErrors().add(new BusinessError(e.getClass().getSimpleName(), e.getMessage()));
        
        return errors;
    }
    



}
