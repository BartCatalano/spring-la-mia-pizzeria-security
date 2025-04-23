package org.lesson.java.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;

import org.lesson.java.spring_la_mia_pizzeria_crud.model.User;
import org.lesson.java.spring_la_mia_pizzeria_crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    
    // % recuperare un utente in base allo username

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);

        if (userAttempt.isPresent()){
            return new DatabaseUserDetails(userAttempt.get());
        } else {
            throw new UsernameNotFoundException("No user with username " + " have been found");
        }
    }



}