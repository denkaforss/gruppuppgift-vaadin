package com.example.gruppuppgiftvaadin.backend.services;

import com.example.gruppuppgiftvaadin.backend.entities.Album;
import com.example.gruppuppgiftvaadin.backend.repositories.AlbumRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {

    AlbumRepo albumRepo;

    public AlbumService(AlbumRepo albumRepo) {this.albumRepo = albumRepo;}

    public List<Album> findAll() {return albumRepo.findAll(); }

    public List<Album> findAll(String filter){
        if (filter == null || filter.isEmpty()) {
            return albumRepo.findAll();
        } else {
            return albumRepo.search(filter);
        }
    }

    public Album findAlbumById(int id) {
        return albumRepo.findById(id).orElseThrow();
    }

    public Album saveAlbum(Album album) {return albumRepo.save(album);}

    public void deleteAlbum(Album album) {albumRepo.delete(album);}

    public void deleteById(int id) {albumRepo.deleteById(id);}

    public Album updateAlbum(int id, Album changedAlbum) {

        Album existingAlbum = albumRepo.findById(id).orElseThrow();

        if (changedAlbum.getAlbumName() != null)
            existingAlbum.setAlbumName(changedAlbum.getAlbumName());
        if(changedAlbum.getArtistName() != null)
            existingAlbum.setArtistName(changedAlbum.getArtistName());
        if (changedAlbum.getReleaseYear() != null)
            existingAlbum.setReleaseYear(changedAlbum.getReleaseYear());

        albumRepo.save(existingAlbum);

        return existingAlbum;

    }

    public List<Album> findPostByAuthorUsername(String principalName) {
        return albumRepo.findByAppUser_Username(principalName);
    }
}
