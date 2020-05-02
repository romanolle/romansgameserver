package olle.roman.game.romansgameserver.domain.model.objects;

import olle.roman.game.romansgameserver.domain.model.exception.InvalidPositionException;

public interface CommonObject extends Cloneable {

	void onStep();

	void onJump();
	
	void onUse(Equipment usedEquipment);
	
	void onTake();
		
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel();
	
	public String asString();

	public void staysOn() throws InvalidPositionException;
	
	CommonObject clone() throws CloneNotSupportedException;
	
}
