package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.models.entities.TrainingRequest;

import java.util.List;

public class TrainingRequestMapper {

    private TrainingRequestMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static TrainingRequestDTO toDTO(TrainingRequest trainingRequest) {
        if (trainingRequest != null) {
            return new TrainingRequestDTO(
                    trainingRequest.getId(),
                    trainingRequest.getStatus() != null ? trainingRequest.getStatus().toString() : null,
                    trainingRequest.getTraining() != null ? trainingRequest.getTraining().getId() : null,
                    trainingRequest.getEmployee() != null ? trainingRequest.getEmployee().getId() : null,
                    trainingRequest.getReviewer() != null ? trainingRequest.getReviewer().getId() : null
            );
        }
        return null;
    }

    public static List<TrainingRequestDTO> toDTO(List<TrainingRequest> trainingRequests) {
        return trainingRequests.stream()
                .map(TrainingRequestMapper::toDTO)
                .toList();
    }
}
