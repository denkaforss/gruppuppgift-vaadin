package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
}
