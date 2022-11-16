package br.edu.ifpb.dac.iriedson.projetojpa.business.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.edu.ifpb.dac.iriedson.projetojpa.business.services.PasswordEnconderService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.SystemRoleService;
import br.edu.ifpb.dac.iriedson.projetojpa.business.services.UserService;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.SystemRole;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.User;
import br.edu.ifpb.dac.iriedson.projetojpa.model.repository.UserRepository;



public class UserServiceImpl implements UserService{


	@Autowired
	private UserRepository repository;
	@Autowired
	private SystemRoleService systemRoleService;
	@Autowired
	private PasswordEnconderService passwordEnconderService;
	
	@Override
	public User save(User user) {
		if(user.getId() != null) {
			throw new IllegalStateException("User is already in the database");
		}
		
		passwordEnconderService.encryptPassword(user);
		
		List<SystemRole> roles = new ArrayList<>();
		roles.add(systemRoleService.findDefault());
		user.setRoles(roles);
		
		return repository.save(user);
	}

	@Override
	public User update(User user) {
		if(user.getId() == null) {
			throw new IllegalStateException("Error id null");
		}
		
		passwordEnconderService.encryptPassword(user);
		
		return repository.save(user);
	}

	@Override
	public void delete(Long id) {
		if(id == null) {
			throw new IllegalStateException("Error id null");
		}
		
		User user = findById(id);
		
		if(user == null) {
			throw new IllegalStateException(String.format("Entity not find"));
		}
		
		repository.delete(user);
	}

	@Override
	public User findById(Long id) {
		if(id == null) {
			throw new IllegalStateException("Id cannot be null");
		}
		
		Optional<User> optional = repository.findById(id);
		
		return optional.isPresent() ? optional.get() : null;
	}
	

	@Override
	public User findByEmail(String email) {
		if(email == null) {
			throw new IllegalStateException("Email cannot be null");
		}
		
		Optional<User> optional = repository.findByEmail(email); 
		
		return optional.isPresent() ? optional.get() : null;
	}
	
	@Override
	public User findByUsername(String username) {
		if(username == null) {
			throw new IllegalStateException("Username cannot be null");
		}
		
		Optional<User> optional = repository.findByUsername(username); 
		
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Iterable<User> findAll() {
		return repository.findAll();
	}
	
	@Override
	public List<User> find(User filter) {
		Example example = Example.of(filter,
				ExampleMatcher.matching()
					.withIgnoreCase()
					.withStringMatcher(StringMatcher.CONTAINING));
		
		return repository.findAll(example);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException(String.format("Could not find any user with username %s", username));
		}
		
		return user;
	}
}

