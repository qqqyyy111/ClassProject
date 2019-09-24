package com.app.classproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Show user interface
 */
@Controller
public class InterfaceController {
    @RequestMapping(value = "/ProjectUI", method = RequestMethod.GET)
    public String showUI(Model model) {
        return "ProjectUI";
    }
}
