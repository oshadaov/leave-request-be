package com.leavedata.leavedata.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class LoginRequestDTO {
    private String email;
    private String Password;
}
