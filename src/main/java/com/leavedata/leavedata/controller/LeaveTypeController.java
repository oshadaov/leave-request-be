package com.leavedata.leavedata.controller;

import com.leavedata.leavedata.model.LeaveType;
import com.leavedata.leavedata.service.LeaveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/leaveTypes")
public class LeaveTypeController {


        private final LeaveTypeService service;

        @Autowired
    public LeaveTypeController(LeaveTypeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<LeaveType>> getLeaveType(){

            List<LeaveType> leaveTypes = service.getLeaveType();
            return new ResponseEntity<>(leaveTypes, HttpStatus.ACCEPTED);
        }
}
