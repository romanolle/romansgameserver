package olle.roman.game.romansgameserver.domain.model.objects.enemy;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;

public class Archer extends EnemyBase {

	public Archer(ObjectActions actions, int index) {
		super(actions, index, DEFAULT_HEALTH);
	}

	public Archer(ObjectActions actions, int index, int health) {
		super(actions, index, health);
	}

	@Override
	public CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.action.enemy.Archer.newInstance(getHealth());
	}

	@Override
	public String getNameForPrintString() {
		return "archer";
	}

	@Override
	protected int getDistance() {
		return 2;
	}

	@Override
	protected int getDamage() {
		return 1;
	}
	

}
