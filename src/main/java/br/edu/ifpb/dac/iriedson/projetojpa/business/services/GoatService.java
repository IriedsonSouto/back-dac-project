package br.edu.ifpb.dac.iriedson.projetojpa.business.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.repository.GoatRepository;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatNewDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.GoatSendDTO;


@Service
public class GoatService {

	@Autowired
	private GoatRepository goatRepository;
	@Autowired
	private ValidationService validationService;
	@Autowired
	private ConvertService convertService;
	
	
	//Metodos crud
	public Goat creatGoat(Goat goat) throws Exception {
		return goatRepository.save(goat);
	}
	
	public Goat readGoatByID(Integer id) throws Exception {
		Optional<Goat> goat = goatRepository.findById(id);
		if(goat.isPresent()) {
			return goat.get();
		}
		throw new Exception("Invalido, bode não encontrado!");
	}
	
	public List<Goat> getGoatByParam(GoatSendDTO goatSend) throws Exception {
		Goat goat = convertService.goatSendDtoToGoat(goatSend);
		Example example = Example.of(goat, ExampleMatcher.matching().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING));
		List<Goat> goats = goatRepository.findAll(example);
		if(goatSend.getId() != null) {
			Goat goatId = readGoatByID(goatSend.getId());
			goats.add(goatId);
		}
		return goats;
	}
	
	public List<Goat> listGoat() {
		return (List<Goat>) goatRepository.findAll();
	}
	
	public Goat updateGoat(Integer id, GoatNewDTO goatNewDto) throws Exception {
		Goat goat = readGoatByID(id);
		goat.setNickname(validationService.nameValidation(goatNewDto.getNickname()));
		goat.setBirthDay(validationService.dateValidation(goatNewDto.getBirthDay()));
		goat.setDescription(goatNewDto.getDescription());
		goat.setGender(validationService.genderValidation(goatNewDto.getGender()));
		return goatRepository.save(goat);
	}
	
	public void deleteGoat(Integer id) throws Exception {
		Goat goat = readGoatByID(id);
		goatRepository.delete(goat);
	}
	
}
