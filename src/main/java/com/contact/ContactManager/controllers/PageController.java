package com.contact.ContactManager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.contact.ContactManager.entities.User;
import com.contact.ContactManager.forms.UserForm;
import com.contact.ContactManager.helpers.Message;
import com.contact.ContactManager.helpers.MessageType;
import com.contact.ContactManager.service.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PageController {
    
    @Autowired
    private UserService userService;
    
    
    
    @RequestMapping("/home")
    public String home(Model model){
         System.out.println("Home page handle the main page");

         // sending data to view
         model.addAttribute("name", "Substring Technologies");
         model.addAttribute("youtubeChannel", "Learn Code With Durgesh");
         model.addAttribute("githubRepo", "https://github.com/learncodewithdurgesh/");
         return "home";
    }

    // about route

    @RequestMapping("/about")
    public String aboutPage(Model model) {
        // model.addAttribute("isLogin", true);
        System.out.println("About page loading");
        return "about";
    }

    // services

    @RequestMapping("/services")
    public String servicesPage() {
        System.out.println("services page loading");
        return "services";
    }
   
    // contact page

    @GetMapping("/contact")
    public String contactPage() {
        System.out.println("contact page loading");
        return new String("contact");
    }

    // this is showing login page

    @GetMapping("/login")
    public String loginPage() {
        System.out.println("login page loading");
        return new String("login");
    }

    // registration page
    @GetMapping("/register")
    public String registerPage(Model model) {
        System.out.println("signup page loading");
        UserForm userForm= new UserForm();
        // userForm.setName("mahak");
        // // we can also put default data in user
        model.addAttribute("userform", userForm);
        return "register";
    }

    //processing register
    
    @RequestMapping(value = "/do-register", method = RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session){
        System.out.println("registration processing");

        //fetch from data
        //userform
        System.out.println(userForm);

        //validate from data
           if(rBindingResult.hasErrors()){
            return "register";
           }

        //ToDo:: validate userForm

        //save to db
    User user = new User();
    user.setName(userForm.getName());
    user.setEmail(userForm.getEmail());
    user.setPassword(userForm.getPassword());
    user.setAbout(userForm.getAbout());
    user.setPhoneNumber(userForm.getPhoneNumber());
    user.setEnabled(true);
    user.setProfilePic( " ");

    User savedUser = userService.saveUser(user);
    System.out.println("user saved :");

       

    //message ="registration Successful"
      
    //add message
    Message message = Message.builder().content("Registration Successful").type(MessageType.green).build();
    session.setAttribute("message",message);
    
        //redirect to login page
        return "redirect:/register";
    }
}
