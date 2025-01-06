package fer.or.api.service;

import fer.or.api.model.Artist;
import fer.or.api.repository.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistService {
    @Autowired
    private final ArtistRepo artistRepo;

    public ArtistService(ArtistRepo artistRepo) {
        this.artistRepo = artistRepo;
    }

    public List<Artist> getAllArtists() {
        return (List<Artist>) artistRepo.findAll();
    }

    public Optional<Artist> getArtistById(int id) {
        return artistRepo.findById(id);
    }

    public List<Artist> getAllArtistsByIds(List<Integer> artistIds) {
        return (List<Artist>) artistRepo.findAllById(artistIds);
    }
}
