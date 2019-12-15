package com.example.demowebsecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // showing h2_console
        // Spring Boot /h2-console throws 403 with Spring Security 1.5.2
        // https://stackoverflow.com/questions/43794721
        http.authorizeRequests().antMatchers("/h2-console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();

        http.authorizeRequests().antMatchers("/", "/home").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login").permitAll();
        http.logout().permitAll();

    }

    // In Memory Authentication
    /**
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = encoder.encode("demo"); // For demo!!!
        System.out.println(password);

        // For Testing!!!
        auth.inMemoryAuthentication().withUser("admin").password(password).authorities("ROLE_ADMIN");
        auth.inMemoryAuthentication().withUser("user1").password(password).authorities("ROLE_USER");
        auth.inMemoryAuthentication().withUser("user2").password("{noop}demo").authorities("ROLE_USER");
    }
     */

    // DB Authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder)
        .withUser("hoge").password("{noop}password").authorities("role_hoge");
        // the above is just for demo purpose!!!
    }

}
