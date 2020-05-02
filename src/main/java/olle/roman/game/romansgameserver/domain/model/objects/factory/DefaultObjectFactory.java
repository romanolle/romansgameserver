package olle.roman.game.romansgameserver.domain.model.objects.factory;

import olle.roman.game.romansgameserver.domain.model.objects.Nothing;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Portal;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinition;
import olle.roman.game.romansgameserver.domain.model.objects.enemy.Archer;
import olle.roman.game.romansgameserver.domain.model.objects.enemy.Catapult;
import olle.roman.game.romansgameserver.domain.model.objects.enemy.Knight;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Axe;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Bridge;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Matches;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Wood;
import olle.roman.game.romansgameserver.domain.model.objects.obstacle.Ditch;
import olle.roman.game.romansgameserver.domain.model.objects.obstacle.Tree;
import olle.roman.game.romansgameserver.domain.model.objects.weapon.Bow;
import olle.roman.game.romansgameserver.domain.model.objects.weapon.Sword;

public class DefaultObjectFactory implements ObjectFactory {

	private static final ObjectFactory INSTANCE = new DefaultObjectFactory();

	public static ObjectFactory getInstance() {
		return INSTANCE;
	}
	
	private DefaultObjectFactory() {
	}
	
	@Override
	public ObjectBase createObject(ObjectDefinition definition, ObjectActions actions, int index) {
		switch (definition.getType()) {
			case NOTHING:
				return new Nothing(actions, index);
			case PORTAL:
				return new Portal(actions, index);
			case AXE:
				return new Axe(actions, index);
			case MATCHES:
				return new Matches(actions, index);
			case TREE:
				return new Tree(actions, index);
			case WOOD:
				return new Wood(actions, index);
			case DITCH:
				return new Ditch(actions, index);
			case BRIDGE:
				return new Bridge(actions, index);
			case KNIGHT:
				return new Knight(actions, index);
			case CATAPULT:
				return new Catapult(actions, index);
			case ARCHER:
				return new Archer(actions, index);
			case BOW:
				return new Bow(actions, index);
			case SWORD:
				return new Sword(actions, index);
		}
		throw new RuntimeException("Invalid object type");
	}

}
