package fer.or.api.repository;

import fer.or.api.model.Franchise;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FranchiseRepo extends CrudRepository<Franchise, Integer> {
}
