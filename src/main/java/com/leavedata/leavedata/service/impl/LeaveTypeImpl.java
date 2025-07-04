package com.leavedata.leavedata.service.impl;

import com.leavedata.leavedata.model.LeaveType;
import com.leavedata.leavedata.repository.LeaveTypeRepository;
import com.leavedata.leavedata.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveTypeImpl implements LeaveTypeService {

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;


    @Override
    public List<LeaveType> getLeaveType() {
        return leaveTypeRepository.findAll();
    }
}
