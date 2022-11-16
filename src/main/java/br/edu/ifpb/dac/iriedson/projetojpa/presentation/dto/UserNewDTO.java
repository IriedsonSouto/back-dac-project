package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;


public class UserNewDTO {

	//Atributos
	private Long id;
	private String email;
	private String name;
	private String username;
	private String password;
	private String[] roles;
		
		
	//Construtores
	public UserNewDTO() {}

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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
}
