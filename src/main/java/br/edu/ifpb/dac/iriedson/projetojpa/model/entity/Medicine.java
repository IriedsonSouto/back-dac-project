package br.edu.ifpb.dac.iriedson.projetojpa.model.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TB_MEDICINE")
public class Medicine implements Serializable{
	
	private static final long serialVersionUID = -5759746745017176796L;

	//Atributos
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;
	
    @Column(name = "NAME", nullable = false)
	private String name;
    
    @OneToMany(mappedBy = "medicine")
	private List<Aplication> aplication;
       
    @Column(name = "DESCRIPTION", length = 600)
	private String description;

	
    //Construtores
    public Medicine() {}

	public Medicine(String name, String aplicationMode) {
		this.name = name;
	}

	public Medicine(String name, String aplicationMode, String description) {
		this.name = name;
		this.description = description;
	}

	
	//Get's and Set's'
	public Integer getId() {
		return id;
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

	
	//Metodos utils
	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medicine other = (Medicine) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "* Name:" + name + ", ID: " + id + ", Description: " + description + ".";
	}
}
