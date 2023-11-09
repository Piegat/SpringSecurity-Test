package com.uniamerica.springsecuritytest.config;

import com.uniamerica.springsecuritytest.service.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig  {
    @Autowired
    private UsuarioService userDetailsService;

    protected void authManager(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
             .cors(cors -> cors.disable())
             .authorizeHttpRequests(auth -> auth
                     .requestMatchers("/livre/*")
                .permitAll()
                .anyRequest()
                .authenticated());



        http.csrf(csrf -> csrf.disable());

        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    @Bean
    static PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    UserDetailsService user() {
//        UserDetails user = User.builder()
//                .username("piegat")
//                .password(encoder().encode("123456"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
}
