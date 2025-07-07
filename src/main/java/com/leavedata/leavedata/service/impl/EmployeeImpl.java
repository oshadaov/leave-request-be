package com.leavedata.leavedata.service.impl;

import com.leavedata.leavedata.dto.response.LoggedInEmployeeDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.repository.EmployeeRepository;
import com.leavedata.leavedata.service.EmployeeServiec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeImpl implements EmployeeServiec {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }



    @Override
    public LoggedInEmployeeDTO getLoggedInEmployee(LoggedInUserIdDTO dto) {
        Employee employee = employeeRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        return new LoggedInEmployeeDTO(
                employee.getName(),
                employee.getMemberNo()
        );
    }


}
