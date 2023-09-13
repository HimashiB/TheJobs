package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.model.Country;
import com.mycompany.model.JobTypes;
import com.mycompany.repository.BookingRepository;
import com.mycompany.repository.CountryRepository;
import com.mycompany.repository.JobTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookrepo;

    @Autowired
    CountryRepository countryrepo;

    @Autowired
    JobTypesRepository jobrepo;


    @GetMapping("/appointment")
    public String showAppointmentPage(Model model){
        List<Country>listCountries= countryrepo.findAll();
        model.addAttribute("listCountries",listCountries);

        List<JobTypes>listJobs = jobrepo.findAll();
        model.addAttribute("listJobs", listJobs);

        model.addAttribute("booking", new Booking());
        return "appointment";
    }

    @GetMapping("/history")
    public String appointmentHistory(Model model){
        List<Booking> listAppointments = bookrepo.findAll();
        model.addAttribute("listAppointments", listAppointments);
        return "history";
    }

    @PostMapping("/appointment/save")
    public String saveBooking(Booking booking){
        bookrepo.save(booking);
        return "redirect:/history";
    }

    @GetMapping("history/edit/{id}")
    public String showEditAppForm(@PathVariable("id") Integer id, Model model){
        Booking booking = bookrepo.findById(id).get();
        model.addAttribute("booking", booking);

        List<Country>listCountries= countryrepo.findAll();
        model.addAttribute("listCountries",listCountries);

        List<JobTypes>listJobs = jobrepo.findAll();
        model.addAttribute("listJobs", listJobs);

        model.addAttribute("booking", new Booking());

        return "appointment";
    }

}
