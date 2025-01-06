package fer.or.api.service;

import fer.or.api.model.Designer;
import fer.or.api.repository.DesignerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DesignerService {
    @Autowired
    private final DesignerRepo designerRepo;

    public DesignerService(DesignerRepo designerRepo, DesignerRepo designerRepo1) {
        this.designerRepo = designerRepo1;
    }

    public List<Designer> getAllDesigners() {
        return (List<Designer>) designerRepo.findAll();
    }

    public Optional<Designer> getDesignerById(int id) {
        return designerRepo.findById(id);
    }

    public List<Designer> getAllDesignersByIds(List<Integer> designerIds) {
        return (List<Designer>) designerRepo.findAllById(designerIds);
    }
}
