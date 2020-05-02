package olle.roman.game.romansgameserver.domain.model.objects;

import olle.roman.game.romansgameserver.domain.model.exception.InvalidPositionException;

public class Wall implements CommonObject {

	private static final Wall INSTANCE = new Wall();

	public static Wall getInstance() {
		return INSTANCE;
	}
	private Wall() {
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.objects.Wall.getInstance();
	}

	@Override
	public String asString() {
		return "|";
	}

	@Override
	public void onStep() {		
	}
	
	@Override
	public void onJump() {
	}

	@Override
	public void onUse(Equipment usedEquipment) {		
	}

	@Override
	public void onTake() {		
	}

	@Override
	public void staysOn() throws InvalidPositionException {
		throw new InvalidPositionException();
	}
	
	@Override
	public CommonObject clone() throws CloneNotSupportedException {
		return (CommonObject) super.clone();
	}
	
}
