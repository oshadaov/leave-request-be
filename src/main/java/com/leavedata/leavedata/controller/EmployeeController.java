package com.leavedata.leavedata.controller;

import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.service.EmployeeServiec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {


    @Autowired
    private EmployeeServiec service;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return service.getAllEmployee();
    }
}
