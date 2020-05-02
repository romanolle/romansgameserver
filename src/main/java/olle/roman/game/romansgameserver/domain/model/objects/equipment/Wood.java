package olle.roman.game.romansgameserver.domain.model.objects.equipment;

import java.util.Collection;

import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;

public class Wood extends ObjectBase implements Equipment {

	public Wood(ObjectActions actions, int index) {
		super(actions, index);
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return toClientEquipment();
	}

	@Override
	public String asString() {
		return "wood";
	}

	@Override
	public void onTake() {		
		actions.addEquipment(this);
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		return olle.roman.game.romansgameclient.model.objects.equipment.Wood.getInstance();
	}

	@Override
	public String toString() {
		return asString();
	}

	@Override
	public Collection<Equipment> madeFrom() throws NotPossibleToMakeException {
		throw new NotPossibleToMakeException();
	}
	
}
