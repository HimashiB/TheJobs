package com.mycompany.controller;


import com.mycompany.model.Booking;
import com.mycompany.model.Country;
import com.mycompany.model.JobTypes;
import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.AppointmentService;
import com.mycompany.service.CountryService;
import com.mycompany.service.JobService;
import com.mycompany.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    AppointmentService appService;

    @Autowired
    CountryService countryService;

    @Autowired
    JobService jobService;

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userHome")
    public String showUserHome() {
        return "userHome";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        service.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "login";
    }

    @PostMapping("/loginpage")
    public String loginProcess(@RequestParam("email") String email, @RequestParam("password") String password) {
        User dbUser = userRepository.findByEmail(email);
        boolean isPasswordMatch = BCrypt.checkpw(password, dbUser.getPassword());
        if (isPasswordMatch)
            return "redirect:/userHome";
        else
            return "redirect:/login";
    }


    @GetMapping("/UserHistory")
    public ModelAndView getAllUsers(){
        List <User> list = service.getAllUsers();
        return new ModelAndView("UserHistory","users",list);
    }

    @RequestMapping("/deleteUserList/{id}")
    public String deleteUserList(@PathVariable("id") int id){
        service.deleteById(id);
        return "redirect:/UserHistory";
    }

    @GetMapping("/userAppointment")
    public String showUserAppointment(Model model){
        List<Country>listCountries= countryService.getAllCountries();
        model.addAttribute("listCountries",listCountries);
        List<JobTypes>listJobs = jobService.getAllJobs();
        model.addAttribute("listJobs", listJobs);
        Booking booking = new Booking();
        model.addAttribute("booking", new Booking());
        return "userAppointment";
    }

    @PostMapping("/userAppointment/save")
    public String saveUserAppointment(@ModelAttribute Booking booking){
        appService.save(booking);
        return "redirect:/userBooking";
    }

    @GetMapping("/userBooking")
    public ModelAndView getAllBooking(){
        List<Booking> userBooking = appService.getAllBooking();
        return new ModelAndView("userBooking","userBooking",userBooking);
    }
}



