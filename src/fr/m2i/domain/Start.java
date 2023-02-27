package fr.m2i.domain;

import java.util.Scanner;

import fr.m2i.model.Todo;
import fr.m2i.model.TodoList;
import fr.m2i.model.Urgence;

public class Start {

	public static void main(String[] args) {
		// On crée un nouveau todo
		TodoList list = new TodoList();
		
		// On met la méthode menu dans une variable
		Integer choice = menu();
		while (choice != 7) {
			execution(choice, list);
			choice = menu();
		}
	}
	
	// Création de la méthode menu
	public static int menu() {
		Integer choice = 0;
		
		System.out.println("Veuillez choisir une opération :");
		System.out.println("1) Ajouter une todo");
		System.out.println("2) Voir la liste des todo");
		System.out.println("3) Afficher une todo par index");
		System.out.println("4) Afficher la dernière todo");
		System.out.println("5) Retirer une todo par index");
		System.out.println("6) Retirer la dernière todo");
		System.out.println("7) Quitter");
		Scanner scanner = new Scanner(System.in);
		choice = scanner.nextInt();
		//scanner.close();
		return choice;
	}
	
	// Création de la méthode execution qui prend en paramètre le choix fait ds la méthode menu et exécute la méthode correspondante 
	public static void execution(int choice, TodoList list) {
		switch (choice) {
			case 1 :
				list.addTodo(createTodo());
				break;
			case 2: 
				list.showTodoList();
				break;
			case 3:
				list.showTodoByIndex(askForIndex());
				break;
			case 4:
				list.showLastTodo();
				break;
			case 5:
				list.removeTodoByIndex(askForIndex());
				break;
			case 6:
				list.removeLastTodo();
				break;
			default:
				break;
		}
	}
	
	/**
	 * Création de la méthode createTodo. 
	 * @return qui retourne le nouveau Todo avec son niveau d'uegence, titre et description.
	 */
	public static Todo createTodo() {
		System.out.println("Quel est le niveau d'urgence ? 1 = Haute, 2= Normale, 3= Faible");
		Scanner scanner = new Scanner(System.in);
		Integer choiceUrgence;
		do {
			choiceUrgence = scanner.nextInt();
		} while (choiceUrgence <1 || choiceUrgence >3);
		
		
		System.out.println("Quel est le titre ?");
		String title = scanner.next();
		System.out.println("Quel est la description?");
		scanner.nextLine();
		String description = scanner.nextLine();
		
		Todo result = new Todo(Urgence.values()[choiceUrgence-1], title, description);
		return result;
	}
	
	/**
	 * Création de la méthode askForIndex qui demande quel est l'index du todo recherché
	 * @return retourne l'index recherché
	 */
	public static int askForIndex() {
		System.out.println("Quel index vous intéresse ?");
		Scanner scanner = new Scanner(System.in);
		return scanner.nextInt();
	}
}
