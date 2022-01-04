package com.example.WebMeetingPlanner.controller;


import com.example.WebMeetingPlanner.Model.Organization;
import com.example.WebMeetingPlanner.Model.User;
import com.example.WebMeetingPlanner.Repository.OrganisationRepository;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Controller
public class OrganisationController {



    @Autowired
    private OrganisationRepository organisationRepository;




    @GetMapping("/Addorganisation")
    public String AddOrganisation(Model model) {
        model.addAttribute("organisation", new Organization());

        return "AddOrganisation";
    }




    @PostMapping("/Saveorganisation")
    public  String OrganisationSave(ModelAndView modelAndView,Organization organisation, Model model,BindingResult bindingResult, final Organization organization)
    {
 
        try{
            organisationRepository.save(organisation);
        } catch (DataIntegrityViolationException e) {
 
            model.addAttribute("message", "Duplicate Entry.");
 
        }



        return "AddOrganisationSuccess";
    }


//    @PostMapping("/Saveorganisation")
//    public ModelAndView Saveorganisation(ModelAndView modelAndView, final Organization organization, BindingResult bindingResult) {
//        Organization organisationExist = organisationRepository.findByOrganisation(organization.getOrganisation());
//
//        if (organisationExist != null || bindingResult.hasErrors() ) {
//            modelAndView.setViewName("AddOrganisation");
//            bindingResult.rejectValue("organisation", "OrganisationAlreadyExist");
//            modelAndView.setViewName("AddOrganisation");
//        }
//
//
//        else {
//
//
//            organisationRepository.save(organization);
//
//            modelAndView.setViewName("savedorganisation");
//
//        }
//        return modelAndView;
//    }






    @GetMapping("/Savedorganisation")
    public String AddOrganisations(Model model)
    {
        List<Organization> addorganisation=organisationRepository.findAll();
        model.addAttribute("listorganisation",addorganisation);

        return "savedorganisation";
    }


}
