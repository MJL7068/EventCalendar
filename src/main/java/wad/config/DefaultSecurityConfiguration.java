package wad.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("default")
@Configuration
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class DefaultSecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // sallitaan h2-konsolin käyttö
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                .anyRequest().authenticated();
        http.formLogin()
                .permitAll();
                
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("jack").password("bauer").roles("USER");
//        
//        auth.inMemoryAuthentication()
//                .withUser("moe").password("moe").roles("ADMIN");
//    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
