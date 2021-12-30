package com.nerdy.test.service.mapper.impl;

import com.nerdy.test.model.TimeReportDay;
import com.nerdy.test.model.dto.TimeReportDayRequestDto;
import com.nerdy.test.model.dto.TimeReportDayResponseDto;
import com.nerdy.test.service.mapper.RequestDtoMapper;
import com.nerdy.test.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class TimeReportDayMapper
        implements ResponseDtoMapper<TimeReportDayResponseDto, TimeReportDay>,
        RequestDtoMapper<TimeReportDayRequestDto, TimeReportDay> {

    @Override
    public TimeReportDay mapToModel(TimeReportDayRequestDto dto) {
        TimeReportDay day = new TimeReportDay();
        day.setTimeReportId(dto.getTimeReportId());
        day.setHour(dto.getHour());
        day.setDescription(dto.getDescription());
        day.setDate(dto.getDate());
        return day;
    }

    @Override
    public TimeReportDayResponseDto mapToDto(TimeReportDay day) {
        TimeReportDayResponseDto dto = new TimeReportDayResponseDto();
        dto.setTimeReportId(day.getTimeReportId());
        dto.setId(day.getId());
        dto.setDate(day.getDate());
        dto.setHour(day.getHour());
        dto.setDescription(day.getDescription());
        return dto;
    }
}
