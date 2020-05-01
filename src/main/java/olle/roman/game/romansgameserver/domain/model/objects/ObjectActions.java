package olle.roman.game.romansgameserver.domain.model.objects;

import olle.roman.game.romansgameserver.domain.model.Result;

public interface ObjectActions {

	void gameFinished();
	void setResult(Result result);
	void move();
	void addEquipment(Equipment equipment);
	/**
	 * Increase health for positive numbers or decrease health for negative numbers.
	 * @param value How much health will be increased or decreased.
	 */
	void changeHealth(int value);
	CommonObject look();
	void removeObjectFromMap(int index);
	void changeObjectInMap(int index, ObjectBase object);
}
