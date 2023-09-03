package com.mycompany.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register/save")
    public String saveUser(User user){
        String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(12));
        user.setPassword(hashedPassword);
        service.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(User user) {
        return "login";
    }

    //Handler for Login Process
    @PostMapping("/loginpage")
    public String loginProcess(@RequestParam("email")String email, @RequestParam("password") String password){
        User dbUser = userRepository.findByEmail(email);
        Boolean isPasswordMatch=BCrypt.checkpw(password,dbUser.getPassword());
        if(isPasswordMatch)
            return "redirect:/userHome";
        else
            return "redirect:/login";
    }
    @GetMapping("/userHome")
    public String showUserHome() {
        return "userHome";
    }
}
