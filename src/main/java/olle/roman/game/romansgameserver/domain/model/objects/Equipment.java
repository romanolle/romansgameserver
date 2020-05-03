package olle.roman.game.romansgameserver.domain.model.objects;

import java.util.Collection;

import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;

public interface Equipment extends CommonObject {

	Collection<Equipment> madeFrom() throws NotPossibleToMakeException;
	
	boolean singletonInList();
	
	olle.roman.game.romansgameclient.model.objects.Equipment toClientEquipment();
	
}
