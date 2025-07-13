package com.leavedata.leavedata.service.impl;

import com.leavedata.leavedata.dto.request.CreateUserDTO;
import com.leavedata.leavedata.dto.request.LoginRequestDTO;
import com.leavedata.leavedata.dto.response.LoggedInUserIdDTO;
import com.leavedata.leavedata.dto.response.LoginResponseDTO;
import com.leavedata.leavedata.enums.UserRole;
import com.leavedata.leavedata.model.Employee;
import com.leavedata.leavedata.model.User;
import com.leavedata.leavedata.repository.EmployeeRepository;
import com.leavedata.leavedata.repository.UserRepository;
import com.leavedata.leavedata.service.UserService;
import com.leavedata.leavedata.utils.JwtUtil;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository ,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public ResponseEntity<String> createUser(CreateUserDTO dto) {


        try{
        if(userRepository.existsByEmail(dto.getEmail())){
            return new ResponseEntity<>("Email is already registered", HttpStatus.CONFLICT);
        }
        User user = new User(
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                UserRole.REG_USER
        );
        userRepository.save(user);



            Employee employee = new Employee();
            employee.setMemberNo("M000" + user.getId());
            employee.setName(dto.getUsername());
            employee.setUser(user);

            employeeRepository.save(employee);

        return new ResponseEntity<>("Successfully created",HttpStatus.CREATED);
    } catch(Exception ex){
        return new ResponseEntity<>("Error creating user: "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<?> login(LoginRequestDTO loginRequestDTO) {
        try {
            Optional<User> existingUser = userRepository.findByEmail(loginRequestDTO.getEmail());

            if (existingUser.isEmpty()) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }

            User user = existingUser.get();

            if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }

            String token = jwtUtil.generateToken(
                    user.getEmail(),
                    user.getRole().name(),
                    user.getId(),
                    user.getUsername()
            );

            LoginResponseDTO responseDTO = new LoginResponseDTO(
                    token,
                    user.getRole().name(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getId()
            );

            return ResponseEntity.ok(responseDTO);

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong: " + e.getMessage());
        }
    }



    //used to get http-request
    @Autowired
    private HttpServletRequest request;


    //utilty-common method no map
    @Override
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
