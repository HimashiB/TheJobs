package com.mycompany.controller;

import com.mycompany.model.Country;
import com.mycompany.repository.CountryRepository;
import com.mycompany.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping("/country")
    public String showCountryForm(Model model){
        Country country = new Country();
        model.addAttribute("country", new Country());
        return "country";
    }

    @PostMapping("/country/save")
        public String saveCountry(@ModelAttribute Country country){
        countryService.save(country);
        return "redirect:/countryList";
    }

    @GetMapping("/countryList")
    public ModelAndView getAllCountries(){
        List<Country> listCountries = countryService.getAllCountries();
        return new ModelAndView("countryList","country", listCountries);
    }

    @RequestMapping("/deleteCountryList/{id}")
    public String deleteCountryList(@PathVariable("id") int id){
        countryService.deleteById(id);
        return "redirect:/countryList";
    }

    @RequestMapping("/editCountry/{id}")
    public String editCountry(@PathVariable("id") int id, Model model){
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        return "countryEdit";
    }
}
