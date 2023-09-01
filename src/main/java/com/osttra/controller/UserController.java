package com.osttra.controller;

import com.osttra.service.UserService;
import com.osttra.to.UserTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping(path = "/register")
    public ModelAndView Registration(UserTO user) {
        ModelAndView modelAndView = null;
        int res = userService.register(user);
        switch (res) {
            case 0 -> {
                modelAndView = new ModelAndView("index");
                modelAndView.addObject("message", "Successful registration");
            }
            case 1 -> {
                modelAndView = new ModelAndView("registration_page");
                modelAndView.addObject("message", "Email already registered");
            }
            case 2 -> {
                modelAndView = new ModelAndView("registration_page");
                modelAndView.addObject("message", "Something wrong happened please try again");
            }
        }
        return modelAndView;
    }

    @PostMapping(path = "/login")
    public ModelAndView Login(UserTO user, HttpServletRequest request) {
        ModelAndView modelAndView = null;
        int res = userService.login(user);
        switch (res) {
            case 0 -> {
                modelAndView = new ModelAndView("welcome_page");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                if (user.getRole().equalsIgnoreCase("admin")) {
                    ArrayList<UserTO> users = userService.getAllUsers();
                    modelAndView.addObject("users", users);
                }
            }
            case 1 -> {
                modelAndView = new ModelAndView("index");
                modelAndView.addObject("message", "Email Not approved by admin");
            }
            case 2 -> {
                modelAndView = new ModelAndView("index");
                modelAndView.addObject("message", "Email Not Registered");
            }
            case -1 -> {
                modelAndView = new ModelAndView("index");
                modelAndView.addObject("message", "Something wrong happened please try again");
            }
        }
        return modelAndView;
//        }
    }

    @GetMapping("/update_page/{email}")
    public ModelAndView UpdatePage(@PathVariable String email, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        ModelAndView modelAndView = null;
        if (session != null) {
            UserTO user = userService.getUser(email);
            modelAndView = new ModelAndView("update_page");
            modelAndView.addObject("user", user);
        } else {
            modelAndView = new ModelAndView("index");
            modelAndView.addObject("message", "Please login to update profile");
        }
        return modelAndView;
    }

    @PostMapping(path = "/update")
    public ModelAndView update(UserTO user, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserTO userNew = userService.getUser(user.getEmail());

            userNew.setName(user.getName());
            int res = userService.update(userNew);
            UserTO actingUser = (UserTO) session.getAttribute("user");
            switch (res) {
                case 0 -> {
                    modelAndView = new ModelAndView("welcome_page");
                    modelAndView.addObject("message", "Details updated successfully");
                    if (actingUser.getRole().equalsIgnoreCase("admin")) {
                        ArrayList<UserTO> users = userService.getAllUsers();
                        modelAndView.addObject("users", users);
                    }
                    if (actingUser.getEmail().equalsIgnoreCase(userNew.getEmail())) {
                        session.setAttribute("user", userNew);
                    }

                }
                case -1, 1 -> {
                    modelAndView = new ModelAndView("welcome_page");
                    modelAndView.addObject("message", "Something wrong happened please try again");
                }
            }
        }else{
            modelAndView.addObject("message", "Please login to update profile");
        }
        return modelAndView;
    }

    @GetMapping(path = "/delete/{email}")
    public ModelAndView delete(@PathVariable String email, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = request.getSession(false);
        if (session != null) {
            UserTO userNew = userService.getUser(email);
            UserTO actingUser = (UserTO) session.getAttribute("user");
            int res = userService.delete(userNew);
            System.out.println(res);
            if (res == 0) {
                if(actingUser.getRole().equalsIgnoreCase("admin") && !actingUser.getEmail().equalsIgnoreCase(userNew.getEmail())){
                    modelAndView = new ModelAndView("welcome_page");
                    System.out.println("hello");
                }
                else{
                    session.invalidate();
                }
                modelAndView.addObject("message", "Account deleted successfully");
            } else {
                modelAndView = new ModelAndView("welcome_page");
                modelAndView.addObject("message", "Something went wrong please try again");
            }
        } else {
            modelAndView.addObject("message", "Please login to delete profile");
        }
        return modelAndView;
    }

    @GetMapping(path = "/logout")
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = request.getSession(false);
        session.invalidate();
        modelAndView.addObject("message", "Logout Successful");
        return modelAndView;
    }

    @PostMapping(path = "/approve/{email}")
    public ModelAndView approve(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("index");
        HttpSession session = request.getSession(false);
        session.invalidate();
        modelAndView.addObject("message", "Logout Successful");
        return modelAndView;
    }
}

