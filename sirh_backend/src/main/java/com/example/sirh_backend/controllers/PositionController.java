package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.PositionDTO;
import com.example.sirh_backend.mappers.PositionMapper;
import com.example.sirh_backend.models.Position;
import com.example.sirh_backend.services.PositionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class PositionController {

    private final PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/positions")
    public List<PositionDTO> getAllPositions() {
        return PositionMapper.toDTO(positionService.getAllPositions());
    }

    @GetMapping("/positions/{id}")
    public PositionDTO getPositionById(@PathVariable long id) {
        return PositionMapper.toDTO(positionService.getPositionById(id));
    }

    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/positions/{id}")
    public Position updatePosition(@PathVariable long id, @RequestBody Position position) {
        return positionService.updatePosition(id, position);
    }
}
