package com.nerdy.test.controller;

import com.nerdy.test.model.TimeReportDay;
import com.nerdy.test.model.dto.TimeReportDayRequestDto;
import com.nerdy.test.model.dto.TimeReportDayResponseDto;
import com.nerdy.test.service.TimeReportDayService;
import com.nerdy.test.service.mapper.impl.TimeReportDayMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-reports")
public class TimeReportDayController {
    private final TimeReportDayMapper timeReportDayMapper;
    private final TimeReportDayService timeReportDayService;


    public TimeReportDayController(TimeReportDayMapper timeReportDayResponseMapper,
                                   TimeReportDayService timeReportDayService) {
        this.timeReportDayMapper = timeReportDayResponseMapper;
        this.timeReportDayService = timeReportDayService;
    }

    @GetMapping("{timeReportId}/{date}")
    public TimeReportDayResponseDto findDayReportByDate(
            @PathVariable Long timeReportId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return timeReportDayMapper.mapToDto(timeReportDayService
                .findTimeReportDayByDate(timeReportId, date));
    }

    @PutMapping("/{timeReportId}/{date}")
    public TimeReportDayResponseDto updateDayReport(
            @PathVariable Long timeReportId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody TimeReportDayRequestDto requestDto) {
        TimeReportDay timeReportDay = timeReportDayService
                .updateTimeReportDay(timeReportId, date);
        timeReportDay.setDescription(requestDto.getDescription());
        timeReportDay.setHour(requestDto.getHour());
        timeReportDayService.save(timeReportDay);
        return timeReportDayMapper.mapToDto(timeReportDay);
    }

    @DeleteMapping("/{timeReportId}/{date}")
    public void deleteTimeReportDay(
            @PathVariable Long timeReportId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        timeReportDayService.deleteByTimeReportIdAndDate(timeReportId, date);
    }

    @GetMapping("/{id}/{firstTime}/{secondTime}")
    public List<TimeReportDayResponseDto> getAllTimeReportDaysetweenDates(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate firstTime,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate secondTime) {
        return timeReportDayService.findAllBetweenDates(id, firstTime, secondTime).stream()
                .map(timeReportDayMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/custom-query/by-id/{id}")
    public  List<TimeReportDayResponseDto> getAllByIdCustom(@PathVariable  Long id) {
        return  timeReportDayService.getAllTimeReportDaysByTimeReportIdCustom(id).stream()
                .map(timeReportDayMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/custom-query/by-hours/{hours}")
    public List<TimeReportDayResponseDto> getAllByHoursCustom(@PathVariable Integer hours) {
        return  timeReportDayService.getAllTimeReportDayByHoursCustom(hours).stream()
                .map(timeReportDayMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
