package com.mycompany.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@SuppressWarnings("JpaDataSourceORMInspection")
@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate date;

    private String time;

    @ManyToOne
    @JoinColumn(name="country_id")
    private  Country country;

    @ManyToOne
    @JoinColumn(name="job_id")
    private  JobTypes jobTypes;

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
