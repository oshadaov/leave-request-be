package com.leavedata.leavedata.service;

import com.leavedata.leavedata.dto.response.LoggedInEmployeeDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.model.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EmployeeServiec {
    List<Employee> getAllEmployee();

    LoggedInEmployeeDTO getLoggedInEmployee(LoggedInUserIdDTO loggedInUser);



}
