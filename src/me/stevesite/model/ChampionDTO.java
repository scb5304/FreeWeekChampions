package me.stevesite.model;

import java.io.Serializable;

/**
 * Simple data transfer object that holds Champion information, for use in the rest of the application.
 */
public class ChampionDTO implements Serializable {

	private static final long serialVersionUID = 5474606831215025031L;
	
	private String name;
	private String title;
	private String role;
	private String passive;
	private String spell1;
	private String spell2;
	private String spell3;
	private String spell4;
	private String lore;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassive() {
		return passive;
	}
	public void setPassive(String passive) {
		this.passive = passive;
	}
	public String getSpell1() {
		return spell1;
	}
	public void setSpell1(String spell1) {
		this.spell1 = spell1;
	}
	public String getSpell2() {
		return spell2;
	}
	public void setSpell2(String spell2) {
		this.spell2 = spell2;
	}
	public String getSpell3() {
		return spell3;
	}
	public void setSpell3(String spell3) {
		this.spell3 = spell3;
	}
	public String getSpell4() {
		return spell4;
	}
	public void setSpell4(String spell4) {
		this.spell4 = spell4;
	}
	public String getLore() {
		return lore;
	}
	public void setLore(String lore) {
		this.lore = lore;
	}
	
}
