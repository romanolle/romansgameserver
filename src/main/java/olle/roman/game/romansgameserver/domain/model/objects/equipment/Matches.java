package olle.roman.game.romansgameserver.domain.model.objects.equipment;

import java.util.Collection;

import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;

public class Matches extends ObjectBase implements Equipment {

	public Matches(ObjectActions actions, int index) {
		super(actions, index);
	}	

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return toClientEquipment();
	}

	@Override
	public String asString() {
		return "matches";
	}

	@Override
	public void onTake() {		
		actions.addEquipment(this);
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		return olle.roman.game.romansgameclient.model.objects.equipment.Matches.getInstance();
	}

	@Override
	public String toString() {
		return asString();
	}

	@Override
	public Collection<Equipment> madeFrom() throws NotPossibleToMakeException {
		throw new NotPossibleToMakeException();
	}

	@Override
	public boolean singletonInList() {
		return true;
	}
}
