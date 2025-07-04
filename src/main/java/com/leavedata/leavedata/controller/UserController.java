package com.leavedata.leavedata.controller;


import com.leavedata.leavedata.dto.request.CreateUserDTO;
import com.leavedata.leavedata.dto.request.LoginRequestDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.dto.response.LoginResponseDTO;
import com.leavedata.leavedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> createUser(@RequestBody CreateUserDTO dto){
      return   userService.createUser(dto);
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        LoginResponseDTO response = userService.login(loginRequestDTO);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/loggedInUser")
    public ResponseEntity<LoggedInUserIdDTO> getLoggedInUser(){
        LoggedInUserIdDTO response= userService.getLoggedInUser();
        return ResponseEntity.ok(response);
    }
}

