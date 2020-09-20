package com.sharemoment.ws.service;

import com.sharemoment.ws.configuration.ShareMomentUserDetails;
import com.sharemoment.ws.entity.UserEntity;
import com.sharemoment.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userInDB = userRepository.findByUserName(username);
        if (userInDB == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ShareMomentUserDetails(userInDB);
    }
}
