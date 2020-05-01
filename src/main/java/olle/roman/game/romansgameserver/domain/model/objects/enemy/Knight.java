package olle.roman.game.romansgameserver.domain.model.objects.enemy;

import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;

public class Knight extends EnemyBase {

	private static final int DEFAULT_HEALTH = EnemyBase.DEFAULT_HEALTH;

	public Knight(ObjectActions actions, int index) {
		super(actions, index, DEFAULT_HEALTH);
	}

	public Knight(ObjectActions actions, int index, int health) {
		super(actions, index, health);
	}

	@Override
	public CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.action.enemy.Knight.newInstance(getHealth());
	}

	@Override
	public String getNameForPrintString() {
		return "knight";
	}

	@Override
	protected int getDistance() {
		return 1;
	}

	@Override
	protected int getDamage() {
		return 1;
	}

}
