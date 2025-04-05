package com.example.sirh_backend.controllers;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.models.Skill;
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
        return skillService.getAllSkills();
    }

    @GetMapping("/skills/{id}")
    public SkillDTO getSkillById(@PathVariable long id) {
        return skillService.getSkillById(id);
    }

    @PostMapping("/skills")
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.createSkill(skill);
    }
}
