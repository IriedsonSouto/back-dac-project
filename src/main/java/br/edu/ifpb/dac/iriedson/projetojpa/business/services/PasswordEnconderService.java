package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import org.springframework.security.crypto.password.PasswordEncoder;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;

public interface PasswordEnconderService extends PasswordEncoder{

	void encryptPassword(User user);
	
}