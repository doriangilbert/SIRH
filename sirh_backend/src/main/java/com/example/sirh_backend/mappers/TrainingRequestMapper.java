package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.models.TrainingRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrainingRequestMapper {

    public static TrainingRequestDTO toDTO(TrainingRequest trainingRequest) {
        if (trainingRequest != null) {
            return new TrainingRequestDTO(
                    trainingRequest.getId(),
                    trainingRequest.getStatus().toString(),
                    trainingRequest.getTraining().getId(),
                    trainingRequest.getEmployee().getId(),
                    trainingRequest.getReviewer().getId()
            );
        }
        return null;
    }

    public static List<TrainingRequestDTO> toDTO(List<TrainingRequest> trainingRequests) {
        return trainingRequests.stream()
                .map(TrainingRequestMapper::toDTO)
                .collect(Collectors.toList());
    }
}
