package com.Validation.TokenValidation.service;

import com.Validation.TokenValidation.model.AuthenticationException;
import com.Validation.TokenValidation.model.Register;
import com.Validation.TokenValidation.model.UserCredentials;
import com.Validation.TokenValidation.repository.RegisterRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.function.Function;

@Service
public class RegisterService {

    @Autowired
    RegisterRepository registerRepository;

@Autowired
JwtTokenService jwtTokenService;
    public Register create(Register register) {
        register.setPassword(doHashing(register.getPassword()));
        return registerRepository.save(register);
    }

    private String doHashing(String password) {
        StringBuilder stringBuilder = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            stringBuilder = new StringBuilder();
            for (byte b : resultByteArray) {
                stringBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);
    }

    public String generateToken(UserCredentials userCredentials) throws AuthenticationException {
        String username = userCredentials.getName();
        String password = userCredentials.getPassword();

        Register register = registerRepository.findByName(username);
        if (register != null && doHashing(password).equals(register.getPassword())) {
            return jwtTokenService.token(username);
        } else {
            throw new AuthenticationException("Invalid credentials");
        }
    }
    public Boolean validateToken(String token) throws Exception {

        final String username = getUsernameFromToken(token);
        Register register = registerRepository.findByName(username);
        return (username.equals(register.getName()));

    }
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    @Value("${jwt.secretKey}")
    private String secretKey;
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
}
