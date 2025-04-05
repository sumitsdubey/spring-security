package com.sumit.tableserve_backend.repositories;

import com.sumit.tableserve_backend.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long> {
    Optional<User> findByEmail(String username);
}
