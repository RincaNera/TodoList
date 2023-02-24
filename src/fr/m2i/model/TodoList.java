package fr.m2i.model;

import java.util.ArrayList;

/**
 * Classe de gestion d'une liste ToDo.
 * @author Thomas Seiler
 *
 */

public class TodoList {
	ArrayList<Todo> list;
	
	// Constructeur
	public TodoList() {
		list = new ArrayList<Todo>();
	}
	
	/**
	 * Méthode ajoutant un todo à la liste
	 * @param todo à ajouter
	 */
	public void addTodo(Todo todo) {
		list.add(todo);
	}
	
	/**
	 * Méthode supprimant un todo via son index
	 * @param index todo à supprimer.
	 * @return true si supprimé, false si non supprimé.
	 */
	public boolean removeTodoByIndex(int index) {
		if (index < list.size()) {
			list.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Méthode supprimant le dernier ToDo ajouté.
	 */
	public void removeLastTodo() {
		list.remove(list.size()-1);
	}
	
	/**
	 * Méthode affichant la liste des Todo (si il n'y a pas de todo, affiche "rien à faire".
	 */
	
	public void showTodoList() {
		if (list.size()==0) {
			System.out.println("Rien à faire");
		} else {
			for (int i=0; i<list.size();i++) {
				System.out.println(i + ": " + list.get(i));
			}
		}
	}
	
	/**
	 * Méthode qui affiche le todo demandé via son index
	 * @param index index du todo voulu.
	 */
	
	public void showTodoByIndex(int index) {
		if (index < list.size()) {
			System.out.println(list.get(index));
		} else {
			System.out.println("Veuillez saisir une index correct");
		}
	}
	
	/**
	 * Affiche le dernier todo créé.
	 */
	public void showLastTodo() {
		System.out.println(list.get(list.size()-1));
	}
}
