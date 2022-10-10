package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;

public class MedicineSendDTO {

	//Atributis
	private Integer id;
	private String name;
	private String description;
	
	//Construtores	
	public MedicineSendDTO() {}

	public MedicineSendDTO(Medicine medicine) {
		this.setId(medicine.getId());
		this.name = medicine.getName();
		this.description = medicine.getDescription();
	}

	//Get's and Set's'
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
			
}
