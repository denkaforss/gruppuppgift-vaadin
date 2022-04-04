package com.example.gruppuppgiftvaadin.backend.services;

import com.example.gruppuppgiftvaadin.backend.entities.Artist;
import com.example.gruppuppgiftvaadin.backend.repositories.ArtistRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    ArtistRepo artistRepo;

    public ArtistService(ArtistRepo artistRepo) {this.artistRepo = artistRepo;}

    public List<Artist> findAll() {return artistRepo.findAll(); }

    public List<Artist> findAll(String filter){
        if (filter == null || filter.isEmpty()) {
            return artistRepo.findAll();
        } else {
            return artistRepo.search(filter);
        }
    }

    public Artist saveArtist(Artist artist) {return artistRepo.save(artist);}

    public void deleteArtist(Artist artist) {artistRepo.delete(artist);}

    public Artist updateArtist(int id, Artist changedArtist) {

        Artist existingArtist = artistRepo.findById(id).orElseThrow();

        if (changedArtist.getArtistName() != null)
            existingArtist.setArtistName(changedArtist.getArtistName());
        if (changedArtist.getStartingYear() != null)
            existingArtist.setStartingYear(changedArtist.getStartingYear());
        if (changedArtist.getHomeCountry() != null)
            existingArtist.setHomeCountry(changedArtist.getHomeCountry());

        artistRepo.save(existingArtist);

        return existingArtist;

    }

}
