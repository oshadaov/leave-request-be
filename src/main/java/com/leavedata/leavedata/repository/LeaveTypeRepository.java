package com.leavedata.leavedata.repository;

import com.leavedata.leavedata.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType , Integer> {
}
