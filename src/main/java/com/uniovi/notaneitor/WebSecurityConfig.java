package com.uniovi.notaneitor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                    .antMatchers("/mark/add").hasAuthority("ROLE_PROFESSOR")
                    .antMatchers("/mark/edit/*").hasAuthority("ROLE_PROFESSOR")
                    .antMatchers("/mark/delete/*").hasAuthority("ROLE_PROFESSOR")

                    .antMatchers("/professor/add").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/professor/edit").hasAuthority("ROLE_ADMIN")
                    .antMatchers("/professor/details").hasAnyAuthority("ROLE_ADMIN","ROLE_PROFESSOR")

                    .antMatchers("/mark/**").hasAnyAuthority("ROLE_STUDENT", "ROLE_PROFESSOR", "ROLE_ADMIN")
                    .antMatchers("/user/**").hasAnyRole("ADMIN")
                    .antMatchers("/css/**", "/images/**", "/script/**", "/", "/signup", "/login/**").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .and()
                .logout()
                .permitAll();
    }

}