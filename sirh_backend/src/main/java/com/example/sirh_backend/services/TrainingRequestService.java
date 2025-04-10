package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.TrainingRequestDTO;
import com.example.sirh_backend.models.RequestStatus;
import com.example.sirh_backend.models.TrainingRequest;
import com.example.sirh_backend.repositories.TrainingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingRequestService {

    private final TrainingRequestRepository trainingRequestRepository;

    public TrainingRequestService(TrainingRequestRepository trainingRequestRepository) {
        this.trainingRequestRepository = trainingRequestRepository;
    }

    public List<TrainingRequestDTO> getAllTrainingRequests() {
        return trainingRequestRepository.findAll().stream()
                .map(trainingRequest -> new TrainingRequestDTO(
                        trainingRequest.getId(),
                        trainingRequest.getStatus().toString(),
                        trainingRequest.getTraining().getId(),
                        trainingRequest.getEmployee().getId(),
                        trainingRequest.getReviewer().getId()
                ))
                .toList();
    }

    public TrainingRequestDTO getTrainingRequestById(long id) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElse(null);
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

    public TrainingRequest createTrainingRequest(TrainingRequest trainingRequest) {
        return trainingRequestRepository.save(trainingRequest);
    }

    public TrainingRequest updateTrainingRequest(long id, TrainingRequest updatedTrainingRequest) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElse(null);
        if (trainingRequest != null) {
            trainingRequest.setStatus(updatedTrainingRequest.getStatus());
            trainingRequest.setTraining(updatedTrainingRequest.getTraining());
            trainingRequest.setEmployee(updatedTrainingRequest.getEmployee());
            trainingRequest.setReviewer(updatedTrainingRequest.getReviewer());
            return trainingRequestRepository.save(trainingRequest);
        }
        return null;
    }

    public TrainingRequest approveTrainingRequest(long id, long reviewerId) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElse(null);
        if (trainingRequest != null) {
            if (trainingRequest.getReviewer().getId() == reviewerId) {
                trainingRequest.setStatus(RequestStatus.APPROVED);
                return updateTrainingRequest(id, trainingRequest);
            } else {
                throw new IllegalStateException("Employee is not the reviewer");
            }
        } else {
            throw new IllegalStateException("Training request not found");
        }
    }

    public TrainingRequest refuseTrainingRequest(long id, long reviewerId) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElse(null);
        if (trainingRequest != null) {
            if (trainingRequest.getReviewer().getId() == reviewerId) {
                trainingRequest.setStatus(RequestStatus.REFUSED);
                return updateTrainingRequest(id, trainingRequest);
            } else {
                throw new IllegalStateException("Employee is not the reviewer");
            }
        } else {
            throw new IllegalStateException("Training request not found");
        }
    }
}
