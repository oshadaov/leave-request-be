package com.leavedata.leavedata.utils;

import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;


@Component
public class UserUtil {


    private final JwtUtil jwtUtil;

    public UserUtil(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    private HttpServletRequest request;

    public LoggedInUserIdDTO getLoggedInUser() {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        Long userId = jwtUtil.extractId(token);

        return new LoggedInUserIdDTO(userId);
    }
}
