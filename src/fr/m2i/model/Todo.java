package fr.m2i.model;


/**
 * 
 * @author Thomas Seiler
 * Déclaration des urgences.
 */

public class Todo {
	
	private Urgence urgence;
	private String titre;
	private String description;
	
	public Urgence getUrgence() {
		return urgence;
	}
	public void setUrgence(Urgence urgence) {
		this.urgence = urgence;
	}
	
	
	public String getTitre() {
		return titre;
	}
	
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Todo(Urgence urg, String tit, String des) {
		setUrgence(urg);
		setTitre(tit);
		setDescription(des);
	}
	
	/**
	 * Retourne le todo sous la forme : Type urgence - nom du todo - description todo
	 */
	@Override
	public String toString() {
		return String.format("%s - %s - %s", urgence, titre, description);
	}
}
