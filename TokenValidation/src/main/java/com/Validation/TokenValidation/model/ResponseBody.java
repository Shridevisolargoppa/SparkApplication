package com.Validation.TokenValidation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseBody {

    String body;

    HttpStatus status;

    public ResponseBody(String body, HttpStatus status) {
        this.body = body;
        this.status = status;
    }
}
