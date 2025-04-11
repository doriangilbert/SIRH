package com.example.sirh_backend.services;

import com.example.sirh_backend.models.Position;
import com.example.sirh_backend.repositories.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    public Position getPositionById(long id) {
        return positionRepository.findById(id).orElse(null);
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePosition(long id, Position updatedPosition) {
        Position position = positionRepository.findById(id).orElse(null);
        if (position != null) {
            position.setName(updatedPosition.getName());
            return positionRepository.save(position);
        }
        return null;
    }
}
