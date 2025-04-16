package com.example.sirh_backend.services;

import com.example.sirh_backend.exceptions.NotFoundException;
import com.example.sirh_backend.models.entities.Position;
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
        return positionRepository.findById(id).orElseThrow(() -> new NotFoundException("Position not found"));
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePosition(long id, Position updatedPosition) {
        Position position = positionRepository.findById(id).orElseThrow(() -> new NotFoundException("Position not found"));
        if (updatedPosition == null) {
            throw new IllegalArgumentException("Updated position data cannot be null");
        }
        position.setName(updatedPosition.getName());
        return positionRepository.save(position);
    }
}
