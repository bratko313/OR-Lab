package fer.or.api.repository;

import fer.or.api.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends CrudRepository<Artist, Integer> {
}
