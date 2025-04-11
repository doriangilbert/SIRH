package com.example.sirh_backend.services;

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

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(long id) {
        return skillRepository.findById(id).orElse(null);
    }

    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    public Skill updateSkill(long id, Skill updatedSkill) {
        Skill skill = skillRepository.findById(id).orElse(null);
        if (skill != null) {
            skill.setName(updatedSkill.getName());
            return skillRepository.save(skill);
        }
        return null;
    }
}
