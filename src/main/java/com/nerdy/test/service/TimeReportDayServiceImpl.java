package com.nerdy.test.service;

import com.nerdy.test.exception.DataProcessingException;
import com.nerdy.test.model.TimeReport;
import com.nerdy.test.model.TimeReportDay;
import com.nerdy.test.repository.TimeReportDayRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TimeReportDayServiceImpl implements TimeReportDayService {
    private final TimeReportDayRepository timeReportDayRepository;
    private final TimeReportService timeReportService;

    public TimeReportDayServiceImpl(TimeReportDayRepository timeReportDayRepository,
                                    TimeReportService timeReportService) {
        this.timeReportDayRepository = timeReportDayRepository;
        this.timeReportService = timeReportService;
    }

    @Override
    public TimeReportDay save(TimeReportDay timeReportDay) {
        return timeReportDayRepository.save(timeReportDay);
    }

    @Override
    public TimeReportDay findById(Long id) {
        return timeReportDayRepository.findById(id).orElseThrow(()
                -> new DataProcessingException("Can't find time report day by id" + id));
    }

    @Override
    public void deleteByTimeReportIdAndDate(Long timeReportId, LocalDate date) {
        TimeReport timeReport = timeReportService.findById(timeReportId);
        TimeReportDay timeReportDay = timeReportDayRepository
                .findTimeReportDay(timeReportId, date)
                .orElseThrow(() -> new DataProcessingException("Can't find time report day by date " + date));
        timeReport.getTimeReportDays().remove(timeReportDay);
        timeReportService.save(timeReport);
        timeReportDayRepository.delete(timeReportDay);
    }

    @Override
    public TimeReportDay findTimeReportDayByDate(Long id, LocalDate date) {
        return timeReportDayRepository.findTimeReportDay(id, date)
                .orElseThrow(() ->
                        new DataProcessingException("Can't find time report day by date " + date));
    }

    @Override
    public TimeReportDay updateTimeReportDay(Long id, LocalDate date) {
        TimeReport timeReport = timeReportService.findById(id);
        TimeReportDay timeReportDay = new TimeReportDay();
        timeReportDay.setTimeReportId(id);
        timeReportDay.setDate(date);
        timeReport.getTimeReportDays().add(timeReportDay);
        timeReportDayRepository.save(timeReportDay);
        timeReportService.save(timeReport);
        return timeReportDay;
    }

    @Override
    public List<TimeReportDay> findAllBetweenDates(Long timeReportId,
                                                   LocalDate firstDate,
                                                   LocalDate secondDate) {
        return timeReportDayRepository.findTimeReportDaysBetween(
                timeReportId,
                firstDate,
                secondDate);
    }
}
