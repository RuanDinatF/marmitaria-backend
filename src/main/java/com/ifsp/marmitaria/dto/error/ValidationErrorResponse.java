package com.ifsp.marmitaria.dto.error;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ValidationErrorResponse extends ErrorResponse {
    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(int status, String message, Map<String, String> fieldErrors) {
        super(status, message);
        this.fieldErrors = fieldErrors;
    }
}
