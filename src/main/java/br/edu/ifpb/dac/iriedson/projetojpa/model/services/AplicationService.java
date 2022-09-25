package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.AplicationRepositoryDAO;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;

@Service
public class AplicationService {

	@Autowired
	private AplicationRepositoryDAO aplicationRepository;
	@Autowired
	private ValidationService validationService;
	
	//Metodos crud
		public void creatAplication(String aplicationMode, String date, Goat goat, Medicine medicine) throws Exception {
			Aplication aplication = new Aplication(validationService.aplicationModeValidation(aplicationMode), validationService.dateValidation(date)
								, goat, medicine);
			aplicationRepository.save(aplication);
		}
		
		public Aplication readAplicationByID(Integer id) throws Exception {
			Optional<Aplication> aplication = aplicationRepository.findById(id);
			if(aplication.isPresent()) {
				return aplication.get();
			}
			throw new Exception("Aplicação não encontrado invalido!");
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
		
		
		public void updateAplication(Integer id, String aplicationMode, String date, Goat goat, Medicine medicine) throws Exception {
			Aplication aplication = readAplicationByID(id);
			aplication.setAplicationMode(validationService.aplicationModeValidation(aplicationMode));
			aplication.setDate(validationService.dateValidation(date));
			aplication.setGoat(goat);
			aplication.setMedicine(medicine);
			aplicationRepository.save(aplication);
		}
		
		public void deleteAplication(Integer id) throws Exception {
			Aplication aplication = readAplicationByID(id);
			aplicationRepository.delete(aplication);
		}
}
