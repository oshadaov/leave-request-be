package com.leavedata.leavedata.service;

import com.leavedata.leavedata.dto.request.CreateUserDTO;
import com.leavedata.leavedata.dto.request.LoginRequestDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> createUser(CreateUserDTO dto);

    ResponseEntity<?> login(LoginRequestDTO loginRequestDTO);

    LoggedInUserIdDTO getLoggedInUser();

}
