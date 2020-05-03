package olle.roman.game.romansgameserver.domain.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.objects.equipment.Axe;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Matches;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Wood;

@SpringBootTest
public class EquipmentsTest {

	@Test
	public void empty() {
		Equipments equipments = new Equipments();
		assertTrue(equipments.getAllEquipments().size() == 0);
	}

	@Test
	public void addToEmptyList() {
		Equipments equipments = new Equipments();
		assertTrue(equipments.getAllEquipments().size() == 0);
		
		equipments.add(new Axe(null, -1));
		assertTrue(equipments.getAllEquipments().size() == 1);
		assertTrue(equipments.getAllEquipments().iterator().next() instanceof Axe);
		
		assertTrue(equipments.contains(new Axe(null, -10)));
		assertFalse(equipments.contains(new Matches(null, -10)));
	}
	
	@Test
	public void addSameSingletonEquipment() {
		Equipments equipments = new Equipments(Lists.newArrayList(new Axe(null, -1)));
		assertTrue(equipments.getAllEquipments().size() == 1);
		assertTrue(equipments.getAllEquipments().iterator().next() instanceof Axe);

		assertTrue(equipments.contains(new Axe(null, -10)));
		assertFalse(equipments.contains(new Matches(null, -10)));
		
		equipments.add(new Axe(null, -1));
		assertTrue(equipments.getAllEquipments().size() == 1);
		
		assertTrue(equipments.contains(new Axe(null, -10)));
		assertFalse(equipments.contains(new Matches(null, -10)));
	}
	
	@Test
	public void removeSingletonEquipment() {
		Equipments equipments = new Equipments(Lists.newArrayList(new Axe(null, -1)));
		assertTrue(equipments.getAllEquipments().size() == 1);
		assertTrue(equipments.getAllEquipments().iterator().next() instanceof Axe);

		assertFalse(equipments.delete(new Axe(null, -10)));
		assertFalse(equipments.delete(new Matches(null, -10)));
		
		assertTrue(equipments.getAllEquipments().size() == 1);
	}
	
	@Test
	public void addSameNonSingletonValue() {
		Equipments equipments = new Equipments(Lists.newArrayList(new Wood(null, -1)));
		assertTrue(equipments.getAllEquipments().size() == 1);
		assertTrue(equipments.getAllEquipments().iterator().next() instanceof Wood);

		assertTrue(equipments.contains(new Wood(null, -10)));
		assertFalse(equipments.contains(new Matches(null, -10)));
		
		equipments.add(new Wood(null, -1));
		assertTrue(equipments.getAllEquipments().size() == 2);
		
		assertTrue(equipments.contains(new Wood(null, -10)));
		assertFalse(equipments.contains(new Matches(null, -10)));
	}
	
	@Test
	public void removeNonSingletonEquipment() {
		Equipments equipments = new Equipments(Lists.newArrayList(new Wood(null, -1)));
		assertTrue(equipments.getAllEquipments().size() == 1);
		assertTrue(equipments.getAllEquipments().iterator().next() instanceof Wood);
		
		equipments.add(new Wood(null, -1));
		assertTrue(equipments.getAllEquipments().size() == 2);
		
		assertTrue(equipments.delete(new Wood(null, -10)));
		assertFalse(equipments.delete(new Matches(null, -10)));

		assertTrue(equipments.getAllEquipments().size() == 1);
		
		assertTrue(equipments.delete(new Wood(null, -10)));

		assertTrue(equipments.getAllEquipments().size() == 0);
	}
}
