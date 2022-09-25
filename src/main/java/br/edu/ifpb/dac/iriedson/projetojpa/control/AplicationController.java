package br.edu.ifpb.dac.iriedson.projetojpa.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.model.services.AplicationService;

@Controller
public class AplicationController {

	@Autowired
	private AplicationService aplicationService;
	
	public void creat(String aplicationMode, String date, Goat goat, Medicine medicine) throws Exception {
		aplicationService.creatAplication(aplicationMode, date, goat, medicine);
	}
	
	public Aplication readForID(Integer id) throws Exception {
		return aplicationService.readAplicationByID(id);
	}
	
	public List<Aplication> list() {
		return aplicationService.listAplication();
	}
	
	public List<Aplication> list(Goat goat) {
		return aplicationService.listAplicationByGoat(goat);
	}
	
	public List<Aplication> list(Medicine medicine) {
		return aplicationService.listAplicationByMedicine(medicine);
	}
	
	public void update(Integer id, String aplicationMode, String date, Goat goat, Medicine medicine) throws Exception {
		aplicationService.updateAplication(id, aplicationMode, date, goat, medicine);
	}
	
	public void delete(Integer id) throws Exception {
		aplicationService.deleteAplication(id);
	}
}
