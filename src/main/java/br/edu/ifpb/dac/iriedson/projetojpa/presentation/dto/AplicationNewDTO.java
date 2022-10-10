package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;


public class AplicationNewDTO {

	//Atributos
	private String aplicationMode; 
	private Integer medicineId;
    private Integer goatId;
	private String date;
	
	//Construtores	
	public AplicationNewDTO() {}

	public AplicationNewDTO(String aplicationMode, String date, Integer goatId, Integer medicineId) {
		this.aplicationMode = aplicationMode;
		this.date = date;
		this.goatId = goatId;
		this.medicineId = medicineId;
	}
	
	//Get's and Set's'
	public String getAplicationMode() {
		return aplicationMode;
	}
	
	public void setAplicationMode(String aplicationMode) {
		this.aplicationMode = aplicationMode;
	}
	
	public Integer getMedicineId() {
		return medicineId;
	}
	
	public void setMedicineId(Integer medicineId) {
		this.medicineId = medicineId;
	}
	
	public Integer getGoatId() {
		return goatId;
	}
	
	public void setGoatId(Integer goatId) {
		this.goatId = goatId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

}
