package olle.roman.game.romansgameserver.domain.model;

import java.util.List;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.objects.Equipment;

public class Equipments {

	private final List<Equipment> equipments;
	
	public Equipments() {
		this(Lists.newArrayList());
	}

	public Equipments(List<Equipment> equipments) {
		this.equipments = Lists.newArrayList(equipments);
	}
	
	
	public boolean delete(Equipment equipmentToDelete) {
		Integer index = findIndex(equipmentToDelete);
		if(index == null) {
			return false;
		}
		if(equipments.get(index).singletonInList()) {
			return false;
		}
		equipments.remove(index.intValue());
		return true;
	}
	
	public boolean contains(Equipment equipment) {
		return findIndex(equipment) == null ? false : true;
	}
	
	private Integer findIndex(Equipment equipment) {
		for(int index = 0 ; index < equipments.size() ; index++) {
			if(equipments.get(index).getClass().isInstance(equipment)) {
				return index;
			}
		}
		return null;
	}
	
	public boolean add(Equipment equipment) {
		if(equipment == null) {
			return false;
		}
		if(!equipment.singletonInList()) {
			equipments.add(equipment);
			return true;
		}
		if(contains(equipment)) {
			return false;
		}
		equipments.add(equipment);
		return true;
	}
	
	public List<Equipment> getAllEquipments() {
		return Lists.newArrayList(equipments);
	}
	
}
