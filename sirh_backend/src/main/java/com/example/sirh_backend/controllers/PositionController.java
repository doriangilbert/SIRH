package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.PositionDTO;
import com.example.sirh_backend.mappers.PositionMapper;
import com.example.sirh_backend.models.entities.Position;
import com.example.sirh_backend.services.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<PositionDTO>> getAllPositions() {
        return new ResponseEntity<>(PositionMapper.toDTO(positionService.getAllPositions()), HttpStatus.OK);
    }

    @GetMapping("/positions/{id}")
    public ResponseEntity<PositionDTO> getPositionById(@PathVariable long id) {
        return new ResponseEntity<>(PositionMapper.toDTO(positionService.getPositionById(id)), HttpStatus.OK);
    }

    @PostMapping("/positions")
    public ResponseEntity<PositionDTO> createPosition(@RequestBody Position position) {
        return new ResponseEntity<>(PositionMapper.toDTO(positionService.createPosition(position)), HttpStatus.CREATED);
    }

    @PutMapping("/positions/{id}")
    public ResponseEntity<PositionDTO> updatePosition(@PathVariable long id, @RequestBody Position position) {
        return new ResponseEntity<>(PositionMapper.toDTO(positionService.updatePosition(id, position)), HttpStatus.OK);
    }
}
