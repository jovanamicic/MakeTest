package com.maketest.JPA_implementations;

import com.maketest.converter.UserConverter;
import com.maketest.dto.UserDTO;
import com.maketest.model.User;
import com.maketest.repository.UserRepository;
import com.maketest.service.EmailService;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class JPAUserService implements UserService {

    private static final int EXPIRATION = 60 * 24; //24 hours

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmailService emailSender;

    @Override
    public User findOne(int id) {
        User user = userRepository.findOne(id);
        User retVal = null;

        if(user!=null){
            retVal = user;
        }
        else
            throw new IllegalArgumentException("There is no data with id: " + id);
        return retVal;
    }

    /* Function saves user info in data base, generate token and send user activation link.*/
    @Override
    public UserDTO register(UserDTO newUser) {
        //TODO: Uraditi enkripciju password-a
        User user = UserConverter.UserDTOToUser(newUser);
        user.setProfilePhotoRelativePath("img/defaultUserPhoto.png");

        //Generating activation token
        String token = UUID.randomUUID().toString();
        Date expireDate = calculateExpiryDate(EXPIRATION);
        user.setToken(token);
        user.setTokenExpireDate(expireDate);

        userRepository.save(user);

        //Sending email
        String subject = "Activation link";
        String confirmationUrl = "/registrationConfirm.html?token=" + token;
        String text = "To activate your account click here: http://localhost:8080" + confirmationUrl
                + "\n Link for activation will expire in 24 hours.\n Make Test website.";


        try {
            emailSender.send(user.getEmail(), subject, text);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Greksa prilikom slanja mejla");
        }
        UserDTO retVal = UserConverter.UserToUserDTO(user);
        return retVal;
    }

    @Override
    public String checkIfEmailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null){
           return  "exists";
        }
        else{
            return "not exists";
        }
    }

    /* Calculate expire date of token */
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
