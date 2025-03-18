package com.rk.dailydish.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rk.dailydish.security.CustomeUserDetailService;
import com.rk.dailydish.security.JwtAuthenticationEntryPoint;
import com.rk.dailydish.security.JwtAuthenticationFilter;

import jakarta.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	
	@Autowired
	private CustomeUserDetailService userDetailService;
//	.requestMatchers("/test").authenticated().requestMatchers("/auth/login").permitAll()
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		  http.csrf(csrf -> csrf.disable())
          .authorizeHttpRequests(auth->auth
        		  .requestMatchers("/api/auth/register","/api/auth/login").permitAll()
                  .anyRequest().authenticated() )
          			.userDetailsService(userDetailService)
                  .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
  http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
  return http.build();
	}
	

		@Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
		@Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
			   return builder.getAuthenticationManager();
	    }

	
}
