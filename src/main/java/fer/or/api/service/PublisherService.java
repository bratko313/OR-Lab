package fer.or.api.service;

import fer.or.api.model.Publisher;
import fer.or.api.repository.PublisherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublisherService {
    @Autowired
    private final PublisherRepo publisherRepo;

    public PublisherService(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    public List<Publisher> getAllPublishers() {
        return (List<Publisher>) publisherRepo.findAll();
    }

    public Optional<Publisher> getPublisherById(int id) {
        return publisherRepo.findById(id);
    }
}
