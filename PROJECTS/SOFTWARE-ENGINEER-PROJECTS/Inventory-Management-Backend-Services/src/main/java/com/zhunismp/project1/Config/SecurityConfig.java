package com.zhunismp.project1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        // tell spring security to use authentication with JDBC datasource
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a username,password by username
        // ? is placeholder for userid input from user
        // user need enabled field. Hence user always active so just select true as enabled
        jdbcUserDetailsManager.setUsersByUsernameQuery("select username,password,'true' as enabled from users where username=?");

        // define query to retrieve a username,authorities/roles by username
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select username,authority from authorities where username=?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(configure ->
                        configure
                                .requestMatchers("/","/orders","/products").hasRole("EMPLOYEE")
                                .requestMatchers("/orders/add/**","/products/add/**").hasRole("MANAGER")
                                .anyRequest() // every user need to authenticate
                                .authenticated()
        );

        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf -> csrf.disable());

        return httpSecurity.build();
    }

}
