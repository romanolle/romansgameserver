package olle.roman.game.romansgameserver.domain.model.objects.obstacle;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.Result;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Obstacle;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinitionVariables;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Bridge;
import olle.roman.game.romansgameserver.domain.model.objects.factory.DefaultObjectFactory;

public class Ditch extends ObjectBase implements Obstacle {

	public Ditch(ObjectActions actions, int index) {
		super(actions, index);
	}

	@Override
	public void onStep() {
		actions.move();
		actions.changeHealth(Integer.MAX_VALUE);
		actions.gameFinished();
		actions.setResult(Result.DIED);
	}

	@Override
	public CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.objects.obstacle.Ditch.getInstance();
	}
	
	@Override
	public void onUse(Equipment usedEquipment) {
		if(usedEquipment instanceof Bridge) {
			actions.changeObjectInMap(getIndex(), DefaultObjectFactory.getInstance().createObject(ObjectDefinitionVariables.BRIDGE_DEFINITION, actions, getIndex()));
		}
	}

	@Override
	public String asString() {
		return "ditch";
	}

}
