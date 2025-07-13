package com.leavedata.leavedata.controller;

import com.leavedata.leavedata.dto.response.LoggedInEmployeeDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.service.EmployeeServiec;
import com.leavedata.leavedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {


    private final EmployeeServiec service;


    private final UserService userService;

    @Autowired
    public EmployeeController(EmployeeServiec service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<Employee>> getAllEmployee(){
        List<Employee> allEmployees = service.getAllEmployee();
        return new ResponseEntity<>(allEmployees,HttpStatus.OK);
    }



    @GetMapping("/loggedinemp")
    public ResponseEntity<LoggedInEmployeeDTO> getLoggedInEmployee() {
        LoggedInUserIdDTO loggedInUser = userService.getLoggedInUser();
        LoggedInEmployeeDTO dto = service.getLoggedInEmployee(loggedInUser);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    }

