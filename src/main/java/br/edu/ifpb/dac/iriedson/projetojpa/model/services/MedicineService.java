package br.edu.ifpb.dac.iriedson.projetojpa.model.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.dao.MedicineRepositoryDAO;

@Service
public class MedicineService {

	@Autowired
	private MedicineRepositoryDAO medicineRepository;
	@Autowired
	private ValidationService validationService;
	
	
	
}
