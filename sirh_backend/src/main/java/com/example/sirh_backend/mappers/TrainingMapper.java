package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TrainingDTO;
import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.models.entities.Training;

import java.util.List;

public class TrainingMapper {

    private TrainingMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static TrainingDTO toDTO(Training training) {
        if (training != null) {
            return new TrainingDTO(
                    training.getId(),
                    training.getName(),
                    training.getDescription(),
                    training.getSkills() != null ? training.getSkills().stream().map(Skill::getId).toList() : List.of()
            );
        }
        return null;
    }

    public static List<TrainingDTO> toDTO(List<Training> trainings) {
        return trainings.stream()
                .map(TrainingMapper::toDTO)
                .toList();
    }
}
