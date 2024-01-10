package ba.sum.fsre.ednevnik.config;

import ba.sum.fsre.ednevnik.Services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration

public class SecurityConfig {
    @Autowired
    DataSource dataSource;
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder((this.passwordEncoder()));
        daoAuthenticationProvider.setUserDetailsService((this.userDetailsService()));
        return daoAuthenticationProvider;
    }


    public SecurityFilterChain filterChain(HttpSecurity httpSecurity)  throws Exception
    {
        httpSecurity
                .authorizeHttpRequests()
                .requestMatchers("/auth/register/**","/","/register")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/login")
                .permitAll()
                .usernameParameter("email")
                .defaultSuccessUrl("/templates/users", true)
                .permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
/*
        httpSecurity.authenticationProvider(authenticationProvider());
        httpSecurity.headers().frameOptions().sameOrigin();

         */

        return httpSecurity.build();
    }
}

