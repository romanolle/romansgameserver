package olle.roman.game.romansgameserver.domain.model.objects;

public interface Weapon extends Equipment {

	olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment();
	
	int damage();
	
	int distance();
	
}
