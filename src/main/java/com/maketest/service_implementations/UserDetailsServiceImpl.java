package com.maketest.service_implementations;

import com.maketest.model.User;
import com.maketest.model.UserAuthority;
import com.maketest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/*
 * Created by Jovana Micic on 01-Nov-16.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email); //I will use email as username

        if (user == null){
            throw new UsernameNotFoundException(String.format("No user found with email:"+ email));
        }
        else {
            java.util.List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

            for (UserAuthority ua : user.getUserAuthorities()){
                grantedAuthorities.add(new SimpleGrantedAuthority(ua.getAuthority().getName()));
            }
            System.out.println("User authorities are: " + grantedAuthorities.toString());
            return new org.springframework.security.core.userdetails.User(
                    user.getEmail(),
                    user.getPassword(),
                    grantedAuthorities);
        }
    }
}
