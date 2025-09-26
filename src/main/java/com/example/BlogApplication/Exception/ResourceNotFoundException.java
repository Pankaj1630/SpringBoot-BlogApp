package com.example.BlogApplication.Exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{
    private String resourceName;
    private String resourceId;
    private int fieldValue;


    public ResourceNotFoundException(String resourceName, String resourceId, int fieldValue) {
        super(String.format("%s not found with %s : %d",resourceName,resourceId,fieldValue));
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.fieldValue = fieldValue;
    }

}
