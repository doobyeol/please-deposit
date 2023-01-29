package tk.returntrue.deposit.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import tk.returntrue.deposit.domain.security.AuthEntryPoint;
import tk.returntrue.deposit.domain.security.AuthTokenFilter;
import tk.returntrue.deposit.domain.security.auth.UserDetailsServiceImpl;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

    private final AuthEntryPoint unauthorizedHandler;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public AuthTokenFilter authTokenFilter() {
        return new AuthTokenFilter(userDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return  http.cors().and().csrf().disable()
                .addFilterBefore(authTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .anyRequest().authenticated()
                .and().build();
    }
}