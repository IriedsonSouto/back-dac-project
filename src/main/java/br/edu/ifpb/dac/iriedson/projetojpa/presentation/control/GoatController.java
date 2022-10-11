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

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ConvertService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.GoatService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ValidationService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatSendDTO;

@RequestMapping("/api/goat")
@RestController
public class GoatController {

	@Autowired
	private GoatService goatService;
	@Autowired
	private ConvertService convertService;
	@Autowired
	private ValidationService validationService;
	
	
	@PostMapping
	public ResponseEntity creat(@RequestBody GoatNewDTO goatNewDto) {
		try {
			Goat goat = goatService.creatGoat(convertService.goatNewDtoToGoat(goatNewDto));
			return new ResponseEntity(convertService.goatToGoatSendDTO(goat), HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find(@RequestParam(value = "id", required = false) Integer id
			, @RequestParam(value = "name", required = false) String name
			, @RequestParam(value = "date", required = false) String date
			, @RequestParam(value = "gender", required = false) String gender ) {
		try{
			GoatSendDTO goatSend = new GoatSendDTO();
			goatSend.setId(id);
			goatSend.setNickname(name);
			if(date != null)
				goatSend.setBirthDay(validationService.dateValidation(date));
			if(gender != null)
				goatSend.setGender(validationService.genderValidation(gender));
			
			List<Goat> goats = goatService.getGoatByParam(goatSend);
			List<GoatSendDTO> goatsSend = convertService.goatToGoatSendDTO(goats);
			
			return ResponseEntity.ok(goatsSend);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody GoatNewDTO goatNewDto) {
		try {
			Goat goat = goatService.updateGoat(id, goatNewDto);
			return ResponseEntity.ok(convertService.goatToGoatSendDTO(goat));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			goatService.deleteGoat(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
			
}
