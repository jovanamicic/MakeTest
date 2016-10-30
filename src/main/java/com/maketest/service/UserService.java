package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    User findOne(int id);
    UserDTO register (UserDTO newUser);
    String checkIfEmailExists(String email);
}
