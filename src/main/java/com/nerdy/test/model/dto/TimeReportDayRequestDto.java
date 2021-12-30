package com.nerdy.test.model.dto;

import java.time.LocalDate;
import lombok.Data;

@Data
public class TimeReportDayRequestDto {
    private Long timeReportId;
    private String description;
    private int hour;
    private LocalDate date;
}
