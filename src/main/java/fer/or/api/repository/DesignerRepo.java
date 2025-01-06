package fer.or.api.repository;

import fer.or.api.model.Designer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignerRepo extends CrudRepository<Designer, Integer> {
}
