package olle.roman.game.romansgameserver.domain.model.objects.weapon;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Weapon;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Wood;

/**
 * Bow can attack on distace 2. But hits first enemy on distance 1 if there is not any enemy after that is possible to hit enemy on distance 2.
 * @author roman.olle
 *
 */
public class Bow extends ObjectBase implements Weapon {

	private static final List<Equipment> INITIAL_MATERIALS = Lists.newArrayList(
				new Wood(null, -1)
	);
	
	public Bow(ObjectActions actions, int index) {
		super(actions, index);
	}


	@Override
	public CommonObject toClientObjectModel() {
		return toClientEquipment();
	}

	@Override
	public String asString() {
		return "bow";
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		return olle.roman.game.romansgameclient.model.objects.weapon.Bow.getInstance();
	}

	@Override
	public int damage() {
		return 1;
	}

	@Override
	public int distance() {
		return 2;
	}

	@Override
	public Collection<Equipment> madeFrom() {
		return INITIAL_MATERIALS;
	}

	@Override
	public boolean singletonInList() {
		return true;
	}

}
