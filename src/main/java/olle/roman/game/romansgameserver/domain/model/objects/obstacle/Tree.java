package olle.roman.game.romansgameserver.domain.model.objects.obstacle;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Obstacle;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinitionVariables;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Axe;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Matches;
import olle.roman.game.romansgameserver.domain.model.objects.factory.DefaultObjectFactory;

public class Tree extends ObjectBase implements Obstacle {

	public Tree(ObjectActions actions, int index) {
		super(actions, index);
	}

	@Override
	public void onStep() {		
	}

	@Override
	public void onJump() {		
	}

	@Override
	public void onUse(Equipment usedEquipment) {
		if(usedEquipment instanceof Matches) {
			actions.removeObjectFromMap(getIndex());
		}
		if(usedEquipment instanceof Axe) {
			actions.changeObjectInMap(getIndex(), DefaultObjectFactory.getInstance().createObject(ObjectDefinitionVariables.WOOD_DEFINITION, actions, getIndex()));
		}
	}

	@Override
	public CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.objects.obstacle.Tree.getInstance();
	}

	@Override
	public String asString() {
		return "tree";
	}

}
