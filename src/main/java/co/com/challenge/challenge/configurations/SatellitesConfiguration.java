package co.com.challenge.challenge.configurations;

import co.com.challenge.challenge.filters.SatellitesFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SatellitesConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf().disable()
        .addFilterAfter(new SatellitesFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST,"/satellite/location/topsecret").hasAuthority("USER")
        .antMatchers(HttpMethod.POST,"/satellite/location/topsecret_split").hasAuthority("USER")
        .antMatchers(HttpMethod.POST,"/satellite/location/login").permitAll()
        .antMatchers(HttpMethod.GET,"/satellite/location/status").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();
  }
}
