package fr.m2i.model;

import java.util.ArrayList;

public class TodoList {
	ArrayList<Todo> list;
	
	public TodoList() {
		list = new ArrayList<Todo>();
	}
	
	public void addTodo(Todo todo) {
		list.add(todo);
	}
	
	public boolean removeTodoByIndex(int index) {
		if (index < list.size()) {
			list.remove(index);
			return true;
		} else {
			return false;
		}
	}
	
	public void removeLastTodo() {
		list.remove(list.size()-1);
	}
	
	public void showTodoList() {
		if (list.size()==0) {
			System.out.println("Rien à faire");
		} else {
			for (int i=0; i<list.size();i++) {
				System.out.println(i + ": " + list.get(i));
			}
		}
	}
	
	public void showTodoByIndex(int index) {
		if (index < list.size()) {
			System.out.println(list.get(index));
		} else {
			System.out.println("Veuillez saisir une index correct");
		}
	}
	
	public void showLastTodo() {
		System.out.println(list.get(list.size()-1));
	}
}
