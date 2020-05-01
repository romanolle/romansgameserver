package olle.roman.game.romansgameserver.domain.model;

import java.io.Serializable;
import java.util.Collection;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;

public class HistoryState implements Serializable {

	private static final long serialVersionUID = 6055419246819709195L;
	
	private final int currentPosition;
	private final int health;
	private final int maxHealth;
	private final Equipment standsOn;
	private final Collection<Equipment> equipments;
	private final Action action;

	public HistoryState(int currentPosition, int health, int maxHealth, Equipment standsOn, Collection<Equipment> equipments, Action action) {
		this.currentPosition = currentPosition;
		this.health = health;
		this.maxHealth = maxHealth;
		this.standsOn = standsOn;
		this.equipments = equipments;
		this.action = action;
	}

	public int getCurrentPosition() {
		return currentPosition;
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public Equipment getStandsOn() {
		return standsOn;
	}

	public Collection<Equipment> getEquipments() {
		return equipments;
	}
	
	public Action getAction() {
		return action;
	}

}
