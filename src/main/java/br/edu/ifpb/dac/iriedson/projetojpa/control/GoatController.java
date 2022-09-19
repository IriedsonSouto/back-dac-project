package br.edu.ifpb.dac.iriedson.projetojpa.control;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumGender;
import br.edu.ifpb.dac.iriedson.projetojpa.model.services.GoatService;

@Controller
public class GoatController {

	@Autowired
	private GoatService goatService;
	
	
	public void creat(String nickname, String birthDay, String description, String gender) throws Exception {
		goatService.creatGoat(nickname, birthDay, description, gender);
	}
	
	public Goat readForID(Integer id) {
		return goatService.readGoatForID(id);
	}
	
	public List<Goat> list() {
		return goatService.listGoat();
	}
	
	public void update(Integer id, String nickname, Date birthDay, String description, EnumGender gender) {
		Goat goat = readForID(id);
		goat.setNickname(nickname);
		goat.setBirthDay(birthDay);
		goat.setDescription(description);
		goat.setGender(gender);
	}
	
	public void delete(Integer id) {
//		goatRepository.deleteById(id);;
	}
			
}
