package br.edu.ifpb.dac.iriedson.projetojpa.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.AplicationService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ConvertService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ValidationService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.AplicationSendDTO;

@RequestMapping("/api/aplication")
@RestController
public class AplicationController {

	@Autowired
	private AplicationService aplicationService;
	@Autowired
	private ConvertService convertService;
	@Autowired
	private ValidationService validationService;
	
	
	@PostMapping
	public ResponseEntity creat(@RequestBody AplicationNewDTO aplicationNewDto) {
		try {
			Aplication aplication = aplicationService
					.creatAplication(convertService.aplicationNewDtoToAplication(aplicationNewDto));
			return new ResponseEntity(convertService.aplicationToAplicationSendDTO(aplication), HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find(@RequestParam(value = "id", required = false) Integer id
			, @RequestParam(value = "aplicationMode", required = false) String aplicationMode
			, @RequestParam(value = "date", required = false) String date
			, @RequestParam(value = "goatId", required = false) Integer goatId 
			, @RequestParam(value = "medicineId", required = false) Integer medicineId ){
		try{
			AplicationSendDTO aplicationSend = new AplicationSendDTO();
			aplicationSend.setId(id);
			if(aplicationMode != null)
				aplicationSend.setAplicationMode(validationService.aplicationModeValidation(aplicationMode));
			if(date != null)
				aplicationSend.setDate(validationService.dateValidation(date));
			aplicationSend.setGoatId(goatId);
			aplicationSend.setMedicineId(medicineId);
			
			List<Aplication> aplications = aplicationService.getAplicationByParam(aplicationSend);
			List<AplicationSendDTO> aplicationsSend = convertService.aplicationToAplicationSendDTO(aplications);
			
			return ResponseEntity.ok(aplicationsSend);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}	
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody AplicationNewDTO aplicationNewDto) {
		try {
			Aplication aplication = aplicationService.updateAplication(id, aplicationNewDto);
			return ResponseEntity.ok(convertService.aplicationToAplicationSendDTO(aplication));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			aplicationService.deleteAplication(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}
