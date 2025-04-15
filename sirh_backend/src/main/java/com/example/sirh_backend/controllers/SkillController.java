package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.mappers.SkillMapper;
import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.services.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/skills")
    public ResponseEntity<List<SkillDTO>> getAllSkills() {
        return new ResponseEntity<>(SkillMapper.toDTO(skillService.getAllSkills()), HttpStatus.OK);
    }

    @GetMapping("/skills/{id}")
    public ResponseEntity<SkillDTO> getSkillById(@PathVariable long id) {
        return new ResponseEntity<>(SkillMapper.toDTO(skillService.getSkillById(id)), HttpStatus.OK);
    }

    @PostMapping("/skills")
    public ResponseEntity<SkillDTO> createSkill(@RequestBody Skill skill) {
        return new ResponseEntity<>(SkillMapper.toDTO(skillService.createSkill(skill)), HttpStatus.CREATED);
    }

    @PutMapping("/skills/{id}")
    public ResponseEntity<SkillDTO> updateSkill(@PathVariable long id, @RequestBody Skill skill) {
        return new ResponseEntity<>(SkillMapper.toDTO(skillService.updateSkill(id, skill)), HttpStatus.OK);
    }
}
