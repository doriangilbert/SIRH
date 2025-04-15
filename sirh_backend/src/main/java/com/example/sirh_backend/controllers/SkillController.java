package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.mappers.SkillMapper;
import com.example.sirh_backend.models.entities.Skill;
import com.example.sirh_backend.services.SkillService;
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
    public List<SkillDTO> getAllSkills() {
        return SkillMapper.toDTO(skillService.getAllSkills());
    }

    @GetMapping("/skills/{id}")
    public SkillDTO getSkillById(@PathVariable long id) {
        return SkillMapper.toDTO(skillService.getSkillById(id));
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }

    @PutMapping("/skills/{id}")
    public Skill updateSkill(@PathVariable long id, @RequestBody Skill skill) {
        return skillService.updateSkill(id, skill);
    }
}
