package com.nerdy.test.model.dto;

import lombok.Data;

@Data
public class TimeReportRequestDto {
    private String timeReportName;
    private Long employeeId;
}
