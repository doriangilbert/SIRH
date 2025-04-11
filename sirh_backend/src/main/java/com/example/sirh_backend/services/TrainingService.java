package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Training;
import com.example.sirh_backend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    public Training getTrainingById(long id) {
        return trainingRepository.findById(id).orElse(null);
    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }

    public Training updateTraining(long id, Training updatedTraining) {
        Training training = trainingRepository.findById(id).orElse(null);
        if (training != null) {
            training.setName(updatedTraining.getName());
            training.setDescription(updatedTraining.getDescription());
            training.setSkills(updatedTraining.getSkills());
            return trainingRepository.save(training);
        }
        return null;
    }
}
