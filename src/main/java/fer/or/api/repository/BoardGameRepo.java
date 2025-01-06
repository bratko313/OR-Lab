package fer.or.api.repository;

import fer.or.api.model.BoardGame;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameRepo extends CrudRepository<BoardGame, Integer> {
}
