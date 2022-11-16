package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;

public class UserSendDTO {

	//Atributos
	private Integer id;
	private String email;
	private String name;
		
		
	//Construtores
	public UserSendDTO() {}


	public UserSendDTO(User user) {
		this.setId(user.getId());
		this.email = user.getEmail();
		this.name = user.getName();
	}

	//Get's and Set's'
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
