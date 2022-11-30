package com.numan.hr.server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    public Employee(String employeeId, String name, String login, Double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.login = login;
        this.salary = salary;
    }

    @JsonProperty("id")
    @Column(name = "employeeId", nullable = false, unique = true)
    private String employeeId;

    @JsonProperty("name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @JsonProperty("login")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @JsonProperty("salary")
    @Column(name = "salary", nullable = false)
    private Double salary;

    public Employee() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
