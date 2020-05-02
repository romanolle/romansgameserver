package olle.roman.game.romansgameserver.domain.model.objects.equipment;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;

public class Axe extends ObjectBase implements Equipment {

	private static final List<Equipment> INITIAL_MATERIALS = Lists.newArrayList(
				new Wood(null, -1)
	);
	
	protected final int axeCode = 0;	
	
	public Axe(ObjectActions actions, int index) {
		super(actions, index);
	}	

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return toClientEquipment();
	}

	@Override
	public String asString() {
		return "axe";
	}

	@Override
	public void onTake() {		
		actions.addEquipment(this);
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment() {
		return olle.roman.game.romansgameclient.model.objects.equipment.Axe.getInstance();
	}

	@Override
	public String toString() {
		return asString();
	}

	@Override
	public Collection<Equipment> madeFrom() {
		return INITIAL_MATERIALS;
	}
}
