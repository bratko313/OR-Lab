package fer.or.api.service;

import fer.or.api.model.Franchise;
import fer.or.api.repository.FranchiseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FranchiseService {
    @Autowired
    private final FranchiseRepo franchiseRepo;

    public FranchiseService(FranchiseRepo franchiseRepo) {
        this.franchiseRepo = franchiseRepo;
    }

    public List<Franchise> getAllFranchises() {
        return (List<Franchise>) franchiseRepo.findAll();
    }

    public Optional<Franchise> getFranchiseById(int id) {
        return franchiseRepo.findById(id);
    }
}
