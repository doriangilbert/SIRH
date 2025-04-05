package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.ObjectiveDTO;
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

    public List<ObjectiveDTO> getAllObjectives() {
        return objectiveRepository.findAll().stream()
                .map(objective -> new ObjectiveDTO(
                        objective.getId(),
                        objective.getDescription(),
                        objective.isAchieved(),
                        objective.getEvaluation().getId()
                ))
                .toList();
    }

    public ObjectiveDTO getObjectiveById(long id) {
        Objective objective = objectiveRepository.findById(id).orElse(null);
        if (objective != null) {
            return new ObjectiveDTO(
                    objective.getId(),
                    objective.getDescription(),
                    objective.isAchieved(),
                    objective.getEvaluation().getId()
            );
        }
        return null;
    }

    public Objective createObjective(Objective objective) {
        return objectiveRepository.save(objective);
    }
}
