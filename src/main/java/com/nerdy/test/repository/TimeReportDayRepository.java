package com.nerdy.test.repository;

import com.nerdy.test.model.TimeReportDay;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeReportDayRepository extends JpaRepository<TimeReportDay, Long> {
    @Query(value = "SELECT * "
            + "FROM time_report_days "
            + "WHERE time_report_id = ?1 AND date = ?2", nativeQuery = true)
    Optional<TimeReportDay> findTimeReportDay(Long timeReportId, LocalDate date);

    @Query(value = "SELECT * "
            + "FROM time_report_day "
            + "WHERE time_report_id = ?1 AND "
            + "date BETWEEN ?2 AND ?3", nativeQuery = true)
    List<TimeReportDay> findTimeReportDaysBetween(Long timeReportId,
                                                  LocalDate firstDate,
                                                  LocalDate secondDate);

    List<TimeReportDay> getByTimeReportIdCustom(Long insertId);

    List<TimeReportDay> getByHoursCustom(Integer insertHour);
}
