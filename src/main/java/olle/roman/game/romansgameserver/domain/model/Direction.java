package olle.roman.game.romansgameserver.domain.model;

public enum Direction {

	FORWARD(1),
	BACKWARD(-1);
	
	private final int direction;

	private Direction(int direction) {
		this.direction = direction;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public Direction changeDirection() {
		if(name().equals(FORWARD.name())) {
			return BACKWARD;
		}
		return FORWARD;
	}
}
