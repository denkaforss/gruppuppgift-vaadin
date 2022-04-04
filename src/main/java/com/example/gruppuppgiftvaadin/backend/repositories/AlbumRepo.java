package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {
}
