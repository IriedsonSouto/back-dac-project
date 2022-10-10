package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;


public class GoatNewDTO {

	//Atributos
	private String nickname;
   	private String gender;
	private String birthDay;
	private String description;
	
	
	//Construtores
	public GoatNewDTO() {}


	public GoatNewDTO(String nickname, String gender, String birthDay, String description) {
		this.nickname = nickname;
		this.gender = gender;
		this.birthDay = birthDay;
		this.description = description;
	}
	
	//Get's and Set's'
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
