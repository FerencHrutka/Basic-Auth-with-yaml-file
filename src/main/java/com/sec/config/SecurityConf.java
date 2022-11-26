package com.sec.config;

import com.sec.entity.AppUser;
import com.sec.entity.AppUserProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.List;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    AppUserProperties appUserProperties;

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {

        List<AppUser> appUserList = appUserProperties.getAppUserList();

        for (AppUser appUser : appUserList) {
            auth
                    .inMemoryAuthentication()
                    .withUser(appUser.getUserName())
                    .password(appUser.getPassword())
                    .roles(appUser.getRole());
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")
                .permitAll();
    }

}
