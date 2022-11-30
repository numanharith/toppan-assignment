package com.numan.hr.server.repositories;

import com.numan.hr.server.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRespository extends JpaRepository<Employee, Long> {
    void deleteByEmployeeId(String employeeId);
}
