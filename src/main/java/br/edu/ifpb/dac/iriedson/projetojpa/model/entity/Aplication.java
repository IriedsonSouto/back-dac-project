package br.edu.ifpb.dac.iriedson.projetojpa.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumAplicationMode;


@Entity
@Table(name = "TB_APLICATION")
public class Aplication implements Serializable{

	private static final long serialVersionUID = 2929425073357098178L;

	//Atributos
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;
    
    @Column(name = "APLICATION_MODE", nullable = false)
	private EnumAplicationMode aplicationMode;
    
    @ManyToOne
    @JoinColumn(name = "MEDICINE_ID")
	private Medicine medicine;
    
    @ManyToOne
    @JoinColumn(name = "GOAT_ID")
    private Goat goat;

    @Temporal(TemporalType.DATE)
	@Column(name = "DATE", nullable = false)
	private Date date;

    //Construtores
	public Aplication() {}

	public Aplication(EnumAplicationMode aplicationMode, Medicine medicine, Goat goat, Date date) {
		this.aplicationMode = aplicationMode;
		this.medicine = medicine;
		this.goat = goat;
		this.date = new Date();
	}

	//Get's and Set's'
	public Integer getId() {
		return id;
	}

	public EnumAplicationMode getAplicationMode() {
		return aplicationMode;
	}
	
	public void setAplicationMode(EnumAplicationMode aplicationMode) {
		this.aplicationMode = aplicationMode;
	}
	
	public Medicine getMedicine() {
		return medicine;
	}
	
	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}
	
	public Goat getGoat() {
		return goat;
	}
	
	public void setGoat(Goat goat) {
		this.goat = goat;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	//Metodos utils
	@Override
	public int hashCode() {
		return Objects.hash(aplicationMode, date, goat, medicine);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aplication other = (Aplication) obj;
		return aplicationMode == other.aplicationMode && Objects.equals(date, other.date)
				&& Objects.equals(goat, other.goat) && Objects.equals(medicine, other.medicine);
	}

	@Override
	public String toString() {
		return "* Medicine: " + medicine.getName() + ",  Aplication Mode: " + aplicationMode 
				+ ", Goat: " + goat.getNickname() + ", Date:" + date + ".";
	}
    
    
}
