package com.leavedata.leavedata.service;
import com.leavedata.leavedata.dto.LeaveRequestDto;
import com.leavedata.leavedata.dto.LeaveRequestUpdateDto;
import com.leavedata.leavedata.model.LeaveRequest;
import com.leavedata.leavedata.repository.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;


public interface LeaveRequestService {

    List<LeaveRequest> getAll();
    LeaveRequest getById(Long id);

    LeaveRequest create(LeaveRequestDto dto, MultipartFile file);

    LeaveRequest updateRequest(Long id, LeaveRequestUpdateDto dto);

    LeaveRequest withdrawRequest(Long id );


  List<LeaveRequest> getRequestsByLoggedInUser();

}



