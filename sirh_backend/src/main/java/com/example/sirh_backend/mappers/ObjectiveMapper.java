package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.ObjectiveDTO;
import com.example.sirh_backend.models.entities.Objective;

import java.util.List;

public class ObjectiveMapper {

    private ObjectiveMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static ObjectiveDTO toDTO(Objective objective) {
        if (objective != null) {
            return new ObjectiveDTO(
                    objective.getId(),
                    objective.getDescription(),
                    objective.isAchieved(),
                    objective.getEvaluation() != null ? objective.getEvaluation().getId() : null
            );
        }
        return null;
    }

    public static List<ObjectiveDTO> toDTO(List<Objective> objectives) {
        return objectives.stream()
                .map(ObjectiveMapper::toDTO)
                .toList();
    }
}
