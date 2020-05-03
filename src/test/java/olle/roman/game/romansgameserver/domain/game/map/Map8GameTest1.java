package olle.roman.game.romansgameserver.domain.game.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.Nothing;
import olle.roman.game.romansgameclient.model.objects.equipment.Axe;
import olle.roman.game.romansgameclient.model.objects.equipment.Wood;
import olle.roman.game.romansgameclient.model.objects.obstacle.Tree;
import olle.roman.game.romansgameclient.model.objects.weapon.Sword;
import olle.roman.game.romansgameclient.model.state.NotificationCode;
import olle.roman.game.romansgameclient.model.state.Severity;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.game.DefaultGame;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.map.Map;

@SpringBootTest
public class Map8GameTest1 {

	private static final String NAME = "GameTestMap8";
	private static final Map MAP = Maps.MAP_8_TREE_1;

	@Test
	public void stepOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.step());
		assertTrue(game.lookAhead() instanceof Tree);
		
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition() + 1), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void jumpOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.step());
		assertTrue(game.lookAhead() instanceof Tree);
		
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition() + 1), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void takeNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.take());
		assertTrue(game.lookAhead() instanceof Nothing);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void healOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.heal());
		assertTrue(game.lookAhead() instanceof Nothing);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.attack());
		assertTrue(game.lookAhead() instanceof Nothing);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackWithNullWeaponOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
		State newState = game.action(Action.attack(null));
		assertTrue(game.lookAhead() instanceof Nothing);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackWithUnexistWeaponOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
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
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void makeNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
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
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void makeNullOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
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
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void useNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
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
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void useNullOnNothingMap8_1() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Nothing);
		
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
		assertNull(newState.getStandsOn());
	}	
	
}
