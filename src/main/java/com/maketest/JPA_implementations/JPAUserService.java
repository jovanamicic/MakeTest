package com.maketest.JPA_implementations;

import com.maketest.converter.UserConverter;
import com.maketest.dto.UserDTO;
import com.maketest.model.User;
import com.maketest.repository.UserRepository;
import com.maketest.service.EmailService;
import com.maketest.service.MD5Hash;
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

    /* Function saves user info in data base, generate token and send user activation link.*/
    @Override
    public UserDTO register(UserDTO newUser) {
        User user = UserConverter.UserDTOToUser(newUser);
        String hashedPassword = MD5Hash.getMD5(user.getPassword());
        user.setPassword(hashedPassword);
        user.setProfilePhotoRelativePath("img/defaultUserPhoto.png");

        //Generating activation token
        String token = UUID.randomUUID().toString();
        Date expireDate = calculateExpiryDate(EXPIRATION);
        user.setToken(token);
        user.setTokenExpireDate(expireDate);

        userRepository.save(user);

        //Sending email
        String subject = "Activation link";
        String confirmationUrl = "/registration-confirmation?token=" + token;
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
    public UserDTO login(UserDTO userToRegister) {
        UserDTO retVal = null;
        String hashedPassword = MD5Hash.getMD5(userToRegister.getPassword());
        User user = userRepository.findByEmailAndPassword(userToRegister.getEmail(),hashedPassword);
        if (user != null){
            retVal = UserConverter.UserToUserDTO(user);
            return retVal;
        }
        else{
           return null;
        }
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

    @Override
    public boolean activateUser(String token) {
        boolean retVal = false;
        User user = userRepository.findByToken(token);
        if (user != null){
            user.setActivated(true);
            userRepository.save(user);
            retVal = true;
        }
        return retVal;
    }

    /* Calculate expire date of token */
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

}
