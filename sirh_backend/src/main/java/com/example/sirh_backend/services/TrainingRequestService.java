package com.example.sirh_backend.services;

import com.example.sirh_backend.models.entities.TrainingRequest;
import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.repositories.TrainingRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingRequestService {

    private final TrainingRequestRepository trainingRequestRepository;
    private final NotificationService notificationService;

    public TrainingRequestService(TrainingRequestRepository trainingRequestRepository, NotificationService notificationService) {
        this.trainingRequestRepository = trainingRequestRepository;
        this.notificationService = notificationService;
    }

    public List<TrainingRequest> getAllTrainingRequests() {
        return trainingRequestRepository.findAll();
    }

    public TrainingRequest getTrainingRequestById(long id) {
        return trainingRequestRepository.findById(id).orElse(null);
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
                trainingRequest.notifyObservers(
                        "A training request has been approved.\n" +
                        "Details:\n" +
                        "- Training Request ID: " + trainingRequest.getId() + "\n" +
                        "- Training ID: " + trainingRequest.getTraining().getId() + "\n" +
                        "- Training Name: " + trainingRequest.getTraining().getName()
                );
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
                trainingRequest.notifyObservers(
                        "A training request has been refused.\n" +
                        "Details:\n" +
                        "- Training Request ID: " + trainingRequest.getId() + "\n" +
                        "- Training ID: " + trainingRequest.getTraining().getId() + "\n" +
                        "- Training Name: " + trainingRequest.getTraining().getName()
                );
                return updateTrainingRequest(id, trainingRequest);
            } else {
                throw new IllegalStateException("Employee is not the reviewer");
            }
        } else {
            throw new IllegalStateException("Training request not found");
        }
    }
}
