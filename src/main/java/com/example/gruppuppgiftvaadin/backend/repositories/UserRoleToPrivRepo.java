package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.UserRoleToPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleToPrivRepo extends JpaRepository<UserRoleToPrivilege, Integer> {
}
