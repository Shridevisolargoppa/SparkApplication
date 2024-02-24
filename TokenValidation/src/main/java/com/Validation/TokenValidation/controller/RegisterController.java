package com.Validation.TokenValidation.controller;

import com.Validation.TokenValidation.model.AuthenticationException;
import com.Validation.TokenValidation.model.Register;
import com.Validation.TokenValidation.model.ResponseBody;
import com.Validation.TokenValidation.model.UserCredentials;
import com.Validation.TokenValidation.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<Register> create(@RequestBody Register register) {
        Register registerUser = registerService.create(register);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @PostMapping("/generate-token")
    public ResponseEntity<String> generateToken(@RequestBody UserCredentials credentials) {
        try {
            String authToken = registerService.generateToken(credentials);
            return ResponseEntity.ok(authToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/validateToken")
    public ResponseEntity<ResponseBody> validate(@RequestHeader("Authorization") String token) throws Exception {

        String authToken = token.replace("Bearer ", "");
        boolean generatedtoken = registerService.validateToken(authToken);
//        try {
            HttpStatus status = generatedtoken ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
            String body = generatedtoken ? "valid token" : "Invalid token";
//            if(generatedtoken)
//                return new ResponseEntity<>("Given token is valid",HttpStatus.ACCEPTED);

            ResponseBody responseBody = new ResponseBody(body, status);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//        return new ResponseEntity<>("Invalid token", HttpStatus.NOT_ACCEPTABLE);
        return  ResponseEntity.ok(responseBody);
    }
}
