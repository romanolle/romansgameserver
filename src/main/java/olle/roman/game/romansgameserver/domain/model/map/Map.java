package olle.roman.game.romansgameserver.domain.model.map;

import java.util.List;

import olle.roman.game.romansgameserver.domain.model.Direction;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinition;

public class Map {

	private static final int DEFAULT_MAX_STEPS_LIMIT = 1000;
	private static final int DEFAULT_HEALTH = 10;
	private final List<ObjectDefinition> map;
	private final int defaultPosition;
	private final Direction direction;
	private final int maxHealth;
	private final int maxStepLimit;

	
	
	public Map(List<ObjectDefinition> map) {
		this(map, 0);
	}

	public Map(List<ObjectDefinition> map, int defaultPosition) {
		this(map, defaultPosition, Direction.FORWARD);
	}

	public Map(List<ObjectDefinition> map, int defaultPosition, Direction direction) {
		this(map, defaultPosition, direction, DEFAULT_HEALTH);
	}

	public Map(List<ObjectDefinition> map, int defaultPosition, Direction direction, int maxHealth) {
		this(map, defaultPosition, direction, maxHealth, DEFAULT_MAX_STEPS_LIMIT);
	}

	public Map(List<ObjectDefinition> map, int defaultPosition, Direction direction, int maxHealth, int maxStepLimit) {
		this.map = map;
		this.defaultPosition = defaultPosition;
		this.direction = direction;
		this.maxHealth = maxHealth;
		this.maxStepLimit = maxStepLimit;
	}

	public List<ObjectDefinition> getMap() {
		return map;
	}

	public int getDefaultPosition() {
		return defaultPosition;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getMaxStepLimit() {
		return maxStepLimit;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
}
