package olle.roman.game.romansgameserver.domain.model.objects;

public interface CommonObject extends Cloneable {

	void onStep();

	void onJump();
	
	void onUse(Equipment usedEquipment);
	
	void onTake();
		
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel();
	
	public String asString();
	
	CommonObject clone() throws CloneNotSupportedException;
	
}
