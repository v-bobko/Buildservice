package ru.buildservice.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;

    }

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
          //    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
          //   .and()
                .authorizeRequests()
                .antMatchers("/**", "index", "/contacts", "/css/**", "/css/#", "/js/**", "/images/**", "/icon/**").permitAll()
                .antMatchers("/customer/*", "/css/*", "/css/#", "/js/*", "/images/*").hasRole("CUSTOMER")
                .antMatchers("/engineer/*", "/css/*", "/css/#", "/js/*", "/images/*").hasRole("ADMIN")
                .antMatchers("/worker/*", "/css/*", "/css/#", "/js/*", "/images/*").hasRole("WORKER")

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(successHandler);
//                .defaultSuccessUrl("/customer/objects",true);
    }


    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails bobko = User.builder()
                .username("bobko")
                .password(passwordEncoder.encode("bobko"))
                .roles("ADMIN")
                .build();
        UserDetails worker = User.builder()
                .username("worker")
                .password(passwordEncoder.encode("worker"))
                .roles("WORKER")
                .build();
        UserDetails customer = User.builder()
                .username("customer")
                .password(passwordEncoder.encode("customer"))
                .roles("CUSTOMER")
                .build();


        return new InMemoryUserDetailsManager(bobko, worker, customer);
    }


}



