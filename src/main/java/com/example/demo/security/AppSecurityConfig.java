package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.Roles.*;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final PasswordEncoder passwordEncoder;


    public AppSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.DELETE, "/api/v1/products/{productId}").hasRole(ADMIN.name())
                .antMatchers(HttpMethod.PUT, "/api/v1/products/{productId}").hasRole(ADMIN.name())
                .antMatchers("/api/v1/products/add").hasAnyRole(ADMIN.name(), SUPERVISOR.name())
                .antMatchers("/api/v1/products").hasAnyRole(ADMIN.name(), SUPERVISOR.name(), INTERN.name())
                .antMatchers("/api/v1/products{productId}").hasAnyRole(ADMIN.name(), SUPERVISOR.name(), INTERN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        UserDetails timmy = User.builder()
                .username("timmy")
                .password(passwordEncoder.encode("password"))
                .roles(INTERN.name())
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password(passwordEncoder.encode("password"))
                .roles(SUPERVISOR.name())
                .build();

        UserDetails sarah = User.builder()
                .username("sarah")
                .password(passwordEncoder.encode("password"))
                .roles(ADMIN.name())
                .build();

        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager(timmy, john, sarah);

        return userDetailsManager;
    }


}
