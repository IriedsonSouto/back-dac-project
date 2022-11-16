package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.SystemRole;

public interface SystemRoleService {
	
	public enum AVAILABLE_ROLES { ADMIN, USER }

	public void createDefaultValues();
	
	public SystemRole findByName(String name);
	
	public SystemRole findDefault();
	
}