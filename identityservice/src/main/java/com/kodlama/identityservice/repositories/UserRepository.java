package com.kodlama.identityservice.repositories;

import com.kodlama.identityservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>
{
    //@Query()
    Optional<User> findByEmail(String email);
}
