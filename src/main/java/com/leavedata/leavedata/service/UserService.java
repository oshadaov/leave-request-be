package com.leavedata.leavedata.service;

import com.leavedata.leavedata.dto.request.CreateUserDTO;
import com.leavedata.leavedata.dto.request.LoginRequestDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.dto.response.LoginResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<String> createUser(CreateUserDTO dto);

    LoginResponseDTO login(LoginRequestDTO loginRequestDTO);

    LoggedInUserIdDTO getLoggedInUser();

}
