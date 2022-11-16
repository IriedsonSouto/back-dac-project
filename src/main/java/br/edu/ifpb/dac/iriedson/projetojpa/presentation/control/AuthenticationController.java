package br.edu.ifpb.dac.iriedson.projetojpa.presentation.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.AuthenticationService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.ConvertService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.TokenService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.UserService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.LoginDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.TokenDTO;
import br.edu.ifpb.dac.iriedson.projetojpa.presentation.dto.UserNewDTO;

@RestController
@RequestMapping("/api")
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class AuthenticationController {

	@Autowired
	private AuthenticationService service;
	@Autowired
	private ConvertService convertService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginDTO dto) {
		try {
			String token = service.login(dto.getUsername(), dto.getPassword());
			User user = userService.findByUsername(dto.getUsername());
			UserNewDTO userDTO = convertService.userToUserNewDto(user);
			
			TokenDTO tokenDTO = new TokenDTO(token, userDTO);
			
			return new ResponseEntity(tokenDTO, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PostMapping("/isTokenValid")
	public ResponseEntity isTokenValid(@RequestBody TokenDTO dto) {
		try {
			boolean isTokenValid = tokenService.isValid(dto.getToken());
			
			return new ResponseEntity(isTokenValid, HttpStatus.OK);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
}
