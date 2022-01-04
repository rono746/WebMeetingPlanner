package com.example.WebMeetingPlanner.controller;

import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.CustomLoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
 public class LoginController {

    private final CustomLoginFailureHandler customLoginFailureHandler;

    @Autowired
    private UserRepository userRepo;

    public LoginController(CustomLoginFailureHandler customLoginFailureHandler) {
        this.customLoginFailureHandler = customLoginFailureHandler;
    }

    @GetMapping(value = "/login")
    public String login (Model model){



          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null || authentication instanceof AnonymousAuthenticationToken)
            {
                return "signin_form";
            }


        return "indexx";



    }

    // Login form with error
//    @GetMapping("/login?error")
//    public String loginError(Model model) {
//
//        try
//        {
//
//        }
//        catch (LockedException ex){
//            model.addAttribute("loginError",  ex.getMessage());
//        }
//
//
//         return "signin_form";
//    }
}
