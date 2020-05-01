package olle.roman.game.romansgameserver.domain.model.objects.weapon;

import java.util.Collection;

import olle.roman.game.romansgameserver.domain.game.DefaultGame;
import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Weapon;

public class Kick extends ObjectBase implements Weapon {

	public Kick(ObjectActions actions, int index) {
		super(actions, index);
	}
	

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String asString() {
		return "";
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return asString();
	}


	@Override
	public int damage() {
		return 1;
	}


	@Override
	public int distance() {
		return 1;
	}


	public static Kick createKick(DefaultGame game) {
		return new Kick(game, -1);
	}


	@Override
	public Collection<Equipment> madeFrom() throws NotPossibleToMakeException {
		throw new NotPossibleToMakeException();
	}
}
