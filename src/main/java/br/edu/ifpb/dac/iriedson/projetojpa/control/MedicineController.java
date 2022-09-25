package br.edu.ifpb.dac.iriedson.projetojpa.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.model.services.MedicineService;

@Controller
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	
	
	public void creat(String name, String description) throws Exception {
		medicineService.creatMedicine(name, description);
	}
	
	public Medicine readForID(Integer id) throws Exception {
		return medicineService.readMedicineForID(id);
	}
	
	public List<Medicine> list() {
		return medicineService.listMedicine();
	}
	
	public void update(Integer id, String name, String description) throws Exception {
		medicineService.updateMedicine(id, name, description);
	}
	
	public void delete(Integer id) throws Exception {
		medicineService.deleteMedicine(id);
	}
}
