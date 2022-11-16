package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;

public interface AuthenticationService {

	public String login(String username, String password);
	
	public User getLoggedUser();
	
}