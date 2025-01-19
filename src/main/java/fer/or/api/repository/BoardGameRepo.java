package fer.or.api.repository;

import fer.or.api.model.BoardGame;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardGameRepo extends CrudRepository<BoardGame, Integer> {
}
