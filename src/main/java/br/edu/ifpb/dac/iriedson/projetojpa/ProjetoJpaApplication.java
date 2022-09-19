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
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuInitial();
	}

	private void menuInitial() {
		Integer choice;
		do {
			choiceMenu();
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				registerNewGoat();
				break;
			case 2:
				searchGoat();
				break;
			case 3:
				registerNewMedicine();
				break;
			case 4:
				searchMedicine();
				break;
			case 5:
				System.out.println("Adios parceiro!");
				break;
			default:
                System.out.println("Opção inválida.");
			}
		}while(choice != 5);
	}
	
	private static void choiceMenu() {
		System.out.println("1 - Cadastrar Cabra: "
						+ "\n2 - Pesquisar Cabra: "
						+ "\n3 - Cadastrar Medicamento: "
						+ "\n4 - Pesquisar Medicamento: "
						+ "\n5 - Sair:\n");
	}
	
	private void registerNewGoat() {

	}
	
	private void searchGoat(){
		System.out.println("Informe o ID da cabra: ");
		Goat goat = goatController.readForID(sc.nextInt());
		System.out.println("Você recuperou: "
						+ "\n"+goat.toString() 
						+ "\n 1 - Listar historico de aplicações"
						+ "\n 2 - Inofrmar nova aplicação:"
						+ "\n 3 - Realizar nova pesquisa:"
						+ "\n 4 - Voltar ao menu:");
		registerNewAplication();
	}
	
	private void registerNewMedicine() {
		
	}
	
	private void searchMedicine() {
		
	}
	
	private void registerNewAplication() {
		
	}
	
}
