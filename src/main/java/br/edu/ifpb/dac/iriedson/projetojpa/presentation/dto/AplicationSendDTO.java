package br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto;

import java.util.Date;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumAplicationMode;

public class AplicationSendDTO {

	//Atributos
	private Integer id;
	private EnumAplicationMode aplicationMode; 
	private String medicine;
    private String goat;
	private Date date;
	private Integer medicineId;
	private Integer goatId;
	
	//Construtores	
	public AplicationSendDTO() {}

	public AplicationSendDTO(Aplication aplication) {
		this.id = aplication.getId();
		this.aplicationMode = aplication.getAplicationMode();
		this.date = aplication.getDate();
		this.goat = "Nickname - "+aplication.getGoat().getNickname();
		this.medicine = "Name - "+aplication.getMedicine().getName();
		this.goatId = aplication.getGoat().getId();
		this.medicineId = aplication.getMedicine().getId();
	}

	//Get's and Set's'
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public EnumAplicationMode getAplicationMode() {
		return aplicationMode;
	}
	public void setAplicationMode(EnumAplicationMode aplicationMode) {
		this.aplicationMode = aplicationMode;
	}
	public String getMedicine() {
		return medicine;
	}
	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}
	public String getGoat() {
		return goat;
	}
	public void setGoat(String goat) {
		this.goat = goat;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	
}
