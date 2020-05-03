package olle.roman.game.romansgameserver.domain.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.Nothing;
import olle.roman.game.romansgameclient.model.objects.Portal;
import olle.roman.game.romansgameclient.model.objects.Wall;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.game.map.Maps;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;

@SpringBootTest
public class GameTest {

	@Test
	public void healTestOnMap1_1() {
		Game game = new DefaultGame("GameTestMap1Heal", UUID.randomUUID(), Maps.MAP_1_PRIMITIVE_1);
		State initialState = game.getCurrentState();
		assertEquals(initialState.getMaxHealth(), initialState.getHealth());
		assertTrue(game.lookAhead() instanceof Nothing);
		
		//-2hp
		assertTrue(game instanceof ObjectActions);
		ObjectActions actions = (ObjectActions) game;
		actions.changeHealth(-2);
		State newInitialState = game.getCurrentState();
		assertEquals(initialState.getMaxHealth(), newInitialState.getMaxHealth());
		assertEquals(newInitialState.getMaxHealth() - 2, newInitialState.getHealth());
		
		//heal (+1hp)
		State newState = game.action(Action.heal());
		assertEquals(newInitialState.getHealth() + 1, newState.getHealth());
		assertTrue(game.lookAhead() instanceof Nothing);
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition()), newState.getCurrentPosition());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());

		//heal (+1hp)
		State newState2 = game.action(Action.heal());
		assertEquals(newState.getHealth() + 1, newState2.getHealth());
		assertEquals(newState.getMaxHealth(), newState2.getHealth());
		assertTrue(game.lookAhead() instanceof Nothing);
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition()), newState2.getCurrentPosition());
		assertTrue(newState2.getNotifications().isEmpty());
		assertTrue(newState2.getEquipments().isEmpty());
		assertNull(newState2.getStandsOn());

		//heal (+1hp but in previous state was in max)
		State newState3 = game.action(Action.heal());
		assertEquals(newState2.getHealth(), newState3.getHealth());
		assertEquals(newState3.getMaxHealth(), newState3.getHealth());
		assertTrue(game.lookAhead() instanceof Nothing);
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition()), newState3.getCurrentPosition());
		assertTrue(newState3.getNotifications().isEmpty());
		assertTrue(newState3.getEquipments().isEmpty());
		assertNull(newState3.getStandsOn());
	}
	

	@Test
	public void changeDirectionTestOnMap1_2() {
		Game game = new DefaultGame("changeDirectionTestOnMap1_2", UUID.randomUUID(), Maps.MAP_1_PRIMITIVE_2);
		State initialState = game.getCurrentState();
		assertEquals(initialState.getMaxHealth(), initialState.getHealth());
		assertTrue(game.lookAhead() instanceof Portal);
		
		game.changeDirection();
		State newState = game.getCurrentState();
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(game.lookAhead() instanceof Nothing);
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition()), newState.getCurrentPosition());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	

	@Test
	public void changeDirectionTestOnMap1_3() {
		Game game = new DefaultGame("changeDirectionTestOnMap1_3", UUID.randomUUID(), Maps.MAP_1_PRIMITIVE_3);
		State initialState = game.getCurrentState();
		assertEquals(initialState.getMaxHealth(), initialState.getHealth());
		assertTrue(game.lookAhead() instanceof Wall);
		
		game.changeDirection();
		State newState = game.getCurrentState();
		assertEquals(initialState.getHealth(), newState.getHealth());
		assertTrue(game.lookAhead() instanceof Portal);
		assertFalse(game.isFinished());
		assertNull(game.getResult());
		assertEquals((initialState.getCurrentPosition()), newState.getCurrentPosition());
		assertTrue(newState.getNotifications().isEmpty());
		assertTrue(newState.getEquipments().isEmpty());
		assertNull(newState.getStandsOn());
	}
	
	
	
	
	
}
