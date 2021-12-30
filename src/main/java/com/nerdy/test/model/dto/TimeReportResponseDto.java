package com.nerdy.test.model.dto;

import com.nerdy.test.model.TimeReportDay;
import java.util.List;
import lombok.Data;

@Data
public class TimeReportResponseDto {
    private Long id;
    private Long employeeId;
    private String timeReportName;
    private List<TimeReportDay> timeReportDay;
}
