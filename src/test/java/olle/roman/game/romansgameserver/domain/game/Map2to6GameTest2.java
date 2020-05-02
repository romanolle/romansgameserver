package olle.roman.game.romansgameserver.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.Equipment;
import olle.roman.game.romansgameclient.model.objects.Nothing;
import olle.roman.game.romansgameclient.model.objects.equipment.Axe;
import olle.roman.game.romansgameclient.model.objects.equipment.Wood;
import olle.roman.game.romansgameclient.model.objects.weapon.Sword;
import olle.roman.game.romansgameclient.model.state.NotificationCode;
import olle.roman.game.romansgameclient.model.state.Severity;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.map.Map;

@SpringBootTest
public class Map2to6GameTest2 {

	private static final String NAME = "GameTestMap";
	
	private static final List<Map> MAPS = Lists.newArrayList(
			Maps.MAP_2_EQUIPMENT_2,
			Maps.MAP_3_EQUIPMENT_2,
			Maps.MAP_4_EQUIPMENT_2,
			Maps.MAP_5_EQUIPMENT_2,
			Maps.MAP_6_EQUIPMENT_2
	);

	@Test
	public void stepOnEquipmentMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
		
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.step());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals((initialState.getCurrentPosition() + 1), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	private Game createGame(int index, Map map) {
		return new DefaultGame(NAME + (index + 2), UUID.randomUUID(), map);
	}

	@Test
	public void jumpOnNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.jump());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals((initialState.getCurrentPosition() + 2), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void takeNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.take());
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void healOnNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.heal());
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void attackEquipmentMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.attack());
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void attackWithNullWeaponEquipmentMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.attack(null));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void attackWithUnexistWeaponEquipmentMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.attack(Sword.getInstance()));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.WARNING);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void makeNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.make(Wood.getInstance(), Sword.getInstance()));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void makeNullOnNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.make(null, null));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.EQUIPMENT_NULL);
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void useNothingMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.use(Axe.getInstance()));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void useNullOnEquipmentMap2to6_2() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Equipment);
			
			State newState = game.action(Action.use(null));
			assertTrue(game.lookAhead() instanceof Equipment);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.EQUIPMENT_NULL);
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}	
	
}
