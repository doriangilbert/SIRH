package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.PositionDTO;
import com.example.sirh_backend.models.entities.Position;

import java.util.List;

public class PositionMapper {

    private PositionMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static PositionDTO toDTO(Position position) {
        if (position != null) {
            return new PositionDTO(
                    position.getId(),
                    position.getName()
            );
        }
        return null;
    }

    public static List<PositionDTO> toDTO(List<Position> positions) {
        return positions.stream()
                .map(PositionMapper::toDTO)
                .toList();
    }
}
