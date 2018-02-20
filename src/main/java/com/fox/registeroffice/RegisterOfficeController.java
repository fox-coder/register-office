package com.fox.registeroffice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class RegisterOfficeController {

    @RequestMapping("/")
    public String mainPage(Map<String, Object> model) {
        return "register_office";
    }

}
