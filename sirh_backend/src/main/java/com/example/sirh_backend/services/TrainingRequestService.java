package com.example.sirh_backend.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.repositories.TrainingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingRequestService {

    private final TrainingRequestRepository trainingRequestRepository;

    public TrainingRequestService(TrainingRequestRepository trainingRequestRepository) {
        this.trainingRequestRepository = trainingRequestRepository;
    }

    public List<TrainingRequest> getAllTrainingRequests() {
        return trainingRequestRepository.findAll();
    }

    public TrainingRequest getTrainingRequestById(long id) {
        return trainingRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Training request not found"));
    }

    public TrainingRequest createTrainingRequest(TrainingRequest trainingRequest) {
        return trainingRequestRepository.save(trainingRequest);
    }

    public TrainingRequest updateTrainingRequest(long id, TrainingRequest updatedTrainingRequest) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Training request not found"));
        if (updatedTrainingRequest == null) {
            throw new IllegalArgumentException("Updated training request data cannot be null");
        }
        trainingRequest.setStatus(updatedTrainingRequest.getStatus());
        trainingRequest.setTraining(updatedTrainingRequest.getTraining());
        trainingRequest.setEmployee(updatedTrainingRequest.getEmployee());
        trainingRequest.setReviewer(updatedTrainingRequest.getReviewer());
        return trainingRequestRepository.save(trainingRequest);
    }

    public TrainingRequest approveTrainingRequest(long id, long reviewerId) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Training request not found"));
        if (trainingRequest.getReviewer().getId() == reviewerId) {
            trainingRequest.setStatus(RequestStatus.APPROVED);
            trainingRequest.notifyObservers(
                    "A training request has been approved.\n" +
                    "Details:\n" +
                    "- Training Request ID: " + trainingRequest.getId() + "\n" +
                    "- Training ID: " + trainingRequest.getTraining().getId() + "\n" +
                    "- Training Name: " + trainingRequest.getTraining().getName()
            );
            return updateTrainingRequest(id, trainingRequest);
        } else {
            throw new IllegalArgumentException("Employee is not the reviewer");
        }
    }

    public TrainingRequest refuseTrainingRequest(long id, long reviewerId) {
        TrainingRequest trainingRequest = trainingRequestRepository.findById(id).orElseThrow(() -> new NotFoundException("Training request not found"));
        if (trainingRequest.getReviewer().getId() == reviewerId) {
            trainingRequest.setStatus(RequestStatus.REFUSED);
            trainingRequest.notifyObservers(
                    "A training request has been refused.\n" +
                    "Details:\n" +
                    "- Training Request ID: " + trainingRequest.getId() + "\n" +
                    "- Training ID: " + trainingRequest.getTraining().getId() + "\n" +
                    "- Training Name: " + trainingRequest.getTraining().getName()
            );
            return updateTrainingRequest(id, trainingRequest);
        } else {
            throw new IllegalArgumentException("Employee is not the reviewer");
        }
    }
}
