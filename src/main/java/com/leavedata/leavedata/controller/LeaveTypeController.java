package com.leavedata.leavedata.controller;

import com.leavedata.leavedata.model.LeaveType;
import com.leavedata.leavedata.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/leaveTypes")
@CrossOrigin(origins = "*")
public class LeaveTypeController {

         @Autowired
        private LeaveTypeService service;


        @GetMapping
    public List<LeaveType> getLeaveType(){
            return service.getLeaveType();
        }
}
