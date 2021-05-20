package com.jeremias.pinheiro.movie.api.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    //authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/search/*")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/create").hasRole("ADMIN_ROLE")
                .antMatchers(HttpMethod.PUT,"/update/*").hasRole("ADMIN_ROLE")
                .antMatchers(HttpMethod.DELETE,"/delete/*").hasRole("ADMIN_ROLE")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();

    }



    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("adminxfce34")
                .password(passwordEncoder.encode("password756"))
                .roles("ADMIN_ROLE")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }
}
