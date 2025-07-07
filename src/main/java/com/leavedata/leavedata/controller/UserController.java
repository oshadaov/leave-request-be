package com.leavedata.leavedata.controller;


import com.leavedata.leavedata.dto.request.CreateUserDTO;
import com.leavedata.leavedata.dto.request.LoginRequestDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.dto.response.LoginResponseDTO;
import com.leavedata.leavedata.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        return userService.createUser(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return userService.login(loginRequestDTO);
    }



    @GetMapping("/loggedInUser")
    public ResponseEntity<LoggedInUserIdDTO> getLoggedInUser(){
        LoggedInUserIdDTO response= userService.getLoggedInUser();
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}

