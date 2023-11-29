package com.example.springboot.crudrestapi.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // for authentication - In mysql, user details stored for authentication via
    // spring security-jdbc
    // no more hardcoded user details
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {

        return new JdbcUserDetailsManager(dataSource);
    }

    // for authentication - In Memory user details stored for authentication
    // hardcoded user details
    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {
    // UserDetails john = User.builder()
    // .username("john")
    // .password("{noop}john")
    // .roles("EMPLOYEE")
    // .build();

    // UserDetails mary = User.builder()
    // .username("mary")
    // .password("{noop}mary")
    // .roles("EMPLOYEE", "MANAGER")
    // .build();

    // UserDetails sumit = User.builder()
    // .username("sumit")
    // .password("{noop}sumit")
    // .roles("EMPLOYEE", "MANAGER", "ADMIN")
    // .build();

    // return new InMemoryUserDetailsManager(john, mary, sumit);
    // }

    // filters for role-based authorization on endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSec) throws Exception {
        httpSec.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));

        // use basic authentication
        httpSec.httpBasic(Customizer.withDefaults());

        // disable csrf, as in genearal not required for stateless rest apis
        httpSec.csrf(csrf -> csrf.disable());

        return httpSec.build();
    }
}
