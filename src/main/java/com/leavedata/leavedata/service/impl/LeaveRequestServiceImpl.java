package com.leavedata.leavedata.service.impl;

import com.leavedata.leavedata.dto.LeaveRequestDto;
import com.leavedata.leavedata.dto.LeaveRequestUpdateDto;
import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.model.LeaveRequest;
import com.leavedata.leavedata.model.LeaveType;
import com.leavedata.leavedata.repository.EmployeeRepository;
import com.leavedata.leavedata.repository.LeaveRequestRepository;
import com.leavedata.leavedata.repository.LeaveTypeRepository;
import com.leavedata.leavedata.service.LeaveRequestService;
import com.leavedata.leavedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Autowired
    private UserService userService;
    @Autowired
    private LeaveRequestRepository leaveRequestRepo;

    @Autowired
    private EmployeeRepository employeeRepo;

    @Autowired
    private LeaveTypeRepository leaveTypeRepo;

    @Value("${app.upload.dir:uploads/}")
    private String uploadDir;

    @Override
    public List<LeaveRequest> getAll() {

        return leaveRequestRepo.findAll();
    }

    @Override
    public LeaveRequest getById(Long id) {

        return leaveRequestRepo.findById(id).orElseThrow(() -> new RuntimeException("LeaveRequest not found"));
    }

    @Override
    public LeaveRequest create(LeaveRequestDto dto, MultipartFile file) {
        Employee employee = employeeRepo.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LeaveType leaveType = leaveTypeRepo.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("LeaveType not found"));

        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Uploaded file is empty or missing");
        }

        String originalFileName = Paths.get(file.getOriginalFilename()).getFileName().toString();
        String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;

        String absoluteUploadPath = new File(uploadDir).getAbsolutePath();
        File uploadPathDir = new File(absoluteUploadPath);
        if (!uploadPathDir.exists() && !uploadPathDir.mkdirs()) {
            throw new RuntimeException("Failed to create upload directory");
        }

        File dest = new File(uploadPathDir, uniqueFileName);

        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }

        LeaveRequest leaveRequest = new LeaveRequest();
        leaveRequest.setEmployee(employee);
        leaveRequest.setLeaveType(leaveType);
        leaveRequest.setLeaveStartDate(dto.getLeaveStartDate());
        leaveRequest.setLeavePeriod(dto.getLeavePeriod());
        leaveRequest.setHalfDayInfo(dto.getHalfDayInfo());
        leaveRequest.setNoOfLeaveDays(dto.getNoOfLeaveDays());
        leaveRequest.setNote(dto.getNote());
        leaveRequest.setStatus("Pending");
        leaveRequest.setSupportedDocument(uniqueFileName);
        leaveRequest.setCreatedBy(employee.getName());
        leaveRequest.setUpdatedBy(employee.getName());

        return leaveRequestRepo.save(leaveRequest);
    }

    @Override
    public LeaveRequest updateRequest(Long id, LeaveRequestUpdateDto dto) {

        LeaveRequest existing = leaveRequestRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave Request not found with id: " + id));


        LeaveType leaveType = leaveTypeRepo.findById(dto.getLeaveTypeId())
                .orElseThrow(() -> new RuntimeException("LeaveType not found"));

        existing.setLeaveType(leaveType);

        existing.setLeaveStartDate(dto.getLeaveStartDate());
        existing.setLeavePeriod(dto.getLeavePeriod());
        existing.setHalfDayInfo(dto.getHalfDayInfo());
//        existing.setStatus(dto.getStatus());
        existing.setNote(dto.getNote());
        existing.setNoOfLeaveDays(dto.getNoOfLeaveDays());

        existing.setUpdatedBy(existing.getEmployee().getName());
        existing.setUpdatedAt(LocalDateTime.now());

        return leaveRequestRepo.save(existing);


    }

    @Override
    public LeaveRequest withdrawRequest(Long id ) {
        LeaveRequest existingRequest = leaveRequestRepo.findById(id).orElseThrow(() -> new RuntimeException("Leave Request not found with id: " + id));

        existingRequest.setStatus("Cancel");
        existingRequest.setUpdatedAt(LocalDateTime.now());
        existingRequest.setUpdatedBy(existingRequest.getEmployee().getName());

        return leaveRequestRepo.save(existingRequest);
    }



//get list of loggedIn users
    @Override
    public List<LeaveRequest> getRequestsByLoggedInUser() {
        Long userId = userService.getLoggedInUser().getUserId();


        Employee employee = employeeRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Employee not found for user ID: " + userId));

        return leaveRequestRepo.findAllByEmployeeId(employee.getId());
    }


}
