package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.SkillDTO;
import com.example.sirh_backend.models.entities.Skill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillMapper {

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
                .collect(Collectors.toList());
    }
}
