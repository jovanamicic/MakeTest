package com.maketest.service_implementations;

import com.google.common.base.Preconditions;
import com.maketest.converter.UserConverter;
import com.maketest.dto.UserDTO;
import com.maketest.dto.UserProfileDTO;
import com.maketest.email.EmailSender;
import com.maketest.exceptions.UserTokenNotFoundException;
import com.maketest.model.Session;
import com.maketest.model.User;
import com.maketest.repository.SessionRepository;
import com.maketest.repository.UserRepository;
import com.maketest.utility.MD5Hash;
import com.maketest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Jovana Micic on 25-Oct-16.
 */
@Service
public class UserServiceImplementation implements UserService {

    private static final int EXPIRATION = 60 * 24; //24 hours
    private static final int SESSION_EXPIRATION = 60 ; //1 hour


    @Autowired
    UserRepository userRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    EmailSender emailSender;

    @Override
    public User findOne(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User update(User user){
        return userRepository.save(user);
    }

    /* Function saves user info in data base, generate token and send user activation link.*/
    @Override
    public User save(User user) {
        if(!isNull(user)) {
            user.setProfilePhotoRelativePath("img/defaultUserPhoto.png");

            //Generating activation token
            String token = UUID.randomUUID().toString();
            Date expireDate = calculateExpiryDate(EXPIRATION);
            user.setToken(token);
            user.setTokenExpireDate(expireDate);

            user = userRepository.save(user);

            //Sending email
      /*      String subject = "Activation link";
            String confirmationUrl = "/api/users/activation?token=" + token;
            String text = "To activate your account click here: http://localhost:8080" + confirmationUrl
                    + "\n Link for activation will expire in 24 hours.\n Make Test website.";

            try {
                emailSender.sendEmail(user.getEmail(), subject, text);
            } catch (MessagingException e) {
                e.printStackTrace();
            } */
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public User login(UserDTO userToRegister) {
        int size = userRepository.findAll().size();
        System.out.println("SIZE: " + size);
        if (userToRegister.getPassword() != null && userToRegister.getEmail()!=null){
            String hashedPassword = MD5Hash.getMD5(userToRegister.getPassword());
            User user = userRepository.findByEmailAndPassword(userToRegister.getEmail(),hashedPassword);
            if (user != null){
                Session userSession = user.getUserSession();
                if(userSession == null) {
                    //creating user session
                    userSession = new Session();
                }
                String sessionToken = UUID.randomUUID().toString();
                Date sessionExpire = calculateExpiryDate(SESSION_EXPIRATION);
                userSession.setSessionExpire(sessionExpire);
                userSession.setSessionToken(sessionToken);
                sessionRepository.save(userSession);

                //saving user session
                user.setUserSession(userSession);
                userRepository.save(user);

                return user;
            }
            else{
                return null;
            }

        }

        else{
           return null;
        }
    }

    @Override
    public boolean emailExists(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null){
           return  true;
        }
        else{
            return false;
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

    @Override
    public com.maketest.model.User getUserProfile(String sessionToken) {
        Session s = sessionRepository.findBySessionToken(sessionToken);
        return userRepository.findByUserSession(s);
    }

    /* Calculate expire date of token */
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    private boolean isNull(User user){
        if (user.getFirstName() == null)
            return true;
        else if(user.getLastName() == null)
            return true;
        else if(user.getEmail() == null)
            return true;
        else if(user.getPassword() == null)
            return true;
        else
            return false;
    }

}
