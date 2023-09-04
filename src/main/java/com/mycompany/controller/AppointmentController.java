package com.mycompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppointmentController {

    @GetMapping("/appointments")
    public String showAppointmentPage() {
        return "appointments";
    }

    //@PostMapping("/Appointment/save")
}
