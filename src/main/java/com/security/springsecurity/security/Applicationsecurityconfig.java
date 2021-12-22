package com.security.springsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
//It enables web security
@EnableWebSecurity
public class Applicationsecurityconfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public Applicationsecurityconfig( PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //select control+o to see the overrided methods and add httpsecurity
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .authorizeRequests()
             .antMatchers("/","index","/css/*","/js/*")
             .permitAll()
             .anyRequest()
             .authenticated()
             .and()
             .httpBasic();
    }
    //for all the requsts first anuthenticate the requests
    //here what we are saying is all the authorized users (it may be any request post,delete,get..) must be authenticated
    //and enforcing the authenticity to the client with httpbasic

    // for all the random generated passwords are stored in inmemory databases for the default user
//for developing applications we have bumch of users and need a real database
    //store the users in real databases
    // user must have unique name , password encrypt,role of the user,authorities and more

//to create a user override Userdetailsservice
    //this userdetialservice (DB)is how you retrieve data from the database
    //from User.builder give all the details and build it
    //pass the userdetails obj to InmemoryDetialsManager(DB) now db has that user
    //we need to encrypt the password for that
    //@Bean is tagged here which means we are creating an instance of the particular class and registers in spring ioc container
    //Here it's an interface so we are creating an instance for the subclass that implements this Userdetailservice
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ravi= User.builder()
                .username("ravinanda")
                .password(passwordEncoder.encode("sree@12"))
                .roles("student")
                .build();
        return new InMemoryUserDetailsManager(ravi);
    }
    @Autowired
    private UserDetailsService userDetailsService;
    //how the ^^ above service connect to the doa layer
    //so create a service class and implement the UserDetailservice
    @Bean
    //there is an implemetation provider for the Authenticationprovider is Daoauthenticationprovider
    public AuthenticationProvider authenticationProvider(){
        //this DaoAuthentication provider is responsible to talk to datbase and verify it
        //we need a serviceprovider in which configuration(Assume as a controller) talks to service and from service to daoentity to db

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //mention servicedetail service to talk with dao
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }
}
//keypoint: In Basic Auth for every single request , server validates the username and password
//Drawback we can't simply logout
