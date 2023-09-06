package com.mycompany.controller;

import com.mycompany.model.Country;
import com.mycompany.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryRepository repo;

    @GetMapping("/countryList")
    public String listCountries(Model model){
        List<Country> listCountries = repo.findAll();
        model.addAttribute("listCountries",listCountries);
        return"countryList";
    }

    @GetMapping("/country")
    public String showCountryPage(Model model){
        model.addAttribute("country", new Country());
        return "country";
    }

    @PostMapping("country/save")
    public  String addCountries(Country country){
        repo.save(country);
        return "userHome";
    }

}
