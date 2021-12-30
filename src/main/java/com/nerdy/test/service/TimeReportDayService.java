package com.nerdy.test.service;

import com.nerdy.test.model.TimeReportDay;
import java.time.LocalDate;
import java.util.List;

public interface TimeReportDayService {
    TimeReportDay save(TimeReportDay timeReportDay);

    TimeReportDay findById(Long id);

    void deleteByTimeReportIdAndDate(Long timeReportId, LocalDate date);

    TimeReportDay findTimeReportDayByDate(Long id, LocalDate date);

    TimeReportDay updateTimeReportDay(Long id, LocalDate date);

    List<TimeReportDay> findAllBetweenDates(Long timeReportId,
                                            LocalDate firstDate,
                                            LocalDate secondDate);
}
