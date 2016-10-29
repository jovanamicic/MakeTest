package com.maketest.converter;

import com.maketest.dto.UserDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
public class UserConverter {
    public static User UserDTOToUser(UserDTO userDto){

        if (userDto == null)
            return null;

        User retVal = new User();
        retVal.setActivated(true);
        retVal.setEmail(userDto.getEmail());
        retVal.setFirstName(userDto.getFirstName());
        retVal.setLastName(userDto.getLastName());
        retVal.setPassword(userDto.getPassword());

        return retVal;
    }

    public static UserDTO UserToUserDTO(User user) {
        if (user == null)
            return null;

        UserDTO retVal = new UserDTO();
        retVal.setEmail(user.getEmail());
        retVal.setFirstName(user.getFirstName());
        retVal.setLastName(user.getLastName());
        retVal.setPassword(user.getPassword());

        return retVal;
    }
}
