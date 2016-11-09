package com.maketest.client;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Controller
public class IndexPageController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String indexPage(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(){
        return "registration";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage(@RequestParam String mtt)
    {
        return "home";
    }
}

