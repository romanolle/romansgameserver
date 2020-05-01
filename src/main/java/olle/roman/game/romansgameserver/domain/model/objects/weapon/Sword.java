package olle.roman.game.romansgameserver.domain.model.objects.weapon;

import java.util.Collection;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Weapon;

public class Sword extends ObjectBase implements Weapon {
	
	public Sword(ObjectActions actions, int index) {
		super(actions, index);
	}


	@Override
	public CommonObject toClientObjectModel() {
		return toClientEquipment();
	}

	@Override
	public String asString() {
		return "sword";
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		return olle.roman.game.romansgameclient.model.objects.weapon.Sword.getInstance();
	}

	@Override
	public int damage() {
		return 2;
	}

	@Override
	public int distance() {
		return 1;
	}

	@Override
	public Collection<Equipment> madeFrom() throws NotPossibleToMakeException {
		throw new NotPossibleToMakeException();
	}

}
