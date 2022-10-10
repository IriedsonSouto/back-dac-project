package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;

import java.util.Date;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;

public class GoatSendDTO {

	//Atributis
	private Integer id;
	private String nickname;
   	private EnumGender gender;
	private Date birthDay;
	private String description;
		
		
	//Construtores
	public GoatSendDTO() {}


	public GoatSendDTO(Goat goat) {
		this.setId(goat.getId());
		this.nickname = goat.getNickname();
		this.gender = goat.getGender();
		this.birthDay = goat.getBirthDay();
		this.description = goat.getDescription();
		
	}
	
	//Get's and Set's'
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public EnumGender getGender() {
		return gender;
	}
	public void setGender(EnumGender gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
