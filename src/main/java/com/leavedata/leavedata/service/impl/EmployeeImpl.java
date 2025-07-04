package com.leavedata.leavedata.service.impl;

import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.repository.EmployeeRepository;
import com.leavedata.leavedata.service.EmployeeServiec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeImpl implements EmployeeServiec {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }
}
