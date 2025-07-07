package com.leavedata.leavedata.controller;

import com.leavedata.leavedata.dto.LeaveRequestDto;
import com.leavedata.leavedata.dto.LeaveRequestUpdateDto;
import com.leavedata.leavedata.model.LeaveRequest;
import com.leavedata.leavedata.service.LeaveRequestService;
import com.leavedata.leavedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@CrossOrigin(origins = "*")
public class LeaveRequestController {



    private final LeaveRequestService leaveRequestService;
    private final UserService userService;

    @Autowired
    public LeaveRequestController(LeaveRequestService leaveRequestService, UserService userService) {
        this.leaveRequestService = leaveRequestService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAll() {
        List<LeaveRequest> allRequests = leaveRequestService.getAll();
        return new ResponseEntity<>(allRequests, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public LeaveRequest getById(@PathVariable Long id) {

        return leaveRequestService.getById(id);
    }




    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<LeaveRequest> createLeaveRequest(
            @RequestPart("data") LeaveRequestDto dto,
            @RequestPart("supportedDocument") MultipartFile file) {
        LeaveRequest newRequest = leaveRequestService.create(dto, file);
        return new ResponseEntity<>(newRequest, HttpStatus.CREATED);
    }



    @PutMapping("update/{id}")
    public ResponseEntity<LeaveRequest> updateLeaveRequest(
            @PathVariable Long id,
            @RequestBody LeaveRequestUpdateDto dto){

        LeaveRequest updatedRequest = leaveRequestService.updateRequest(id,dto);

        return new ResponseEntity<>(updatedRequest,HttpStatus.OK);
    }

    @PutMapping("withdraw/{id}")
    public ResponseEntity<LeaveRequest> withdrawLeaveRequest(@PathVariable  Long id) {

        LeaveRequest withdrewRequest = leaveRequestService.withdrawRequest(id);

        return new ResponseEntity<>(withdrewRequest,HttpStatus.OK);
    }


//API for get request list for loggedInUser
    @GetMapping("/my-requests")
    public ResponseEntity<List<LeaveRequest>> getMyRequests() {
        List<LeaveRequest> requests = leaveRequestService.getRequestsByLoggedInUser();
        return new ResponseEntity<>(requests,HttpStatus.ACCEPTED);
    }

}