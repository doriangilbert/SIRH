package com.example.sirh_backend.services;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.models.Skill;
import com.example.sirh_backend.repositories.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillDTO> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(skill -> new SkillDTO(
                        skill.getId(),
                        skill.getName()
                ))
                .toList();
    }

    public SkillDTO getSkillById(long id) {
        Skill skill = skillRepository.findById(id).orElse(null);
        if (skill != null) {
            return new SkillDTO(
                    skill.getId(),
                    skill.getName()
            );
        }
        return null;
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }
}
