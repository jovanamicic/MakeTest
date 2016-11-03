package com.maketest.converter;

import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.model.User;

/**
 * Created by Jovana Micic on 29-Oct-16.
 */
public class UserConverter {

    public static UserProfileDTO userToUserProfileDTO(User user){
        if(user == null){
            return null;
        }
        else {
            UserProfileDTO retVal = new UserProfileDTO();
            retVal.setEmail(user.getEmail());
            retVal.setFirstName(user.getFirstName());
            retVal.setLastName(user.getLastName());
            retVal.setProfilePhotoRelativePath(user.getProfilePhotoRelativePath());
            return retVal;
        }

    }

    public static User UserDTOToUser(UserDTO userDto){

        if (userDto == null)
            return null;

        User retVal = new User();
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
