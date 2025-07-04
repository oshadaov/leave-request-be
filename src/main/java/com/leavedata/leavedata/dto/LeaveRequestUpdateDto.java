package com.leavedata.leavedata.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LeaveRequestUpdateDto {
    private int leaveTypeId;
    private LocalDate leaveStartDate;
    private String leavePeriod;
    private String halfDayInfo;
    private Double noOfLeaveDays;
    private String note;
    private String status;
}
