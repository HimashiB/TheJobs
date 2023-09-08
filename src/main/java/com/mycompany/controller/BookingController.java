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
    private BookingRepository repo;

    @Autowired
    CountryRepository countryrepo;

    @Autowired
    JobTypesRepository jobrepo;


    @GetMapping("/appointment")
    public String showAppointmentPage(Model model){
        List<Country>listCountries= countryrepo.findAll();
        model.addAttribute("booking", new Booking());
        model.addAttribute("listCountries",listCountries);
        List<JobTypes>listJobs = jobrepo.findAll();
        model.addAttribute("booking", new Booking());
        model.addAttribute("listJobs", listJobs);

        return "appointment";
    }

    @GetMapping("/history")
    public String appointmentHistory(Model model){
        List<Booking> listAppointments = (List<Booking>) repo.findAll();
        model.addAttribute("listAppointments", listAppointments);
        return "history";
    }

    @PostMapping("/appointment/save")
    public String saveBooking(Booking booking){
        repo.save(booking);
        return "redirect:/history";
    }

    @GetMapping("history/edit/{id}")
    public String showEditAppForm(@PathVariable("id") Integer id, Model model){
        System.out.println("This is the id" + id);
        Optional<Booking> bookingOptional = repo.findById(id);
        model.addAttribute("booking", bookingOptional.get());
        System.out.println("Booking Country" + bookingOptional);
        model.addAttribute("listCountries", countryrepo.findAll());
        model.addAttribute("listJobs", jobrepo.findAll());
        /*LocalDate bookingDate = bookingOptional.get().getDate();
        model.addAttribute("date", bookingDate);*/
        return "appointment";
    }
}
