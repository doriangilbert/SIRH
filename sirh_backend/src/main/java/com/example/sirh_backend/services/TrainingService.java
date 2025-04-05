package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.TrainingDTO;
import com.example.sirh_backend.models.Employee;
import com.example.sirh_backend.models.Skill;
import com.example.sirh_backend.models.Training;
import com.example.sirh_backend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<TrainingDTO> getAllTrainings() {
        return trainingRepository.findAll().stream()
                .map(training -> new TrainingDTO(
                        training.getId(),
                        training.getName(),
                        training.getDescription(),
                        training.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()),
                        training.getSkills().stream().map(Skill::getId).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());
    }

    public TrainingDTO getTrainingById(long id) {
        Training training = trainingRepository.findById(id).orElse(null);
        if (training != null) {
            return new TrainingDTO(
                    training.getId(),
                    training.getName(),
                    training.getDescription(),
                    training.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()),
                    training.getSkills().stream().map(Skill::getId).collect(Collectors.toList())
            );
        }
        return null;
    }

    public Training createTraining(Training training) {
        return trainingRepository.save(training);
    }
}
