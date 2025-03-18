package com.rk.dailydish.security;

import com.rk.dailydish.entity.User;
import com.rk.dailydish.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomeUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneString) throws UsernameNotFoundException {
        String phone;
        try {
            phone = phoneString;
        } catch (NumberFormatException e) {
            throw new UsernameNotFoundException("Invalid phone number format");
        }

        Optional<User> userOptional = userRepository.findByPhone(phone);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with phone: " + phone));

        return new org.springframework.security.core.userdetails.User(
                String.valueOf(user.getPhone()), 
                user.getPassword(),
                user.getAuthorities()
        );
    }
}
