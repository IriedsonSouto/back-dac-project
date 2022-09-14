package br.edu.ifpb.dac.iriedson.projetojpa.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.GoatRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;

@Controller
public class GoatController {

	@Autowired
	private GoatRepositoryDAO goatRepository;
	
	public void creat() {
		List<Goat> list = new ArrayList();
		String[] goatNames = {"Bonitinho", "Azulão", "Juliette", "Pipoca", "Bebel",};
		String[] goatDescription  = {"Bode amarelo", "Azul com chifre", "Cabra marrom", "Cabra mocha marrom", "Boe arisca",};
		
		for(int i = 3; i < 5; i++) {
			list.add(new Goat(goatNames[i], new Date(), goatDescription[i]));
		}
		goatRepository.saveAll(list);
	}
		
	public List<Goat> list() {
		return (List<Goat>) goatRepository.findAll();
	}
	
}
