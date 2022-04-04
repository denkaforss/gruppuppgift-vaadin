package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongsRepo extends JpaRepository<Songs, Integer> {
}
