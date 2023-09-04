package com.mycompany.controller;

import com.mycompany.model.Booking;
import com.mycompany.model.Country;
import com.mycompany.repository.BookingRepository;
import com.mycompany.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository repo;

    @Autowired
    CountryRepository countryrepo;

    @GetMapping("/appointments")
    public String showAppointmentPage(Model model){
        List<Country>listCountries= countryrepo.findAll();
        model.addAttribute("booking", new Booking());
        model.addAttribute("listCountries",listCountries);
        return "appointments";
    }

    @GetMapping("/history")
    public String showHistoryPage(){
        return "history";
    }

    @PostMapping("/bookings/save")
    public String saveBooking(Booking booking){
        repo.save(booking);
        return "redirect:/history";
    }
}
