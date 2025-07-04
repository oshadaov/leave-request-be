package com.leavedata.leavedata.model;

import com.fasterxml.jackson.databind.deser.impl.CreatorCandidate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "leave_type_id")
    private LeaveType leaveType;
    private LocalDate leaveStartDate;
    private String leavePeriod;
    private String halfDayInfo;
    private Double noOfLeaveDays;
    private String supportedDocument;
    private String note;

    private String status;



    @CreationTimestamp
    private LocalDateTime createdAt;
    private String createdBy;

//    @UpdateTimestamp
    private LocalDateTime updatedAt;
    private String updatedBy;
}



