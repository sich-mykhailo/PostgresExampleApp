package com.nerdy.test.service;

import com.nerdy.test.model.TimeReport;
import java.util.List;

public interface TimeReportService {
    TimeReport save(TimeReport timeReport);

    TimeReport findById(Long id);

    void delete(Long id);

    List<TimeReport> findAll();

    List<TimeReport> findTimeReportByLetters(String letter);
}
