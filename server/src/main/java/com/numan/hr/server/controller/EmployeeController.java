package com.numan.hr.server.controller;

import com.numan.hr.server.models.Employee;
import com.numan.hr.server.repositories.EmployeeRespository;
import com.numan.hr.server.services.EmployeeService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@RestController
@RequestMapping("employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("all")
    List<Employee> allEmployees() {
        return employeeService.getAllEmployees();
    }

    @DeleteMapping("delete/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable String employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("uploadcsv")
    public ResponseEntity<String> saveCsv(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.validateCsvUpload(file);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
