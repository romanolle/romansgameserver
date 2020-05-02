package olle.roman.game.romansgameserver.domain.model.objects.enemy;

import olle.roman.game.romansgameserver.domain.model.exception.InvalidPositionException;
import olle.roman.game.romansgameserver.domain.model.objects.Enemy;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Weapon;

public abstract class EnemyBase extends ObjectBase implements Enemy {

	public static final int DEFAULT_HEALTH = 10;
	
	protected int health;
	
	public EnemyBase(ObjectActions actions, int index, int health) {
		super(actions, index);
		this.health = health;
	}
	
	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void onStep() {		
	}

	@Override
	public void onJump() {		
	}

	@Override
	public void onTake() {		
	}

	@Override
	public void staysOn() throws InvalidPositionException {
		throw new InvalidPositionException();
	}

	@Override
	public void onUse(Equipment usedEquipment) {
		if(usedEquipment instanceof Weapon) {
			health = health - ((Weapon)usedEquipment).damage();
		} else {
			health--;
		}
		if(health <= 0) {
			actions.removeObjectFromMap(getIndex());
		}
	}
	
	@Override
	public void doAction(int distance) {
		if(distance <= getDistance()) {
			actions.changeHealth(-1 * getDamage());
		}
	}
	
	@Override
	public String asString() {
		return getNameForPrintString() + "(" + health + ")";
	}
	
	protected abstract String getNameForPrintString();

	protected abstract int getDamage();

	protected abstract int getDistance();

}
