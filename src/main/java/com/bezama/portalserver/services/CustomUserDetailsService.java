package com.bezama.portalserver.services;

import com.bezama.portalserver.models.User;
import com.bezama.portalserver.repositories.UserRepository;
import com.bezama.portalserver.repositories.UserRolesRepository;
import com.bezama.portalserver.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRolesRepository userRolesRepository;
    @Autowired
    public CustomUserDetailsService(UserRepository userRepository,UserRolesRepository userRolesRepository) {
        this.userRepository = userRepository;
        this.userRolesRepository=userRolesRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional=userRepository.findByUserName(username);
        if(!userOptional.isPresent()){
            throw new UsernameNotFoundException("No user present with username: "+username);
        }else{
            List<String> userRoles=userRolesRepository.findRoleByUserName(username);
            return new CustomUserDetails(userOptional.get(),userRoles);
        }
    }
}
