package com.mycompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/browse")
    public String showJobsPage() {
        return "browse";
    }

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }
}
