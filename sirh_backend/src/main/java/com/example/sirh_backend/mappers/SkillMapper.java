package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.models.entities.Skill;

import java.util.List;

public class SkillMapper {

    private SkillMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static SkillDTO toDTO(Skill skill) {
        if (skill != null) {
            return new SkillDTO(
                    skill.getId(),
                    skill.getName()
            );
        }
        return null;
    }

    public static List<SkillDTO> toDTO(List<Skill> skills) {
        return skills.stream()
                .map(SkillMapper::toDTO)
                .toList();
    }
}
