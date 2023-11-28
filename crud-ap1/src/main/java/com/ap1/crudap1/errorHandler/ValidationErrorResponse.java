package com.ap1.crudap1.errorHandler;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private String errorMessage = "Aconteceu um problema ao processar sua solicitação";
    private List<Validation> validationErrors = new ArrayList<Validation>();
     private List<BusinessError> businessErrors = new ArrayList<BusinessError>();

    public List<Validation> getValidationErrors() {
        return this.validationErrors;
    }

    public void setValidationErrors(List<Validation> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<BusinessError> getBusinessErrors() {
        return this.businessErrors;
    }

    public void setBusinessErrors(List<BusinessError> businessErrors) {
        this.businessErrors = businessErrors;
    }

    public List<Validation> getErrors() {
        return validationErrors;
    }

    public void setErrors(List<Validation> errors) {
        this.validationErrors = errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    
    public void addErrorValidation(String field, String message){
        this.validationErrors.add(new Validation(field, message));
    }

}
