package com.jayesh.hospitalmanagementsystemapi.security;

import com.jayesh.hospitalmanagementsystemapi.config.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import com.jayesh.hospitalmanagementsystemapi.config.AppConfig;
@Configuration
@EnableWebSecurity
public class WebSecurity {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurity(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth->
                auth.requestMatchers("/public/**").permitAll()
                        .requestMatchers("/patients/**").hasAnyRole("admin","patient")
                        .requestMatchers("/admin/**").authenticated()
                        .requestMatchers("/doctors/**").hasAnyRole("admin","doctor")
                        .requestMatchers("/appointments/**").hasAnyRole("admin","doctor","patient")
                        .requestMatchers("/insurances/**").hasAnyRole("admin","doctor","patient")
                        ).httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("patient")
                .password(passwordEncoder.encode("pass"))
                .roles("patient","admin")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }
}
