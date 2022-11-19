package br.edu.ifpb.dac.iriedson.projetojpa.config;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.PasswordEnconderService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.SystemRoleService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.TokenService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	@Lazy
	private UserService userService;
	
	@Autowired
	private PasswordEnconderService passwordEnconderService;
	
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	@Bean
	public TokenFilter jwtTokenFilter() {
		return new TokenFilter(tokenService, userService);
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService)
			.passwordEncoder(passwordEnconderService);
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
		List<String> all = Arrays.asList("*");
		
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedMethods(all);
		corsConfiguration.setAllowedOriginPatterns(all);
		corsConfiguration.setAllowedHeaders(all);
		corsConfiguration.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		
		CorsFilter corFilter = new CorsFilter(source);
		
		FilterRegistrationBean<CorsFilter> filter = 
				new FilterRegistrationBean<CorsFilter>(corFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return filter;
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
				.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/login").permitAll()
				.antMatchers(HttpMethod.POST, "/api/isTokenValid").permitAll()
				.antMatchers(HttpMethod.POST, "/api/user").permitAll()
				.antMatchers(HttpMethod.DELETE, "/api/user").permitAll()
				
				.antMatchers(HttpMethod.GET, "/api/goat").permitAll()
				.antMatchers(HttpMethod.GET, "/api/goat/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/goat").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.toString())
				.antMatchers(HttpMethod.PUT, "/api/goat/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.toString())
				.antMatchers(HttpMethod.DELETE, "/api/goat/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.toString())
				
				.antMatchers(HttpMethod.GET, "/api/medicine").permitAll()
				.antMatchers(HttpMethod.GET, "/api/medicine/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/medicine").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				.antMatchers(HttpMethod.PUT, "/api/medicine/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				.antMatchers(HttpMethod.DELETE, "/api/medicine/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				
				.antMatchers(HttpMethod.GET, "/api/aplication").permitAll()
				.antMatchers(HttpMethod.GET, "/api/aplication/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/aplication").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				.antMatchers(HttpMethod.PUT, "/api/aplication/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				.antMatchers(HttpMethod.DELETE, "/api/aplication/**").hasRole(SystemRoleService.AVAILABLE_ROLES.ADMIN.name())
				
				.anyRequest().authenticated()	
		.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
			.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		http
		.logout(
				logout ->
					logout
					.clearAuthentication(true)
					.invalidateHttpSession(true)
					.logoutUrl("/api/logout")
					.logoutSuccessHandler(new LogoutSuccessHandler() {
						@Override
						public void onLogoutSuccess(HttpServletRequest request, 
								HttpServletResponse response, Authentication authentication)
								throws IOException, ServletException {
						}
					})
			);
	}

}
