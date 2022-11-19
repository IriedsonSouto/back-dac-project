package br.edu.ifpb.dac.iriedson.projetojpa.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.SystemRole;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
	
	Optional<SystemRole> findByName(String name);
	
}