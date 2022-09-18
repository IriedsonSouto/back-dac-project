package br.edu.ifpb.dac.iriedson.projetojpa.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.GoatRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;

@Controller
public class GoatController {

	@Autowired
	private GoatRepositoryDAO goatRepository;
	
	public void creat(String nickname, Date birthDay, String description, EnumGender gender) {
		goatRepository.save(new Goat(nickname, birthDay, description, gender));
	}
	
	public Goat readForID(Integer id) {
		return goatRepository.findById(id).get();
	}
	
	public List<Goat> list() {
		return (List<Goat>) goatRepository.findAll();
	}
	
	public void update(Integer id, String nickname, Date birthDay, String description, EnumGender gender) {
		Goat goat = readForID(id);
		goat.setNickname(nickname);
		goat.setBirthDay(birthDay);
		goat.setDescription(description);
		goat.setGender(gender);
		goatRepository.save(goat);
	}
	
	public void delete(Integer id) {
		goatRepository.deleteById(id);;
	}
		
	
	
}
