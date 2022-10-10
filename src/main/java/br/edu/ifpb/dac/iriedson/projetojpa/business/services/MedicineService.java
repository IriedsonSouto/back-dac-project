package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.model.repository.MedicineRepository;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatSendDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineSendDTO;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private ConvertService convertService;
	
	//Metodos crud
	public Medicine creatMedicine(Medicine medicine) throws Exception {
		return medicineRepository.save(medicine);
	}
			
	public Medicine readMedicineByID(Integer id) throws Exception {
		Optional<Medicine> medicine = medicineRepository.findById(id);
		if(medicine.isPresent()) {
			return medicine.get();
		}
		throw new Exception("Medicamento não encontrado!");
	}
	
	public List<Medicine> getMedicineByParam(MedicineSendDTO medicineSend) throws Exception {
		Medicine medicine = convertService.medicineSendDtoToMedicine(medicineSend);
		Example example = Example.of(medicine, ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		List<Medicine> medicines = medicineRepository.findAll(example);
		if(medicineSend.getId() != null) {
			Medicine medicineId = readMedicineByID(medicineSend.getId());
			medicines.add(medicineId);
		}
		return medicines;
	}
	
	public List<Medicine> listMedicine() {
		return (List<Medicine>) medicineRepository.findAll();
	}
	
	public Medicine updateMedicine(Integer id, MedicineNewDTO medicineNewDto) throws Exception {
		Medicine medicine = readMedicineByID(id);
		medicine.setName(validationService.nameValidation(medicineNewDto.getName()));
		medicine.setDescription(medicineNewDto.getDescription());
		return medicineRepository.save(medicine);
	}
	
	public void deleteMedicine(Integer id) throws Exception {
		Medicine medicine = readMedicineByID(id);
		medicineRepository.delete(medicine);
	}
	
}
