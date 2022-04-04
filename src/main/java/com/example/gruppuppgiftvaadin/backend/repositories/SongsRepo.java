package com.example.gruppuppgiftvaadin.backend.repositories;

import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongsRepo extends JpaRepository<Songs, Integer> {

    @Query("select s from Songs s "+
            "where lower(s.songName) like lower(concat('%', :searchTerm, '%'))")
    List<Songs> search(@Param("searchTerm")String searchTerm);

}
