package olle.roman.game.romansgameserver.domain.model.objects.enemy;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;

public class Catapult extends EnemyBase {

	public Catapult(ObjectActions actions, int index) {
		super(actions, index, DEFAULT_HEALTH);
	}

	public Catapult(ObjectActions actions, int index, int health) {
		super(actions, index, health);
	}

	@Override
	public CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.action.enemy.Catapult.newInstance(getHealth());
	}

	@Override
	public String getNameForPrintString() {
		return "catapult";
	}
	
	@Override
	public void doAction(int distance) {
		if(distance == 3) {
			actions.changeHealth(-1);
		}
		if(distance == 2) {
			actions.changeHealth(-2);
		}
		if(distance == 1) {
			actions.changeHealth(-3);
		}
	}

	@Override
	protected int getDistance() {
		return 3;
	}

	@Override
	protected int getDamage() {
		return 1;
	}

}
