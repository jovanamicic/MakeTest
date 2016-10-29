package com.maketest.controller;

import com.maketest.dto.UserDTO;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registerNewUser",method = RequestMethod.POST )
    public ResponseEntity<UserDTO> registerNewUser (@RequestBody UserDTO u){
        UserDTO user = userService.register(u);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
}
