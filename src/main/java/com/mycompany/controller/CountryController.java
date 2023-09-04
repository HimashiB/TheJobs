package com.mycompany.controller;

import com.mycompany.model.Country;
import com.mycompany.model.User;
import com.mycompany.repository.CountryRepository;
import com.mycompany.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public String showCountryForm(Model model) {
        // create model object to store form data
        Country country = new Country();
        model.addAttribute("country", new Country());
        return "country";
    }
    @PostMapping("/country/save")
    public String saveUser(Country country, RedirectAttributes ra) {
        countryService.save(country);
        ra.addFlashAttribute("message", "The country has been saved successfully.");
        return "redirect:/country";
    }
}
