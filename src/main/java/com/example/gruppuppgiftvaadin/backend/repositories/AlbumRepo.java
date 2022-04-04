package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Integer> {

    @Query("select a from Album a "+
            "where lower(a.albumName) like lower(concat('%', :searchTerm, '%'))")
    List<Album> search(@Param("searchTerm")String searchTerm);


}
