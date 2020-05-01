package olle.roman.game.romansgameserver.domain.model.map;

import static olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinitionVariables.*;

import java.util.List;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.Direction;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinition;
import olle.roman.game.romansgameserver.domain.model.objects.factory.DefaultObjectFactory;

public abstract class Maps {
	
	public static final List<ObjectDefinition> MAP_1_PRIMITIVE = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_2_WITH_EQUIPMENT = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_4_DITCH = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, DITCH_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_5_TREE = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, TREE_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_6_KNIGHT = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, KNIGHT_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_7_ARCHER = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, ARCHER_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_8_CATAPULT = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, AXE_DEFINITION, CATAPULT_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_9_ARCHER_WITH_DITCH = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, DITCH_DEFINITION, AXE_DEFINITION, ARCHER_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);
	public static final List<ObjectDefinition> MAP_10_DOUBLE_CATAPULT = Lists.newArrayList(TREE_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, CATAPULT_DEFINITION, CATAPULT_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION);

	public static Map map1Primitive() {
		return new Map(MAP_1_PRIMITIVE);
	}

	public static Map map2PrimitiveBackDirection() {
		return new Map(MAP_1_PRIMITIVE, 2, Direction.BACKWARD, 10, 50);
	}

	public static List<ObjectBase> createMap(List<ObjectDefinition> definitions, ObjectActions actions) {
		List<ObjectBase> out = Lists.newArrayList();
		
		for(int index = 0 ; index < definitions.size() ; index++) {
			out.add(index, DefaultObjectFactory.getInstance().createObject(definitions.get(index), actions, index));
		}
		return out;
	}

	public static Map map3PrimitiveWithEquipment() {
		return new Map(MAP_2_WITH_EQUIPMENT);
	}

	public static Map map4PrimitiveBackwardWithEquipment() {
		return new Map(MAP_2_WITH_EQUIPMENT,2, Direction.BACKWARD,10,50);
	}

	public static Map map7Ditch() {
		return new Map(MAP_4_DITCH,2,Direction.FORWARD,10,50);
	}

	public static Map map10Tree() {
		return new Map(MAP_5_TREE,2,Direction.FORWARD,10,50);
	}

	public static Map map12Knight() {
		return new Map(MAP_6_KNIGHT,2,Direction.BACKWARD,10,50);
	}

	public static Map map14Archer() {
		return new Map(MAP_7_ARCHER,2,Direction.BACKWARD,10,50);
	}

	public static Map map15ArcherWithDitch() {
		return new Map(MAP_9_ARCHER_WITH_DITCH,3,Direction.BACKWARD,14,500);
	}

	public static Map map16Catapult() {
		return new Map(MAP_8_CATAPULT,2,Direction.FORWARD,16,500);
	}

	public static Map map17DoubleCatapult() {
		return new Map(MAP_10_DOUBLE_CATAPULT,1,Direction.FORWARD,30,1000);
	}
}
