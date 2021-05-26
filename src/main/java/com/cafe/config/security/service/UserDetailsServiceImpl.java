package com.cafe.config.security.service;

import com.cafe.model.entity.user.User;
import com.cafe.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if (optionalUser.isEmpty())
            optionalUser = userRepository.findUserByEmail(username);
        if (optionalUser.isEmpty())
            optionalUser = userRepository.findUserByPhoneNumber(username);

        if (optionalUser.isEmpty())
            throw new UsernameNotFoundException("Username not found.");

        User user = optionalUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

}