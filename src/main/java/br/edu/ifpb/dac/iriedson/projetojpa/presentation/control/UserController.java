package br.edu.ifpb.dac.iriedson.projetojpa.presentation.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.UserService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.UserNewDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private ConvertService convertService;
	@Autowired
	private UserService service;
	
	@PostMapping
	public ResponseEntity save(@RequestBody UserNewDTO dto) {
		try {
			User user = convertService.userNewDtoToUser(dto);
			user = service.save(user);
			dto = convertService.userToUserNewDto(user);
			
			return new ResponseEntity(dto, HttpStatus.CREATED);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity update(@PathVariable("id") Long id, @RequestBody UserNewDTO dto) {
		try {
			dto.setId(id);
			User user = convertService.userNewDtoToUser(dto);
			user = service.update(user);
			dto = convertService.userToUserNewDto(user);
			
			return ResponseEntity.ok(dto);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		try {
			service.delete(id);
			
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@GetMapping
	public ResponseEntity find(
				@RequestParam(value = "name", required = false) String name,
				@RequestParam(value = "email", required = false) String email,
				@RequestParam(value = "id", required = false) Long id
			) {
		try {
			User filter = new User();
			filter.setId(id);
			filter.setName(name);
			filter.setEmail(email);
			
			List<User> users = service.find(filter);
			List<UserNewDTO> dtos = convertService.listUserToDTO(users);
			
			return ResponseEntity.ok(dtos);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
