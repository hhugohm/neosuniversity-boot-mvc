package com.neos.university.neosuniversitybootmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

    @GetMapping("/greetings")
    public String processMessage(
            @RequestParam(defaultValue = "Desconocido", required = false) String name, Model model){

        model.addAttribute("user",name);

        return "message";
    }
}
