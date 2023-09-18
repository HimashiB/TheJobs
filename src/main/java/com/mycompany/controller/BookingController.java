package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.model.Country;
import com.mycompany.model.JobTypes;
import com.mycompany.repository.BookingRepository;
import com.mycompany.repository.CountryRepository;
import com.mycompany.repository.JobTypesRepository;
import com.mycompany.service.AppointmentService;
import com.mycompany.service.CountryService;
import com.mycompany.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    AppointmentService appService;

    @Autowired
    CountryService countryService;

    @Autowired
    JobService jobService;


    @GetMapping("/appointment")
    public String showAppointmentPage(Model model){
        List<Country>listCountries= countryService.getAllCountries();
        model.addAttribute("listCountries",listCountries);
        List<JobTypes>listJobs = jobService.getAllJobs();
        model.addAttribute("listJobs", listJobs);
        Booking booking = new Booking();
        model.addAttribute("booking", new Booking());
        return "appointment";
    }

    @PostMapping("/appointment/save")
    public String saveAppointment(@ModelAttribute Booking booking){
        appService.save(booking);
        return "redirect:/history";
    }

    @GetMapping("/history")
    public ModelAndView getAllBooking(){
        List<Booking> listAppointments = appService.getAllBooking();
        return new ModelAndView("history","listAppointments",listAppointments);
    }

    @RequestMapping("/deleteAppointmentList/{id}")
    public String deleteAppointmentList(@PathVariable("id") int id){
        appService.deleteById(id);
        return "redirect:/history";
    }

    @RequestMapping("/editAppointment/{id}")
    public String editCountry(@PathVariable("id") int id, Model model){
        Booking booking = appService.getBookingById(id);
        List<Country>listCountries= countryService.getAllCountries();
        model.addAttribute("listCountries",listCountries);
        List<JobTypes>listJobs = jobService.getAllJobs();
        model.addAttribute("listJobs", listJobs);
        model.addAttribute("booking", booking);
        return "appointmentEdit";
    }


    @PostMapping("/appointment/update")
    public String updateAppointment(@ModelAttribute Booking booking){
        appService.save(booking);
        return "redirect:/history";
    }


}
