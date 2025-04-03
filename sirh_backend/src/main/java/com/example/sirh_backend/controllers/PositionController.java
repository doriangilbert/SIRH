package com.example.sirh_backend.controllers;

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
    public List<Position> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/positions/{id}")
    public Position getPositionById(@PathVariable long id) {
        return positionService.getPositionById(id);
    }

    @PostMapping("/positions")
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }
}
