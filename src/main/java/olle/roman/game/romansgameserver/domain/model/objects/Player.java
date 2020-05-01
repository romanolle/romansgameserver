package olle.roman.game.romansgameserver.domain.model.objects;

public class Player implements CommonObject {

	public static final Player PLAYER_INSTANCE = new Player();

	public Player() {
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		throw new UnsupportedOperationException("Player does not have clientModel");
	}

	@Override
	public String asString() {
		return "PLAYER";
	}

	@Override
	public void onStep() {		
	}

	@Override
	public void onUse(Equipment usedEquipment) {		
	}

	@Override
	public void onTake() {		
	}
	
	@Override
	public void onJump() {
	}
	@Override
	public CommonObject clone() throws CloneNotSupportedException {
		return (CommonObject) super.clone();
	}
}
