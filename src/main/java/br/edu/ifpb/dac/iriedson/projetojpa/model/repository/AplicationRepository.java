package br.edu.ifpb.dac.iriedson.projetojpa.model.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Aplication;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;
import br.edu.ifpb.dac.iriedson.projetojpa.model.enums.EnumAplicationMode;

@Repository
public interface AplicationRepository extends JpaRepository<Aplication, Integer> {
	
	List<Aplication> findByAplicationMode(EnumAplicationMode aplicationMode);
	List<Aplication> findByDate(Date date);
	List<Aplication> findByGoat(Goat goat);
	List<Aplication> findByMedicine(Medicine medicine);
	
}
