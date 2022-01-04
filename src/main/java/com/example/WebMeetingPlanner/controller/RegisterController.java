package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Exceptions.UserNotFoundExceptionn;

import com.example.WebMeetingPlanner.Model.*;

import com.example.WebMeetingPlanner.Repository.OrganisationRepository;
import com.example.WebMeetingPlanner.Repository.RoleRepository;

import com.example.WebMeetingPlanner.Repository.UserRepository;
import com.example.WebMeetingPlanner.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



import javax.validation.Valid;


import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.List;


@Controller
public class RegisterController {

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    private UserRepository repo;


    @Autowired
    private UserService userService;


    @Autowired
    private OrganisationRepository organisationRepository;





//    @PostMapping("/process_register")
//    public String processRegistration(User user) {
//        userService.saveUserWithDefaultRole(user);
//
//
//        return "register_success";
//    }

    @GetMapping("/register")
    public String main(Model model) {

        List<Organization> listorganisation = organisationRepository.findAll();
        model.addAttribute("listorganisation", listorganisation);

        List<Role> listRoles = userService.getRoles();
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", new User());
        return "signup_form";
    }


    @GetMapping("/process_register")
    public String Process(Model model) {
//        List<User> listUsers = userService.listAll();
//
//        model.addAttribute("listUsers", listUsers);
        return "registered";
    }


    @PostMapping("/process_register")
    public ModelAndView SaveUser(ModelAndView modelAndView,final User user, BindingResult bindingResult) {
    User emailExists = repo.findByEmail(user.getEmail());

        if (emailExists != null || bindingResult.hasErrors() ) {
            modelAndView.setViewName("signup_form");
            bindingResult.rejectValue("email", "emailAlreadyExists");
            modelAndView.setViewName("signup_form");
        }


        else {


            userService.saveUserWithDefaultRole(user);

            modelAndView.setViewName("registered");

        }
        return modelAndView;
    }




}
