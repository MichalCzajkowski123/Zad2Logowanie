package zajecia.example.logowanie;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.jws.soap.SOAPBinding;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password1")
                .roles("USER")
                .build();
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();
        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("moderator")
                .password("moderator1")
                .roles("MODERATOR")
                .build();
        return new InMemoryUserDetailsManager(userDetails, admin, moderator);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api").permitAll()
                .antMatchers(HttpMethod.POST, "/api").hasRole("MODERATOR")
                .antMatchers(HttpMethod.DELETE, "/api").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
        .and()
        .logout().permitAll()
        .and()
        .csrf().disable();
    }
}
