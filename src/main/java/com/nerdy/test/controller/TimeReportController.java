package com.nerdy.test.controller;

import com.nerdy.test.model.TimeReport;
import com.nerdy.test.model.TimeReportDay;
import com.nerdy.test.model.dto.TimeReportRequestDto;
import com.nerdy.test.model.dto.TimeReportResponseDto;
import com.nerdy.test.repository.TimeReportDayRepository;
import com.nerdy.test.service.TimeReportService;
import com.nerdy.test.service.mapper.impl.TimeReportMapper;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time-reports")
public class TimeReportController {
    private final TimeReportService timeReportService;
    private final TimeReportMapper timeReportMapper;
    private final TimeReportDayRepository repository;

    public TimeReportController(TimeReportService timeReportService,
                                TimeReportMapper timeReportMapper, TimeReportDayRepository repository) {
        this.timeReportService = timeReportService;
        this.timeReportMapper = timeReportMapper;
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public TimeReportResponseDto findById(@PathVariable Long id) {
        return timeReportMapper
                .mapToDto(timeReportService.findById(id));
    }

    @GetMapping
    public List<TimeReportResponseDto> getAll() {
        return timeReportService.findAll().stream()
                .map(timeReportMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public TimeReportResponseDto create(@RequestBody TimeReportRequestDto requestDto) {
        return timeReportMapper.mapToDto(timeReportService
                .save(timeReportMapper.mapToModel(requestDto)));
    }

    @PutMapping("/{id}")
    public TimeReportResponseDto updateTimeReport(@PathVariable Long id,
                                                  @RequestBody TimeReportRequestDto requestDto) {
        TimeReport timeReport = timeReportService.findById(id);
        timeReport.setTimeReportName(requestDto.getTimeReportName());
        timeReport.setEmployeeId(requestDto.getEmployeeId());
        timeReportService.save(timeReport);
        return timeReportMapper.mapToDto(timeReport);
    }

    @DeleteMapping("/{id}")
    public void deleteTimeReport(@PathVariable Long id) {
        timeReportService.delete(id);
    }

    @GetMapping("/create")
    public String createItems() {
        TimeReportDay timeReportDay = new TimeReportDay();
        timeReportDay.setDate(LocalDate.of(2000, 12, 12));
        timeReportDay.setHour(8);
        timeReportDay.setDescription("testday");
        timeReportDay.setId(1L);
        timeReportDay.setTimeReportId(1L);
        repository.save(timeReportDay);
        TimeReport timeReport = new TimeReport();
        List<TimeReportDay> list = new ArrayList<>();
        list.add(timeReportDay);
        timeReport.setTimeReportDays(list);
        timeReport.setEmployeeId(1L);
        timeReport.setTimeReportName("test report");
        timeReportService.save(timeReport);
        return "done!";
    }

    @GetMapping("/contains/{letter}")
    public List<TimeReportResponseDto> containsLetters(@PathVariable String letter) {
        return  timeReportService.findTimeReportByLetters(letter).stream()
                .map(timeReportMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
