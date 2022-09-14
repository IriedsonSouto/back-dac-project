package br.edu.ifpb.dac.iriedson.projetojpa.model.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;

@Repository
public interface GoatRepositoryDAO extends PagingAndSortingRepository<Goat, String> {

	List<Goat> findByNickname(String nickname);
    List<Goat> findByBirthDay(Date birthDay);
    List<Goat> findById(Integer id);

}
