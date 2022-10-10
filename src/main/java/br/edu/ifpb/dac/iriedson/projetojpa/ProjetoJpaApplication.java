package br.edu.ifpb.dac.iriedson.projetojpa;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ProjetoJpaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
