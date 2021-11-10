package com.ube.sis.configuration;

import com.ube.sis.configuration.jwt.AuthTokenFilter;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final AuthTokenFilter authTokenFilter;
  private final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

  public WebSecurityConfig(AuthTokenFilter authTokenFilter) {
    this.authTokenFilter = authTokenFilter;
  }


  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable();

    http = http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and();

    http = http
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
              logger.error("Unauthorized request - {}", ex.getMessage());
              logger.error("URI - {}", request.getRequestURI());
              response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
            }
        )
        .and();

    http.authorizeRequests()
        .antMatchers("/api/auth/**").permitAll()
        .anyRequest().authenticated();

    http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
