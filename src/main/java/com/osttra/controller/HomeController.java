package com.osttra.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    @GetMapping("/")
    public String LoginPage(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        System.out.println(session);
        return "index";
    }

    @GetMapping("/registration_page")
    public String RegistrationPage() {
        System.out.println("in registration mapping");
        return "registration_page";
    }

}
