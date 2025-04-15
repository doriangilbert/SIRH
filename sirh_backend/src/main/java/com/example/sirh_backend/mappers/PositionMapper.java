package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.PositionDTO;
import com.example.sirh_backend.models.entities.Position;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PositionMapper {

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
                .collect(Collectors.toList());
    }
}
