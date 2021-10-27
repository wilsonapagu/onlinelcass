package com.bipro.ocm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wel")
public class WelcomeController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String create2(Model model){
        System.out.println(55555555);
        return "hello";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create (Model model){
        System.out.println(555555);
        return "form/formCreate";
    }
}
