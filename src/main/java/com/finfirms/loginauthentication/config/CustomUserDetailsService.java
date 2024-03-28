package com.finfirms.loginauthentication.config;



import com.finfirms.loginauthentication.dao.UserRepository;
import com.finfirms.loginauthentication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential =  userRepository.findByName(username);
        return credential.map(CustomUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"+ username));
    }
}
