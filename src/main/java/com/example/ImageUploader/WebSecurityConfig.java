package com.example.ImageUploader;

import com.example.ImageUploader.model.AppUser;
import com.example.ImageUploader.repo.AppUserRepository;
import com.example.ImageUploader.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserService appUserService;
    private AppUserRepository appUserRepository;

    @Autowired
    public WebSecurityConfig(AppUserService appUserService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        this.appUserRepository = appUserRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/gallery").hasRole("USER")
                .antMatchers("/uploadImage").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        AppUser appUserJan = new AppUser("User", passwordEncoder().encode("User123"), "ROLE_USER");
        AppUser appUserAdmin = new AppUser("Admin", passwordEncoder().encode("Admin"), "ROLE_ADMIN");
        appUserRepository.save(appUserJan);
        appUserRepository.save(appUserAdmin);
    }
}
