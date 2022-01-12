package ru.buildservice.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ru.buildservice.project.auth.CustomUserDetailService;



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
                .antMatchers("/", "index","/contacts", "/css/**", "/css/#", "/js/**", "/images/**","/icon/**").permitAll()
                .antMatchers("/customer/**", "/css/*", "/css/#", "/js/*", "/images/**").hasAnyRole("CUSTOMER", "ADMIN")

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .successHandler(successHandler);
//                .defaultSuccessUrl("/customer/objects",true);
    }


//    @Autowired
//    private UserDetailsService userDetailsService;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder);

      //  auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder);


    }






//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails bobko = User.builder()
//                .username("bobko")
//                .password(passwordEncoder.encode("bobko"))
//                .roles("ADMIN")
//                .build();
//        UserDetails worker = User.builder()
//                .username("worker")
//                .password(passwordEncoder.encode("worker"))
//                .roles("WORKER")
//                .build();
//        UserDetails customer = User.builder()
//                .username("customer")
//                .password(passwordEncoder.encode("customer"))
//                .roles("CUSTOMER")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(bobko, worker, customer);
//    }


}



