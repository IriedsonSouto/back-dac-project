package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumAplicationMode;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;


@Service
public class ValidationService {

	
	//Metodos validadores
	public String nameValidation(String name) throws Exception {
		final String regex = "[a-zA-Z_][a-zA-Z0-9À-ú_]*";

        Pattern r = Pattern.compile(regex);
        Matcher m = r.matcher(name);

        if(m.find()) {
        	return name;
		}     
        throw new Exception("Nome invalido!");		
	}
	
	public Date dateValidation(String birthDay) throws Exception {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");  
		Date date = formato.parse(birthDay);
		if(date.getTime() > new Date().getTime()) {
			throw new Exception("Data invalida!");
		}
		return date;
	}
	
	public EnumGender genderValidation(String gender) throws Exception {
		if(gender.toUpperCase().equals("MASCULINO")||gender.toUpperCase().equals("M")) {
			return EnumGender.MASCULINO;
		}else if (gender.toUpperCase().equals("FEMININO")||gender.toUpperCase().equals("F")) {
			return EnumGender.FEMININO;
		}
		throw new Exception("Genero invalido!");
	}
	
	public EnumAplicationMode aplicationModeValidation(String aplicationMode) throws Exception {
		if(aplicationMode.toUpperCase().equals("ORAL")) {
			return EnumAplicationMode.ORAL;
		}else if (aplicationMode.toUpperCase().equals("SUBCUTANEO")) {
			return EnumAplicationMode.SUBCUTANEO;
		}else if (aplicationMode.toUpperCase().equals("INTRAMUSCULAR")) {
			return EnumAplicationMode.INTRAMUSCULAR;
		}
		throw new Exception("Genero invalido!");
	}
}
