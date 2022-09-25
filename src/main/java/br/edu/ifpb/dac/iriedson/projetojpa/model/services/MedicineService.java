package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.MedicineRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepositoryDAO medicineRepository;
	@Autowired
	private ValidationService validationService;
	
	//Metodos crud
	public void creatMedicine(String name, String description) throws Exception {
		Medicine medicine = new Medicine(validationService.nameValidation(name), description);
		medicineRepository.save(medicine);
	}
	
	public Medicine readMedicineForID(Integer id) throws Exception {
		Optional<Medicine> medicine = medicineRepository.findById(id);
		if(medicine.isPresent()) {
			return medicine.get();
		}
		throw new Exception("Medicamento não encontrado!");
	}
	
	public List<Medicine> listMedicine() {
		return (List<Medicine>) medicineRepository.findAll();
	}
	
	public void updateMedicine(Integer id, String name, String description) throws Exception {
		Medicine medicine = readMedicineForID(id);
		medicine.setName(validationService.nameValidation(name));
		medicine.setDescription(description);
	}
	
	public void deleteMedicine(Integer id) throws Exception {
		Medicine medicine = readMedicineForID(id);
		medicineRepository.delete(medicine);
	}
	
}
