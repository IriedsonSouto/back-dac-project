package br.edu.ifpb.dac.iriedson.projetojpa.model.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;

@Repository
public interface MedicineRepositoryDAO extends PagingAndSortingRepository<Medicine, Integer> {

	List<Medicine> findByName(String name);

}
