package br.edu.ifpb.dac.iriedson.projetojpa.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;

@Repository
public interface GoatRepository extends JpaRepository<Goat, Integer> {

}
