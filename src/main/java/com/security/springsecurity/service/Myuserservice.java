/*
package com.security.springsecurity.service;

import com.security.springsecurity.Entity.User;
import com.security.springsecurity.repository.Userrepo;
import com.security.springsecurity.userdetailspack.Userdetailsimplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class Myuserservice implements UserDetailsService {
    @Autowired
    private Userrepo userrepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //we need an reposittory to get the data from db
        User user = userrepo.findbyusername(username);
        if (user == null) {
            throw new UsernameNotFoundException("user 404 ");
        }
        // return new Userdetailsimplementation(user);
        //we need an object of Userdetails so create a class which will implement Userdetails

        // }
    }
}
}
*/
