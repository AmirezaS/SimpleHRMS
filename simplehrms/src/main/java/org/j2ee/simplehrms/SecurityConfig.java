package org.j2ee.simplehrms;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
 
// ════════════════════════════════════════════════════════════════════════════
// PHASE 1 — Security is OFF
// Every URL is open. No token needed. Use this while you learn and test.
// When ready to turn security ON, replace this file with SecurityConfig_ON.java
// ════════════════════════════════════════════════════════════════════════════
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    
 
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**")) 
            .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))         
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()                   // ← EVERY URL is open, no token needed
            ).formLogin(withDefaults())
            .build();
    }
 
    // @Bean
    // SecurityFilterChain configure(HttpSecurity http) throws Exception {
    //     http.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
    //       .authorizeHttpRequests(auth -> auth
    //         .anyRequest().authenticated())
    //       .formLogin(withDefaults());

    //     return http.build();
    // }


    // We still need a PasswordEncoder bean because DataInitializer uses it
    // to hash the admin password when it seeds the database.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
