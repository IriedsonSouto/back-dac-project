package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;


public class UserNewDTO {

	//Atributos
	private String email;
	private String name;
	private String password;
		
		
	//Construtores
	public UserNewDTO() {}


	public UserNewDTO(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
