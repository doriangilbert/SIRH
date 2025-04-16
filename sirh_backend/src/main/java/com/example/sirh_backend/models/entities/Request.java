package com.example.sirh_backend.models.entities;

import com.example.sirh_backend.models.enums.RequestStatus;
import com.example.sirh_backend.models.patterns.Observer;
import com.example.sirh_backend.models.patterns.Subject;
import jakarta.persistence.*;

import java.util.List;

@MappedSuperclass
public abstract class Request implements Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Enumerated(EnumType.STRING)
    protected RequestStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    protected Employee employee;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    protected Employee reviewer;

    @ManyToMany
    @JoinTable(
            name = "request_observers",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "observer_id")
    )
    protected List<Employee> observers;

    public Long getId() {
        return id;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Employee getReviewer() {
        return reviewer;
    }

    public void setReviewer(Employee reviewer) {
        this.reviewer = reviewer;
    }

    public List<Employee> getObservers() {
        return observers;
    }

    public void setObservers(List<Employee> observers) {
        this.observers = observers;
    }

    @Override
    public void addObserver(Observer observer) {
        if (observer instanceof Employee emp && !observers.contains(employee)) {
            observers.add(emp);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observer instanceof Employee emp) {
            observers.remove(emp);
        }
    }

    @Override
    public void notifyObservers(String message) {
        for (Employee observer : observers) {
            observer.update(message);
        }
    }
}
