package com.contact.ContactManager.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact.ContactManager.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    // private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    // user dashbaord page

    @RequestMapping(value = "/dashboard")
    public String userDashboard() {
        System.out.println("User dashboard");
        return "user/dashboard";
    }

    // user profile page

    @RequestMapping(value = "/profile")
    public String userProfile() {
        System.out.println("User profile");
        return "user/profile";
    }

    // user add contacts page

    // user view contacts

    // user edit contact

    // user delete contact
}
// Model model, Authentication authentication
