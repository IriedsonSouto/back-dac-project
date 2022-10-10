package br.edu.ifpb.dac.iriedson.projetojpa.business.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationSendDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatSendDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineSendDTO;

@Service
public class ConvertService {

	@Autowired
	@Lazy
	private GoatService goatService;
	@Autowired
	@Lazy
	private MedicineService medicineService;
	@Autowired
	private ValidationService validationService;
	
	
	//Goat convert
	public Goat goatNewDtoToGoat(GoatNewDTO goatNewDto) throws Exception {
		return new Goat(validationService.nameValidation(goatNewDto.getNickname())
				, validationService.dateValidation(goatNewDto.getBirthDay())
				, goatNewDto.getDescription()
				, validationService.genderValidation(goatNewDto.getGender()));
	}
	
	public Goat goatSendDtoToGoat(GoatSendDTO goatSendDto) throws Exception {
		return new Goat(goatSendDto.getNickname(), goatSendDto.getBirthDay(), goatSendDto.getDescription()
				, goatSendDto.getGender());
	}
	
	public GoatSendDTO goatToGoatSendDTO(Goat goat) {
		return new GoatSendDTO(goat);
	}
	
	public List<GoatSendDTO> goatToGoatSendDTO(List<Goat> goats) {
		List<GoatSendDTO> goatsSend = new ArrayList<>(); 
		for(Goat goat: goats) {
			goatsSend.add(goatToGoatSendDTO(goat));
		}
		return goatsSend;
	}
	
	
	//Medicine convert
	public Medicine medicineNewDtoToMedicine(MedicineNewDTO medicineNewDto) throws Exception {
		return new Medicine(validationService.nameValidation(medicineNewDto.getName()), medicineNewDto.getDescription());
	}
	
	public MedicineSendDTO medicineToMedicineSendDTO(Medicine medicine) {
		return new MedicineSendDTO(medicine);
	}
	
	public Medicine medicineSendDtoToMedicine(MedicineSendDTO medicineSendDto) throws Exception {
		return new Medicine(validationService.nameValidation(medicineSendDto.getName()), medicineSendDto.getDescription());
	}
	
	public List<MedicineSendDTO> medicineToMedicineSendDTO(List<Medicine> medicines) {
		List<MedicineSendDTO> medicinesSend = new ArrayList<>(); 
		for(Medicine medicine: medicines) {
			medicinesSend.add(medicineToMedicineSendDTO(medicine));
		}
		return medicinesSend;
	}
	
	
	//Aplication convert
	public Aplication aplicationNewDtoToAplication(AplicationNewDTO aplicationNewDto) throws Exception {
		return new Aplication(validationService.aplicationModeValidation(aplicationNewDto.getAplicationMode())
				, validationService.dateValidation(aplicationNewDto.getDate())
				, goatService.readGoatByID(aplicationNewDto.getGoatId())
				, medicineService.readMedicineByID(aplicationNewDto.getMedicineId()));
	}
	
	public Aplication aplicationSendDtoToAplication(AplicationSendDTO aplicationSendDto) throws Exception {
		return new Aplication(aplicationSendDto.getAplicationMode()
				, aplicationSendDto.getDate()
				, goatService.readGoatByID(aplicationSendDto.getGoatId())
				, medicineService.readMedicineByID(aplicationSendDto.getMedicineId()));
	}
	
	public AplicationSendDTO aplicationToAplicationSendDTO(Aplication aplication) {
		return new AplicationSendDTO(aplication);
	}
	
	public List<AplicationSendDTO> aplicationToAplicationSendDTO(List<Aplication> aplications) {
		List<AplicationSendDTO> aplicationsSend = new ArrayList<>(); 
		for(Aplication aplication: aplications) {
			aplicationsSend.add(aplicationToAplicationSendDTO(aplication));
		}
		return aplicationsSend;
	}
	
}
