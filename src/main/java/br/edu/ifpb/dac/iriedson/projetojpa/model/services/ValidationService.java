package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;


@Service
public class ValidationService {

	
	//Metodos validadores
	public String nameValidation(String nickname) throws Exception {
		if(nickname.matches("[A-Za-z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ'\\s]")) {
			return nickname;
		}
		throw new Exception("Apelido invalido!");		
	}
	
	public Date dateValidation(String birthDay) throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = formato.parse("23/11/2015");
		if(date.getTime() > new Date().getTime()) {
			throw new Exception("Data invalida!");
		}
		return date;
	}
	
	public EnumGender genderValidation(String gender) throws Exception {
		if(gender.toUpperCase().equals("MASCULINO")) {
			return EnumGender.MALE;
		}else if (gender.toUpperCase().equals("FEMININO")) {
			return EnumGender.FEMALE;
		}
		throw new Exception("Genero invalido!");
	}
}
