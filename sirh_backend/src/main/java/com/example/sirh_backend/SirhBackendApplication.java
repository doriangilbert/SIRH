package com.example.sirh_backend;

import com.example.sirh_backend.models.*;
import com.example.sirh_backend.repositories.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin
public class SirhBackendApplication {

    private final EmployeeRepository employeeRepository;
    private final EvaluationRepository evaluationRepository;
    private final FeedbackRepository feedbackRepository;
    private final LeaveRepository leaveRepository;
    private final ObjectiveRepository objectiveRepository;
    private final PositionRepository positionRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final TrainingRepository trainingRepository;

    public SirhBackendApplication(EmployeeRepository employeeRepository,
                                  EvaluationRepository evaluationRepository,
                                  FeedbackRepository feedbackRepository,
                                  LeaveRepository leaveRepository,
                                  ObjectiveRepository objectiveRepository,
                                  PositionRepository positionRepository,
                                  SkillRepository skillRepository,
                                  TeamRepository teamRepository,
                                  TrainingRepository trainingRepository) {
        this.employeeRepository = employeeRepository;
        this.evaluationRepository = evaluationRepository;
        this.feedbackRepository = feedbackRepository;
        this.leaveRepository = leaveRepository;
        this.objectiveRepository = objectiveRepository;
        this.positionRepository = positionRepository;
        this.skillRepository = skillRepository;
        this.teamRepository = teamRepository;
        this.trainingRepository = trainingRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SirhBackendApplication.class, args);
    }

    @GetMapping("/")
    public String index(@RequestParam(value = "name", defaultValue = "User") String name) {
        return String.format("Hello %s! Welcome to SIRH.", name);
    }

    @PostMapping("/init")
    public String init() {
        if (employeeRepository.count() > 0 || evaluationRepository.count() > 0 || feedbackRepository.count() > 0 ||
                leaveRepository.count() > 0 || objectiveRepository.count() > 0 || positionRepository.count() > 0 ||
                skillRepository.count() > 0 || teamRepository.count() > 0 || trainingRepository.count() > 0) {
            throw new IllegalStateException("Tables are not empty. Please reset the database before initializing.");
        }

        Team team1 = new Team("Team A", null);
        Team team2 = new Team("Team B", null);
        Team team3 = new Team("Team C", null);
        Team team4 = new Team("Team D", null);
        teamRepository.saveAll(List.of(team1, team2, team3, team4));

        Position position1 = new Position("Developer");
        Position position2 = new Position("Manager");
        Position position3 = new Position("Tester");
        Position position4 = new Position("Designer");
        positionRepository.saveAll(List.of(position1, position2, position3, position4));

        Skill skill1 = new Skill("Java");
        Skill skill2 = new Skill("Spring Boot");
        Skill skill3 = new Skill("React");
        Skill skill4 = new Skill("Angular");
        Skill skill5 = new Skill("Python");
        Skill skill6 = new Skill("SQL");
        Skill skill7 = new Skill("NoSQL");
        skillRepository.saveAll(List.of(skill1, skill2, skill3, skill4, skill5, skill6, skill7));

        Employee manager1 = new Employee("John", "Doe", position2, team1);
        manager1.setLeaveBalance(30);
        Employee manager2 = new Employee("Jane", "Smith", position2, team2);
        manager2.setLeaveBalance(25);
        Employee manager3 = new Employee("Tom", "Brown", position2, team3);
        manager3.setLeaveBalance(35);
        Employee manager4 = new Employee("Lucy", "White", position2, team4);
        manager4.setLeaveBalance(40);
        Employee emp1 = new Employee("Alice", "Brown", position1, team1);
        emp1.setLeaveBalance(20);
        Employee emp2 = new Employee("Bob", "White", position1, team1);
        emp2.setLeaveBalance(22);
        Employee emp3 = new Employee("Charlie", "Black", position1, team1);
        emp3.setLeaveBalance(18);
        Employee emp4 = new Employee("David", "Green", position1, team2);
        emp4.setLeaveBalance(25);
        Employee emp5 = new Employee("Eve", "Blue", position1, team2);
        emp5.setLeaveBalance(28);
        Employee emp6 = new Employee("Frank", "Yellow", position3, team3);
        emp6.setLeaveBalance(15);
        Employee emp7 = new Employee("Grace", "Pink", position3, team3);
        emp7.setLeaveBalance(17);
        Employee emp8 = new Employee("Hank", "Purple", position4, team4);
        emp8.setLeaveBalance(19);
        Employee emp9 = new Employee("Ivy", "Orange", position4, team4);
        emp9.setLeaveBalance(21);
        Employee emp10 = new Employee("Jack", "Gray", position1, team4);
        emp10.setLeaveBalance(23);

        List<Employee> employees = List.of(manager1, manager2, manager3, manager4, emp1, emp2, emp3, emp4, emp5, emp6, emp7, emp8, emp9, emp10);
        List<Skill> skills = List.of(skill1, skill2, skill3, skill4, skill5, skill6, skill7);

        for (Employee employee : employees) {
            int numSkills = (int) (Math.random() * skills.size()) + 1;
            for (int i = 0; i < numSkills; i++) {
                Skill skill = skills.get((int) (Math.random() * skills.size()));
                if (!employee.getSkills().contains(skill)) {
                    employee.addSkill(skill);
                }
            }
        }

        employeeRepository.saveAll(employees);

        team1.setManager(manager1);
        team2.setManager(manager2);
        team3.setManager(manager3);
        team4.setManager(manager4);
        teamRepository.saveAll(List.of(team1, team2, team3, team4));

        for (Employee employee : employees) {
            if (Math.random() > 0.5) {
                Evaluation evaluation = new Evaluation(Year.of(2025), "Annual Review", employee);
                evaluationRepository.save(evaluation);
                if (employee.getPosition().equals(position2)) {
                    Feedback feedback = new Feedback("Feedback for " + employee.getFirstName(), employee, evaluation);
                    feedbackRepository.save(feedback);
                    int numObjectives = (int) (Math.random() * 3) + 1;
                    for (int i = 0; i < numObjectives; i++) {
                        Objective objective = new Objective("Objective for " + employee.getFirstName() + " " + (i + 1), evaluation);
                        objectiveRepository.save(objective);
                    }
                }
            }
        }

        for (Employee employee : employees) {
            if (Math.random() > 0.5) {
                LocalDate startDate = LocalDate.of(2025, (int) (Math.random() * 12) + 1, (int) (Math.random() * 28) + 1);
                LocalDate endDate = startDate.plusDays((int) (Math.random() * 10) + 1); // Assurer que la date de fin est après la date de début
                Leave leave = new Leave(startDate, endDate, employee);
                leaveRepository.save(leave);
                int leaveDuration = (int) (endDate.toEpochDay() - startDate.toEpochDay()) + 1; // Inclure le jour de début
                employee.setLeaveBalance(employee.getLeaveBalance() - leaveDuration);
            }
        }

        Training training1 = new Training("Java Training", "Advanced Java concepts");
        Training training2 = new Training("Spring Boot Training", "Spring Boot fundamentals");
        Training training3 = new Training("React Training", "React basics");
        Training training4 = new Training("Angular Training", "Angular fundamentals");
        Training training5 = new Training("Python Training", "Python for data science");
        Training training6 = new Training("SQL Training", "SQL for beginners");
        Training training7 = new Training("NoSQL Training", "NoSQL databases");
        List<Training> trainings = List.of(training1, training2, training3, training4, training5, training6, training7);

        for (Training training : trainings) {
            int numSkills = (int) (Math.random() * skills.size()) + 1;
            for (int i = 0; i < numSkills; i++) {
                Skill skill = skills.get((int) (Math.random() * skills.size()));
                if (!training.getSkills().contains(skill)) {
                    training.addSkill(skill);
                }
            }
            int numEmployees = (int) (Math.random() * employees.size()) + 1;
            for (int i = 0; i < numEmployees; i++) {
                Employee employee = employees.get((int) (Math.random() * employees.size()));
                if (!training.getEmployees().contains(employee)) {
                    training.addEmployee(employee);
                }
            }
            trainingRepository.save(training);
        }

        return "SIRH Backend Initialized!";
    }
}