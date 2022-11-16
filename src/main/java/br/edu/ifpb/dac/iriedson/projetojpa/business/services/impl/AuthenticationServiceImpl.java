package br.edu.ifpb.dac.iriedson.projetojpa.business.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.AuthenticationService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ConvertService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.TokenService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.UserService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
	private UserService userService;
	@Autowired
	private ConvertService convertService;
	@Autowired
	private TokenService tokenService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${app.logintype}")
	private String logintype;
	

	@Override
	public User getLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return (User) authentication.getPrincipal();
	}

	private String localLogin(String username, String password) {	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		User user = userService.findByUsername(username);
		
		return tokenService.generate(user);
	}

	@Override
	public String login(String username, String password) {
		return localLogin(username, password);
	}
	
}
