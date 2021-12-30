package com.nerdy.test.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;

@NamedQuery(name = "TimeReportDay.getByTimeReportIdCustom",
            query = "from TimeReportDay tr where tr.timeReportId = :insertId")

@NamedNativeQuery(name = "TimeReportDay.getByHoursCustom",
                  query = "SELECT * FROM time_report_days WHERE hour = :insertHour",
                 resultClass = TimeReportDay.class)
@Data
@Entity
@Table(name = "time_report_days")
public class TimeReportDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long  timeReportId;
    private String description;
    private int hour;
    private LocalDate date;
}
