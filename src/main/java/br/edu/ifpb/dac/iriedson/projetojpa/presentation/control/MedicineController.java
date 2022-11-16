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
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.MedicineService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ValidationService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.MedicineSendDTO;

@RequestMapping("/api/medicine")
@RestController
public class MedicineController {

	@Autowired
	private MedicineService medicineService;
	@Autowired
	private ConvertService convertService;
	@Autowired
	private ValidationService validationService;
	
	
	@PostMapping
	public ResponseEntity creat(@RequestBody MedicineNewDTO medicineNewDto) {
		try {
			Medicine medicine = medicineService
					.creatMedicine(convertService.medicineNewDtoToMedicine(medicineNewDto));
			return new ResponseEntity(convertService.medicineToMedicineSendDTO(medicine)
					, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@GetMapping
	public ResponseEntity find(@RequestParam(value = "id", required = false) Integer id
			, @RequestParam(value = "name", required = false) String name) {
		try{
			MedicineSendDTO medicineSend = new MedicineSendDTO();
			medicineSend.setId(id);
			medicineSend.setName(name);

			
			List<Medicine> medicines = medicineService.getMedicineByParam(medicineSend);
			List<MedicineSendDTO> medicinesSend = convertService.medicineToMedicineSendDTO(medicines);
			
			return ResponseEntity.ok(medicinesSend);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping("{id}")
	public ResponseEntity findById(@PathVariable("id") Integer id) {
		try{
			Medicine medicine = medicineService.readMedicineByID(id);
			return ResponseEntity.ok(convertService.medicineToMedicineSendDTO(medicine));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody MedicineNewDTO medicineNewDto) {
		try {
			Medicine medicine = medicineService.updateMedicine(id, medicineNewDto);
			return ResponseEntity.ok(convertService.medicineToMedicineSendDTO(medicine));
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Integer id) {
		try {
			medicineService.deleteMedicine(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
