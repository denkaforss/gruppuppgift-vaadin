package com.example.gruppuppgiftvaadin.backend.services;

import com.example.gruppuppgiftvaadin.backend.entities.Songs;
import com.example.gruppuppgiftvaadin.backend.repositories.SongsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongsService {

    SongsRepo songsRepo;

    public SongsService(SongsRepo songsRepo) {
        this.songsRepo = songsRepo;
    }

    public List<Songs> findAll(){
        return songsRepo.findAll();
    }

    public List<Songs> findAll(String filter){
        if (filter == null || filter.isEmpty()) {
            return songsRepo.findAll();
        } else {
            return songsRepo.search(filter);
        }
    }

    public Songs saveSong(Songs song){
        return songsRepo.save(song);
    }

    public void deleteSong(Songs song) {
        songsRepo.delete(song);
    }

    public Songs updateSong(int id, Songs changedSong) {

        Songs existingSong = songsRepo.findById(id).orElseThrow();

        if (changedSong.getSongName() != null)
            existingSong.setSongName(changedSong.getSongName());
        if (changedSong.getSongLength() != 0)
            existingSong.setSongLength(changedSong.getSongLength());

        songsRepo.save(existingSong);

        return existingSong;

    }
}
