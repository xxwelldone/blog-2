 package br.com.blog.sec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig {
	@Bean
	public PasswordEncoder pwEncoder() {
		return new  BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authconfig) throws Exception{
		return authconfig.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().csrf().disable().cors();
		
		
//		http.authorizeHttpRequests((auth) -> auth.antMatchers("user/register").permitAll()
//				.antMatchers("user/login").permitAll()
//				.antMatchers(HttpMethod.OPTIONS).permitAll()
//				.anyRequest().authenticated()).httpBasic();
		return http.build();
	}

}
