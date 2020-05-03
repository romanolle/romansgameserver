package olle.roman.game.romansgameserver.domain.game.map;

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
import olle.roman.game.romansgameclient.model.objects.Portal;
import olle.roman.game.romansgameclient.model.objects.equipment.Axe;
import olle.roman.game.romansgameclient.model.objects.equipment.Wood;
import olle.roman.game.romansgameclient.model.objects.weapon.Sword;
import olle.roman.game.romansgameclient.model.state.NotificationCode;
import olle.roman.game.romansgameclient.model.state.Severity;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.game.DefaultGame;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.map.Map;

@SpringBootTest
public class Map2to6GameTest3 {

	private static final String NAME = "GameTestMap";
	
	private static final List<Map> MAPS = Lists.newArrayList(
			Maps.MAP_2_EQUIPMENT_3,
			Maps.MAP_3_EQUIPMENT_3,
			Maps.MAP_4_EQUIPMENT_3,
			Maps.MAP_5_EQUIPMENT_3,
			Maps.MAP_6_EQUIPMENT_3
	);

	@Test
	public void stepOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
		
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.step());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals((initialState.getCurrentPosition() + 1), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertNull(newState.getStandsOn());
		}
	}
	
	private Game createGame(int index, Map map) {
		return new DefaultGame(NAME + (index + 2), UUID.randomUUID(), map);
	}

	@Test
	public void jumpOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.jump());
			assertTrue(game.lookAhead() instanceof Portal);
			
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
	public void takeNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.take());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().size() == 1);
			assertNull(newState.getStandsOn());
		}
	}
	
	@Test
	public void healOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.heal());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void attackOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.attack());
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void attackWithNullWeaponOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.attack(null));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().isEmpty());
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void attackWithUnexistWeaponOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.attack(Sword.getInstance()));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.WARNING);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void makeNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.make(Wood.getInstance(), Sword.getInstance()));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void makeNullOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.make(null, null));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.EQUIPMENT_NULL);
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void useNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.use(Axe.getInstance()));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT);
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}
	
	@Test
	public void useNullOnNothingMap1() {
		for(int index = 0 ; index < MAPS.size() ; index++) {
			Game game = createGame(index, MAPS.get(index));
			State initialState = game.getCurrentState();
			assertTrue(game.lookAhead() instanceof Nothing);
			assertTrue(initialState.getStandsOn() instanceof Equipment);
			
			State newState = game.action(Action.use(null));
			assertTrue(game.lookAhead() instanceof Nothing);
			
			assertFalse(game.isFinished());
			assertNull(game.getResult());
			assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
			assertEquals(initialState.getHealth(), newState.getHealth());
			assertTrue(newState.getNotifications().size()==1);
			assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.ERROR);
			assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.EQUIPMENT_NULL);
			assertTrue(newState.getEquipments().isEmpty());
			assertTrue(newState.getStandsOn() instanceof Equipment);
		}
	}	
	
}
