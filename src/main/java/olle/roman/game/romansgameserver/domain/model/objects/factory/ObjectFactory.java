package olle.roman.game.romansgameserver.domain.model.objects.factory;

import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinition;

public interface ObjectFactory {

	ObjectBase createObject(ObjectDefinition definition, ObjectActions actions, int index);
	
}
