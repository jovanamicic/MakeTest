package com.maketest.service;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.model.User;

import java.util.List;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
public interface UserService {
    User findOne(String email);
    User save(User user);
    User update(User user);
    User login (UserDTO userToRegister);
    boolean emailExists(String email);
    boolean activateUser(String token);
    User getUserProfile(String token);
}
