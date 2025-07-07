package com.leavedata.leavedata.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoggedInEmployeeDTO {


    private String name;
    private String memberNo;
}
