package com.example.RunningApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration // Indicates that this is a configuration class
@EnableWebSecurity // Enables Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource; // Autowires the DataSource bean

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // Configures authorization rules
                .antMatchers("/users/**").authenticated() // Specifies that requests to /users/** require authentication
                .antMatchers("/").permitAll() // Specifies that requests to / are allowed for all users
                .and()
                .formLogin(); // Enables form-based login
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication() // Configures authentication using JDBC
                .dataSource(dataSource) // Specifies the DataSource to use
                .usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?") // Specifies the SQL query to retrieve user details
                .authoritiesByUsernameQuery("SELECT username, authority FROM authorities WHERE username = ?") // Specifies the SQL query to retrieve user authorities
                .passwordEncoder(passwordEncoder()); // Specifies the password encoder to use
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Creates a BCryptPasswordEncoder bean
    }
}