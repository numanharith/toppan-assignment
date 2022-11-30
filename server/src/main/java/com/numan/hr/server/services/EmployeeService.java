package com.numan.hr.server.services;

import com.numan.hr.server.models.Employee;
import com.numan.hr.server.repositories.EmployeeRespository;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class EmployeeService {

    private final EmployeeRespository employeeRespository;

    public EmployeeService(EmployeeRespository employeeRespository) {
        this.employeeRespository = employeeRespository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRespository.findAll();
    }

    @Transactional
    public void deleteEmployee(String employeeId) {
        employeeRespository.deleteByEmployeeId(employeeId);
    }
    public void validateCsvUpload(MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), UTF_8));
        String[] headers = { "id", "login", "name", "salary" };
        Iterable<CSVRecord> csvRecords = CSVFormat.DEFAULT.withHeader(headers).withFirstRecordAsHeader().parse(fileReader);
        ArrayList<Employee> employees = new ArrayList<>();
        for (CSVRecord record : csvRecords) {
            Employee employee = new Employee(
                    record.get("id"),
                    record.get("login"),
                    record.get("name"),
                    Double.valueOf(record.get("salary"))
            );
            employees.add(employee);
        }
        employeeRespository.saveAll(employees);
    }
}
