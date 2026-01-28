//package com.practice.hospitalmanagement.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class SecurityFilter {
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http    //needed for RestAPI
//                .csrf(csrf -> csrf.disable())
//                //Autorization rules
////       ------------------------------------------- @EnableWebSecurity → turns Spring Security ON
////
////        -------------------------------------------SecurityFilterChain → defines security rules
////
////        -------------------------------------------csrf.disable() → required for REST APIs
////
////        -------------------------------------------permitAll() → allow without login
////
////        -------------------------------------------authenticated() → must be logged in
//                .authorizeHttpRequests(
//                        authorizeRequests ->
//                                authorizeRequests.requestMatchers("/login").permitAll()
//                                .anyRequest().authenticated());
//        return http.build();
//    }
//    UsernamePasswordAuthenticationToken aut= new UsernamePasswordAuthenticationToken("admin","password");
//}
