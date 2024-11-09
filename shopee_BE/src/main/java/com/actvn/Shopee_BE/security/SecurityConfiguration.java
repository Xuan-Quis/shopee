package com.actvn.Shopee_BE.security;

import com.actvn.Shopee_BE.security.jwt.AuthEntryPointJwt;
import com.actvn.Shopee_BE.security.jwt.AuthTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration{

    @Bean
    public AuthEntryPointJwt unAuthEntryPoint() {
        return new AuthEntryPointJwt();
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //Không tạo http session
        http.sessionManagement(session -> session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
        ));
        http    .csrf(csrf->csrf.disable())
                .authorizeHttpRequests((requests) ->requests
                        .requestMatchers("/signin").permitAll()
                        .anyRequest().authenticated());

        http.httpBasic(withDefaults()).logout(logout -> logout.permitAll());
        http.headers(header->
                header.frameOptions(frameOptionsConfig ->
                        frameOptionsConfig.sameOrigin())
                );


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        http.exceptionHandling(ex-> ex.authenticationEntryPoint(unAuthEntryPoint()));

        return http.build();

    }
    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public InMemoryUserDetailsManager initData(){
            UserDetails user1 =  User.withUsername("user1")
                    .password(passwordEncoder().encode("password1"))
                    .roles("USER")
                    .build();
            UserDetails userAdmin = User.withUsername("admin")
                    .password(passwordEncoder().encode("admin"))
                    .roles("ADMIN")
                    .build();
            return new InMemoryUserDetailsManager(userAdmin, user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public AuthenticationManager getAuthenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
