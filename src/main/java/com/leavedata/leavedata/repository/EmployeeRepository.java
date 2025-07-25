package com.leavedata.leavedata.repository;

import com.leavedata.leavedata.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Integer> {
    Optional<Employee> findByUserId(Long userId);

}
