package com.vedegiska.gateway_microservice.config;

import com.vedegiska.gateway_microservice.filter.JWTAuthenticationFilter;
import com.vedegiska.gateway_microservice.filter.JWTLoginFilter;
import com.vedegiska.gateway_microservice.repo.RoleRepository;
import com.vedegiska.gateway_microservice.repo.UserRepository;
import com.vedegiska.gateway_microservice.service.inter.IAppUserDetailsService;
import com.vedegiska.gateway_microservice.service.inter.ITokenAuthenticationService;
import com.vedegiska.gateway_microservice.service.model.IAppUserDetailsServiceImpl;
import com.vedegiska.gateway_microservice.service.model.TokenAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IAppUserDetailsService appUserDetailsService() {
        return new IAppUserDetailsServiceImpl(userRepository, roleRepository);
    }

    @Bean
    public ITokenAuthenticationService tokenAuthenticationService() {
        return new TokenAuthenticationService(userRepository);
    }

    //возможна ошибка в работе фильтров из-за объявления их как бинов
    @Bean
    public JWTLoginFilter jwtLoginFilter() throws Exception {
        return new JWTLoginFilter(authenticationManagerBean(),
                tokenAuthenticationService());
    }

    @Bean
    public JWTAuthenticationFilter jwtAuthenticationFilter() {
        return new JWTAuthenticationFilter(tokenAuthenticationService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/users/login", "/users/registration")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtLoginFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
                //.addFilterBefore(new JWLoginFilter(authenticationManagerBean(), tokenAuthenticationService()), UsernamePasswordAuthenticationFilter.class)
                //.addFilterBefore(new JWTAuthenticationFilter(tokenAuthenticationService()), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(appUserDetailsService())
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
