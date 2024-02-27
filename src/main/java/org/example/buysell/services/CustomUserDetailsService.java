package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) userDao.getUserByEmail(email);
    }
}
