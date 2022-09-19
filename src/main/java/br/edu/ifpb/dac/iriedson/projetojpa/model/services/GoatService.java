package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.GoatRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;

@Service
public class GoatService {

	@Autowired
	private GoatRepositoryDAO goatRepository;
	
	public void creatGoat(String nickname, String birthDay, String description, String gender) throws Exception {
		Goat goat = new Goat(nameValidation(nickname), dateValidation(birthDay)
							, description, genderValidation(gender));
		goatRepository.save(goat);
	}
	
	public Goat readGoatForID(Integer id) {
		Optional<Goat> goat = goatRepository.findById(id);
		goat.orElseThrow(IllegalArgumentException::new);
		return goat.get();
	}
	
	public List<Goat> listGoat() {
		return (List<Goat>) goatRepository.findAll();
	}
	
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
