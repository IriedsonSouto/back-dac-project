package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;


public class MedicineNewDTO {

	//Atributis
	private String name;
	private String description;
	
	//Construtores	
	public MedicineNewDTO() {}

	public MedicineNewDTO(String name, String description) {
		this.name = name;
		this.description = description;
	}

	//Get's and Set's'
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
