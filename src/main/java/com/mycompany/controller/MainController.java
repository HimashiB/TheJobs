package com.mycompany.controller;

import com.mycompany.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/jobs")
    public String showJobsPage() {
        return "jobs";
    }


}
