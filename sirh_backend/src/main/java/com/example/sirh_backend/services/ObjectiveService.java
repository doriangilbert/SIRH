package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Objective;
import com.example.sirh_backend.repositories.ObjectiveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectiveService {

    private final ObjectiveRepository objectiveRepository;

    public ObjectiveService(ObjectiveRepository objectiveRepository) {
        this.objectiveRepository = objectiveRepository;
    }

    public List<Objective> getAllObjectives() {
        return objectiveRepository.findAll();
    }

    public Objective getObjectiveById(long id) {
        return objectiveRepository.findById(id).orElse(null);
    }

    public Objective createObjective(Objective objective) {
        return objectiveRepository.save(objective);
    }

    public Objective updateObjective(long id, Objective updatedObjective) {
        Objective objective = objectiveRepository.findById(id).orElse(null);
        if (objective != null) {
            objective.setDescription(updatedObjective.getDescription());
            objective.setAchieved(updatedObjective.isAchieved());
            objective.setEvaluation(updatedObjective.getEvaluation());
            return objectiveRepository.save(objective);
        }
        return null;
    }
}
