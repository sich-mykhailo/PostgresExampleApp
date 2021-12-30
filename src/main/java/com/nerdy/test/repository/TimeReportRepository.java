package com.nerdy.test.repository;

import com.nerdy.test.model.TimeReport;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeReportRepository extends JpaRepository<TimeReport, Long> {
    List<TimeReport> findTimeReportByTimeReportNameContaining(String letters);
}
