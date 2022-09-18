package br.edu.ifpb.dac.iriedson.projetojpa.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;

@Entity
@Table(name = "TB_GOAT")
public class Goat implements Serializable{

    private static final long serialVersionUID = 3582191797181071323L;

	//Atributos
    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private Integer id;

    @Column(name = "NICKNAME", nullable = false)
	private String nickname;
    
    @OneToMany(mappedBy = "goat")
	private List<Aplication> aplication;
    
    @Column(name = "GENDER", nullable = false)
	private EnumGender gender;

    @Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthDay;

    @Column(name = "DESCRIPTION", length = 600)
	private String description;
    

    //Construtores
    public Goat() {}
	
        
    public Goat(String nickname, Date birthDay, String description, EnumGender gender) {
		this.nickname = nickname;
		this.gender = gender;
		this.birthDay = birthDay;
		this.description = description;
	}

    
	//Get's and Set's'
	public Integer getId() {
		return id;
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
	
	//Metodos utils
    @Override
	public int hashCode() {
		return Objects.hash(birthDay, gender, nickname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goat other = (Goat) obj;
		return Objects.equals(birthDay, other.birthDay) && gender == other.gender
				&& Objects.equals(nickname, other.nickname);
	}

	@Override
	public String toString() {
		return "* Nickname: " + nickname + "Gender: " + gender + ", ID: " + id 
				+ ", BirthDate: " + birthDay + ", Description: " + description + ".";
	}
	
}
