package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.UserPrivilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPrivRepo extends JpaRepository<UserPrivilege, Integer> {
}
