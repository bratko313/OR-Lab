package fer.or.api.repository;

import fer.or.api.model.Publisher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepo extends CrudRepository<Publisher, Integer> {
}
