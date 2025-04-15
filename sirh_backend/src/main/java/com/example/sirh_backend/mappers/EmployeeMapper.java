package com.example.sirh_backend.mappers;

import com.example.sirh_backend.dtos.EmployeeDTO;
import com.example.sirh_backend.models.entities.Employee;
import com.example.sirh_backend.models.entities.Notification;
import com.example.sirh_backend.models.entities.Skill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapper {

    public static EmployeeDTO toDTO(Employee employee) {
        if (employee != null) {
            return new EmployeeDTO(
                    employee.getId(),
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getLeaveBalance(),
                    employee.getPosition() != null ? employee.getPosition().getId() : null,
                    employee.getSkills() != null ? employee.getSkills().stream().map(Skill::getId).collect(Collectors.toList()) : null,
                    employee.getTeam() != null ? employee.getTeam().getId() : null,
                    employee.getNotifications() != null ? employee.getNotifications().stream().map(Notification::getId).collect(Collectors.toList()) : null
            );
        }
        return null;
    }

    public static List<EmployeeDTO> toDTO(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .collect(Collectors.toList());
    }
}
