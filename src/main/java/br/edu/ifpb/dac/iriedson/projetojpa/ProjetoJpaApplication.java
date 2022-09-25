package br.edu.ifpb.dac.iriedson.projetojpa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.edu.ifpb.dac.iriedson.projetojpa.control.AplicationController;
import br.edu.ifpb.dac.iriedson.projetojpa.control.GoatController;
import br.edu.ifpb.dac.iriedson.projetojpa.control.MedicineController;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Goat;
import br.edu.ifpb.dac.iriedson.projetojpa.model.entity.Medicine;

@SpringBootApplication
public class ProjetoJpaApplication implements CommandLineRunner {

	@Autowired
	GoatController goatController;
	@Autowired
	MedicineController medicineController;
	@Autowired
	AplicationController aplicationController;
	BufferedReader leitor = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetoJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		menuInitial();
	}

	//menu principal
	private void menuInitial() throws IOException {
		Integer choice;
		do {
			choiceMenu();
			choice = Integer.parseInt(leitor.readLine());
			
			switch(choice) {
			case 1:
				registerNewGoat();
				break;
			case 2:
				searchGoat();
				break;
			case 3:
				showList(goatController.list());
				break;
			case 4:
				registerNewMedicine();
				break;
			case 5:
				searchMedicine();
				break;
			case 6:
				showList(medicineController.list());
				break;
			case 7:
				System.out.println("Adios parceiro!");
				break;
			default:
                System.out.println("Opção inválida.");
			}
		}while(choice != 7);
	}
	
	//lista escolhas do menu
	private static void choiceMenu() {
		System.out.println("1 - Cadastrar Cabra: "
						+ "\n2 - Pesquisar Cabra: "
						+ "\n3 - Listar Cabras: "
						+ "\n4 - Cadastrar Medicamento: "
						+ "\n5 - Pesquisar Medicamento: "
						+ "\n6 - Listar Medicamentos: "
						+ "\n7 - Sair:");
	}
	
	//lista escolhas do menu de pesquisa
	private static void choiceSearchMenu() {
		System.out.println("1 - Listar historico de aplicações: "
						+ "\n2 - Inofrmar nova aplicação: "
						+ "\n3 - Realizar nova pesquisa: "
						+ "\n4 - Voltar ao menu:");
	}
	
	//exibe listas
	private <T> void showList(List<T> list) {
		for (T elemento: list) {
			System.out.println(elemento.toString());
		}
	}
	
	//escolha 1 do menu - cria cabra
	private void registerNewGoat() throws IOException {
		System.out.println("Informe - Nickname: ");
		String name = leitor.readLine();
		System.out.println("Informe - BirthDay (dd/MM/YYYY): ");
		String date = leitor.readLine();
		System.out.println("Informe - Description: ");
		String descrip = leitor.readLine();
		System.out.println("Informe - Gender (M | F): ");
		String gender = leitor.readLine();
		try {
			goatController.creat(name, date, descrip, gender);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("1 - Cadastrar novamente: "
							+ "\n2 - Voltar ao menu: ");
			Integer choice = Integer.parseInt(leitor.readLine());
			if(choice == 1) {
				registerNewGoat();
			}
		}
	}
	
	//escolha 2 do menu - pesquisa cabra
	private void searchGoat() throws IOException{
		Goat goat;
		Integer choice = null;
		do {
			try {
				System.out.println("Informe o ID da cabra: ");
				goat = goatController.readForID(Integer.parseInt(leitor.readLine()));
				System.out.println("Você recuperou: \n"+goat.toString());
				choiceSearchMenu();
				choice = Integer.parseInt(leitor.readLine());
				
				switch(choice) {
				case 1:
					showList(aplicationController.list(goat));
					break;
				case 2:
					System.out.println("Informe o ID do medicamento: ");
					Medicine medicine = medicineController.readForID(Integer.parseInt(leitor.readLine()));
					registerNewAplication(goat, medicine);
					break;
				case 3:
					System.out.println("\n");
					continue;
				case 4:
					System.out.println("\n");
					break;
				default:
	                System.out.println("Opção inválida.");
				}		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				System.out.println("1 - Consultar novamente: "
						+ "\n2 - Voltar ao menu: ");
				choice = Integer.parseInt(leitor.readLine());
				if(choice != 1) {
					choice = 4;
				}
			}
		}while(choice != 4);
	}
	
	//escolha 4 do menu - cria medicamento
	private void registerNewMedicine() throws IOException {
		System.out.println("Informe - Name: ");
		String name = leitor.readLine();
		System.out.println("Informe - Description: ");
		String descrip = leitor.readLine();
		try {
			medicineController.creat(name, descrip);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			System.out.println("1 - Cadastrar novamente: "
							+ "\n2 - Voltar ao menu: ");
			Integer choice = Integer.parseInt(leitor.readLine());
			if(choice == 1) {
				registerNewMedicine();
			}
		}
	}
	
	//escolha 5 do menu - pesquisa medicamento
	private void searchMedicine() throws IOException {
		Medicine medicine;
		Integer choice = null;
		do {
			try {
				System.out.println("Informe o ID do medicamento: ");
				medicine = medicineController.readForID(Integer.parseInt(leitor.readLine()));
				System.out.println("Você recuperou: \n"+medicine.toString());
				choiceSearchMenu();
				choice = Integer.parseInt(leitor.readLine());
				
				switch(choice) {
				case 1:
					showList(aplicationController.list(medicine));
					break;
				case 2:
					System.out.println("Informe o ID do Bode: ");
					Goat goat = goatController.readForID(Integer.parseInt(leitor.readLine()));
					registerNewAplication(goat, medicine);
					break;
				case 3:
					System.out.println("\n");
					continue;
				case 4:
					System.out.println("\n");
					break;
				default:
	                System.out.println("Opção inválida.");
				}		
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}finally {
				System.out.println("1 - Consultar novamente: "
								+ "\n2 - Voltar ao menu: ");
				choice = Integer.parseInt(leitor.readLine());
				if(choice != 1) {
					choice = 4;
				}
			}
		}while(choice != 4);
		
	}
	
	//escolha 2 do menu de pesquisa - cria aplicação
	private void registerNewAplication(Goat goat, Medicine medicine) throws IOException {
		System.out.println("Informe - Modo de aplicação: ");
		String modo = leitor.readLine();
		System.out.println("Informe - Data (dd/MM/YYYY): ");
		String date = leitor.readLine();
		try {
			aplicationController.creat(modo, date, goat, medicine);
			System.out.println("Aplicação cadastrada com sucesso!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
