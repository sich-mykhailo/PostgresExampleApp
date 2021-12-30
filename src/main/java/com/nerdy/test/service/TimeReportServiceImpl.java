package com.nerdy.test.service;

import com.nerdy.test.exception.DataProcessingException;
import com.nerdy.test.model.TimeReport;
import com.nerdy.test.repository.TimeReportRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class TimeReportServiceImpl implements TimeReportService {
    private final TimeReportRepository timeReportRepository;

    public TimeReportServiceImpl(TimeReportRepository timeReportRepository) {
        this.timeReportRepository = timeReportRepository;
    }

    @Override
    public TimeReport save(TimeReport timeReport) {
        return timeReportRepository.save(timeReport);
    }

    @Override
    public TimeReport findById(Long id) {
        return timeReportRepository.findById(id).orElseThrow(()
                -> new DataProcessingException("Can't find time report by id" + id));
    }

    @Override
    public void delete(Long id) {
        timeReportRepository.deleteById(id);
    }

    @Override
    public List<TimeReport> findAll() {
        return timeReportRepository.findAll();
    }

    @Override
    public List<TimeReport> findTimeReportByLetters(String letter) {
        return timeReportRepository.findTimeReportByTimeReportNameContaining(letter);
    }
}
