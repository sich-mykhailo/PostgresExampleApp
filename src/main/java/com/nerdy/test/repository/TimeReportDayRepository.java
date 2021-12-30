package com.nerdy.test.repository;

import com.nerdy.test.model.TimeReportDay;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeReportDayRepository extends JpaRepository<TimeReportDay, Long> {
    Optional<TimeReportDay> findTimeReportDayByTimeReportIdAndDate(Long timeReportId, LocalDate date);

    List<TimeReportDay> findTimeReportDaysByTimeReportIdAndDateBetween(Long timeReportId,
                                                                       LocalDate firstDate,
                                                                       LocalDate secondDate);
}
