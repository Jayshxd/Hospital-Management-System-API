package com.jayesh.hospitalmanagementsystemapi.authentication.role.repo;

import com.jayesh.hospitalmanagementsystemapi.authentication.role.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
