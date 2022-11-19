package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;

public class TokenDTO {

	//Atributos
	private String token;
	private UserNewDTO user;
	
	//Construtores
	public TokenDTO() {
	}
	
	public TokenDTO(String token, UserNewDTO user) {
		super();
		this.token = token;
		this.user = user;
	}
	
	//Get's and Set's
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public UserNewDTO getUser() {
		return user;
	}
	public void setUser(UserNewDTO user) {
		this.user = user;
	}
	
}
