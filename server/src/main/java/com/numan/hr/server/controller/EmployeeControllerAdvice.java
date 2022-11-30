package com.numan.hr.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class EmployeeControllerAdvice {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> integrityConstraint() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("An existing ID was found in the CSV file.");
    }
}
