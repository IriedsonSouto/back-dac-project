package br.edu.ifpb.dac.iriedson.projetojpa.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @Temporal(TemporalType.DATE)
	@Column(name = "BIRTHDAY")
	private Date birthDay;

    @Column(name = "DESCRIPTION", length = 600)
	private String description;


    //Construtores
    public Goat() {}
	
        
    public Goat(String nickname, Date birthDay, String description) {
		this.nickname = nickname;
		this.birthDay = birthDay;
		this.description = description;
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
		return Objects.hash(id, nickname);
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
		return Objects.equals(id, other.id) && Objects.equals(nickname, other.nickname);
	}

	@Override
	public String toString() {
		return "* Nickname: " + nickname + ", ID: " + id + ", BirthDate: " + birthDay + ", Description: " + description
				+ ".";
	}
	
}
