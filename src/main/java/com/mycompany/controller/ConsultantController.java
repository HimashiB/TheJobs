package com.mycompany.controller;

import com.mycompany.model.Consultant;
import com.mycompany.service.ConsultantService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ConsultantController {

    @Autowired
    ConsultantService service;

    @GetMapping("/consultantRegister")
    public String showRegistrationForm(Model model) {
        Consultant consultant = new Consultant();
        model.addAttribute("consultant", new Consultant());
        return "consultantRegister";
    }

    @PostMapping("/consultantRegister/save")
    public String saveConsultant(@ModelAttribute Consultant consultant) {
        String hashedPassword = BCrypt.hashpw(consultant.getPassword(), BCrypt.gensalt(12));
        consultant.setPassword(hashedPassword);
        service.save(consultant);
        return "redirect:/consultantList";
    }

    @GetMapping("/consultantList")
    public ModelAndView getAllConsultants(){
        List<Consultant> listConsultants = service.getAllConsultants();
        return new ModelAndView("consultantList","consultant", listConsultants);
    }

    @RequestMapping("/deleteConsultant/{id}")
    public String deleteConsultant(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/consultantList";
    }

    @RequestMapping("/editConsultant/{id}")
    public String editConsultant(@PathVariable("id") int id, Model model){
        Consultant consultant = service.getConsultantById(id);
        model.addAttribute("consultant", consultant);
        return "consultantEdit";
    }

}
