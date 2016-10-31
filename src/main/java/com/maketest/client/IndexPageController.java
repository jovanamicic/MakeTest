package com.maketest.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Controller
public class IndexPageController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexPage(){
        return "login";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationPage(){
        return "registration";
    }
}

