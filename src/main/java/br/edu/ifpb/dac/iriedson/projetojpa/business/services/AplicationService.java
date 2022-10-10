package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.model.repository.AplicationRepository;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationSendDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatSendDTO;

@Service
public class AplicationService {

	@Autowired
	private AplicationRepository aplicationRepository;
	@Autowired
	private ValidationService validationService;
	@Autowired
	@Lazy
	private ConvertService convertService;
	@Autowired
	@Lazy
	private GoatService goatService;
	@Autowired
	@Lazy
	private MedicineService medicineService;
	
	
	//Metodos crud
	public Aplication creatAplication(Aplication aplication) throws Exception {
		return aplicationRepository.save(aplication);
	}
	
	public Aplication readAplicationByID(Integer id) throws Exception {
		Optional<Aplication> aplication = aplicationRepository.findById(id);
		if(aplication.isPresent()) {
			return aplication.get();
		}
		throw new Exception("Aplicação não encontrado invalido!");
	}
	
	public List<Aplication> getAplicationByParam(AplicationSendDTO aplicationSend) throws Exception {
		Aplication aplication = convertService.aplicationSendDtoToAplication(aplicationSend);
		Example example = Example.of(aplication, ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		List<Aplication> aplications = aplicationRepository.findAll(example);
		if(aplicationSend.getId() != null) {
			Aplication aplicationId = readAplicationByID(aplicationSend.getId());
			aplications.add(aplicationId);
		}
		return aplications;
	}
	
	public List<Aplication> listAplicationByGoat(Goat goat) {
		return (List<Aplication>) aplicationRepository.findByGoat(goat);
	}
	
	public List<Aplication> listAplicationByMedicine(Medicine medicine) {
		return (List<Aplication>) aplicationRepository.findByMedicine(medicine);
	}
			
	public List<Aplication> listAplication() {
		return (List<Aplication>) aplicationRepository.findAll();
	}
	
	
	public Aplication updateAplication(Integer id, AplicationNewDTO aplicationNewDto) throws Exception {
		Aplication aplication = readAplicationByID(id);
		aplication.setAplicationMode(validationService.aplicationModeValidation(aplicationNewDto.getAplicationMode()));
		aplication.setDate(validationService.dateValidation(aplicationNewDto.getDate()));
		aplication.setGoat(goatService.readGoatByID(aplicationNewDto.getGoatId()));
		aplication.setMedicine(medicineService.readMedicineByID(aplicationNewDto.getMedicineId()));
		return aplicationRepository.save(aplication);
	}
	
	public void deleteAplication(Integer id) throws Exception {
		Aplication aplication = readAplicationByID(id);
		aplicationRepository.delete(aplication);
	}
}
