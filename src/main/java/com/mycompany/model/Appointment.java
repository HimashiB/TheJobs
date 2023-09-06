package com.mycompany.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer appId;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date date;

    @Column(nullable = false)
    private String time;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "country", referencedColumnName = "country_Name")
    private Country country;


    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    @Override
    public String toString() {
        return "Appointment{" +
                "appId=" + appId +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", country=" + country +
                '}';
    }
}
