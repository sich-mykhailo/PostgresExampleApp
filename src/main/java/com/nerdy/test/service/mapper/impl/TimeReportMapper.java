package com.nerdy.test.service.mapper.impl;

import com.nerdy.test.model.TimeReport;
import com.nerdy.test.model.dto.TimeReportRequestDto;
import com.nerdy.test.model.dto.TimeReportResponseDto;
import com.nerdy.test.service.mapper.RequestDtoMapper;
import com.nerdy.test.service.mapper.ResponseDtoMapper;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class TimeReportMapper
        implements RequestDtoMapper<TimeReportRequestDto, TimeReport>,
        ResponseDtoMapper<TimeReportResponseDto, TimeReport> {

    @Override
    public TimeReport mapToModel(TimeReportRequestDto dto) {
        TimeReport report = new TimeReport();
        report.setEmployeeId(dto.getEmployeeId());
        report.setTimeReportName(dto.getTimeReportName());
        if(report.getTimeReportDays() == null) {
            report.setTimeReportDays(new ArrayList<>());
        }
        return report;
    }

    @Override
    public TimeReportResponseDto mapToDto(TimeReport report) {
        TimeReportResponseDto responseDto = new TimeReportResponseDto();
        responseDto.setId(report.getId());
        responseDto.setEmployeeId(report.getEmployeeId());
        responseDto.setTimeReportName(report.getTimeReportName());
        responseDto.setTimeReportDay(report.getTimeReportDays());
        return responseDto;
    }
}
