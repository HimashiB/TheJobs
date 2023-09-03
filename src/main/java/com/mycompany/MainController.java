package com.mycompany;

import com.mycompany.user.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/jobs")
    public String showJobsPage() {
        return "jobs";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

}
