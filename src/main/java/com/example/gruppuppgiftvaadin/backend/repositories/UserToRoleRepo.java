package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.UserToRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToRoleRepo extends JpaRepository<UserToRole, Integer> {
}
