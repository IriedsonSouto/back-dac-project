package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.GoatRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;


@Service
public class GoatService {

	@Autowired
	private GoatRepositoryDAO goatRepository;
	@Autowired
	private ValidationService validationService;
	
	//Metodos crud
	public void creatGoat(String nickname, String birthDay, String description, String gender) throws Exception {
		Goat goat = new Goat(validationService.nameValidation(nickname), validationService.dateValidation(birthDay)
							, description, validationService.genderValidation(gender));
		goatRepository.save(goat);
	}
	
	public Goat readGoatByID(Integer id) throws Exception {
		Optional<Goat> goat = goatRepository.findById(id);
		if(goat.isPresent()) {
			return goat.get();
		}
		throw new Exception("Bode não encontrado invalido!");
	}
	
	public List<Goat> listGoat() {
		return (List<Goat>) goatRepository.findAll();
	}
	
	public void updateGoat(Integer id, String nickname, String birthDay, String description, String gender) throws Exception {
		Goat goat = readGoatByID(id);
		goat.setNickname(validationService.nameValidation(nickname));
		goat.setBirthDay(validationService.dateValidation(birthDay));
		goat.setDescription(description);
		goat.setGender(validationService.genderValidation(gender));
		goatRepository.save(goat);
	}
	
	public void deleteGoat(Integer id) throws Exception {
		Goat goat = readGoatByID(id);
		goatRepository.delete(goat);
//		goatRepository.deleteById(id);
	}
	
}
