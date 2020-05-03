package olle.roman.game.romansgameserver.domain.game.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.Nothing;
import olle.roman.game.romansgameclient.model.objects.Portal;
import olle.roman.game.romansgameclient.model.objects.Wall;
import olle.roman.game.romansgameclient.model.objects.equipment.Axe;
import olle.roman.game.romansgameclient.model.objects.equipment.Wood;
import olle.roman.game.romansgameclient.model.objects.weapon.Sword;
import olle.roman.game.romansgameclient.model.report.GameResult;
import olle.roman.game.romansgameclient.model.state.NotificationCode;
import olle.roman.game.romansgameclient.model.state.Severity;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.game.DefaultGame;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.Result;
import olle.roman.game.romansgameserver.domain.model.map.Map;

@SpringBootTest
public class Map1GameTest2 {

	private static final String NAME = "GameTestMap1";
	private static final Map MAP = Maps.MAP_1_PRIMITIVE_2;

	@Test
	public void stepOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.step());
		assertTrue(game.lookAhead() instanceof Nothing);
		
		assertTrue(game.isFinished());
		assertEquals((initialState.getCurrentPosition() + 1), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
		
		GameResult result = game.getResult();
		assertNotNull(result);
		assertEquals(Result.SUCCESS.getResult(), result.getMessage());
		assertTrue(result.getHistory().size() == 2);
		assertTrue(newState.getNotifications().size() == 1);
		assertTrue(newState.getNotifications().iterator().next().getSeverity() == Severity.INFO);
		assertTrue(newState.getNotifications().iterator().next().getCode() == NotificationCode.GAME_SUCCESS);
	}
	
	@Test
	public void jumpOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.jump());
		assertTrue(game.lookAhead() instanceof Wall);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition() + 2), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void takeNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.take());
		assertTrue(game.lookAhead() instanceof Portal);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void healOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.heal());
		assertTrue(game.lookAhead() instanceof Portal);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.attack());
		assertTrue(game.lookAhead() instanceof Portal);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackWithNullWeaponOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.attack(null));
		assertTrue(game.lookAhead() instanceof Portal);
		
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals(initialState.getCurrentPosition(), newState.getCurrentPosition());
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	@Test
	public void attackWithUnexistWeaponOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.attack(Sword.getInstance()));
		assertTrue(game.lookAhead() instanceof Portal);
		
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
	public void makeNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.make(Wood.getInstance(), Sword.getInstance()));
		assertTrue(game.lookAhead() instanceof Portal);
		
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
	public void makeNullOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.make(null, null));
		assertTrue(game.lookAhead() instanceof Portal);
		
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
	public void useNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.use(Axe.getInstance()));
		assertTrue(game.lookAhead() instanceof Portal);
		
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
	public void useNullOnNothingMap1_2() {
		Game game = new DefaultGame(NAME, UUID.randomUUID(), MAP);
		State initialState = game.getCurrentState();
		assertTrue(game.lookAhead() instanceof Portal);
		
		State newState = game.action(Action.use(null));
		assertTrue(game.lookAhead() instanceof Portal);
		
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
