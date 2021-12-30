package com.nerdy.test.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class TimeReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long employeeId;
    private String timeReportName;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.MERGE)
    private List<TimeReportDay> timeReportDays;
}
