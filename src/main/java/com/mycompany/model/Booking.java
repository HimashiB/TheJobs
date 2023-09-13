package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.regex.Pattern;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(nullable = false)
    private String time;

    @ManyToOne
    @JoinColumn(name = "country_id",nullable = false)
    private Country country;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobTypes jobTypes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public JobTypes getJobTypes() {
        return jobTypes;
    }

    public void setJobTypes(JobTypes jobTypes) {
        this.jobTypes = jobTypes;
    }
}
