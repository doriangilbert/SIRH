package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.ObjectiveDTO;
import com.example.sirh_backend.models.entities.Objective;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ObjectiveMapper {

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
                .collect(Collectors.toList());
    }
}
