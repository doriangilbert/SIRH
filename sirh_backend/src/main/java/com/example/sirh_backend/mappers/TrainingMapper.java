package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TrainingDTO;
import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.models.entities.Training;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingMapper {

    public static TrainingDTO toDTO(Training training) {
        if (training != null) {
            return new TrainingDTO(
                    training.getId(),
                    training.getName(),
                    training.getDescription(),
                    training.getSkills().stream().map(Skill::getId).collect(Collectors.toList())
            );
        }
        return null;
    }

    public static List<TrainingDTO> toDTO(List<Training> trainings) {
        return trainings.stream()
                .map(TrainingMapper::toDTO)
                .collect(Collectors.toList());
    }
}
