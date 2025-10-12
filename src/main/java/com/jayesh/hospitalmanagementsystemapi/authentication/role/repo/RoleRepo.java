package com.jayesh.hospitalmanagementsystemapi.authentication.role.repo;

import com.jayesh.hospitalmanagementsystemapi.authentication.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
}
