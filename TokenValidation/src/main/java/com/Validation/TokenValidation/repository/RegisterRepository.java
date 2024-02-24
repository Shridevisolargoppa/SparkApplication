package com.Validation.TokenValidation.repository;

import com.Validation.TokenValidation.model.Register;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepository extends MongoRepository<Register,String> {
    Register findByName(String username);
}
