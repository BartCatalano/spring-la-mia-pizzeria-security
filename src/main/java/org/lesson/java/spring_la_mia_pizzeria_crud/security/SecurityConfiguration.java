 package org.lesson.java.spring_la_mia_pizzeria_crud.security;

import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
@SuppressWarnings("removal")
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/pizze/create", "/pizze/edit/**").hasAuthority("ADMIN")
            .requestMatchers(HttpMethod.POST, "/pizze/**").hasAuthority("ADMIN")
            .requestMatchers("/ingredienti", "/ingredienti/**").hasAuthority("ADMIN")
            .requestMatchers("/offerte", "/offerte/**").hasAuthority("ADMIN")
            .requestMatchers("/pizze", "/pizze/**").hasAnyAuthority("USER", "ADMIN")
            .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())  // abilita il form di login
        .logout(Customizer.withDefaults())     // abilita logout
        .exceptionHandling(Customizer.withDefaults());

    return http.build();
}


    @Bean
    DatabaseUserDetailsService userDetailsService() {
        return new DatabaseUserDetailsService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

}
