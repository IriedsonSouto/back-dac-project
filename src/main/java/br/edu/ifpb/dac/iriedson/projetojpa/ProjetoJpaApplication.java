package br.edu.ifpb.dac.iriedson.projetojpa;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.iriedson.projetojpa.control.GoatController;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;

@SpringBootApplication
public class ProjetoJpaApplication implements CommandLineRunner {

	@Autowired
	GoatController goatController;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
	
}
