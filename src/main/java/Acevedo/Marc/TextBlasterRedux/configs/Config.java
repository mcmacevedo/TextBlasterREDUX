package Acevedo.Marc.TextBlasterRedux.configs;

import Acevedo.Marc.TextBlasterRedux.models.Attendee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.Map;

@Configuration
public class Config extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/api/v1/sbs22/**").permitAll()
            .and()
            .csrf()
            .disable();
        http
            .authorizeRequests()
            .antMatchers("/gui")
            .authenticated()
            .and()
            .oauth2Login();
    }

    @Bean
    public Map<String, Attendee> attendeesByPhoneNumber() {
        // TODO: Logic for parsing excel file
        return Map.of(
                "+17324061005",
                new Attendee("Marc", "Acevedo", "mcmacevedo@gmail.com", "+17324061005", 1));
    }
}
