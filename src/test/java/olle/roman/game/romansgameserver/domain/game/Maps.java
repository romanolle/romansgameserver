package olle.roman.game.romansgameserver.domain.game;

import static olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinitionVariables.*;

import java.util.List;

import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.Direction;
import olle.roman.game.romansgameserver.domain.model.map.Map;
import olle.roman.game.romansgameserver.domain.model.objects.definition.ObjectDefinition;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Axe;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Matches;

public abstract class Maps {

	static final List<ObjectDefinition> MAP_DEFINITION_1 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION, NOTHING_DEFINITION);
	
	//XNNON
	static final Map MAP_1_PRIMITIVE_1 = new Map(MAP_DEFINITION_1);
	
	//NNXON
	static final Map MAP_1_PRIMITIVE_2 = new Map(MAP_DEFINITION_1, 2);
	
	//NNNOX
	static final Map MAP_1_PRIMITIVE_3 = new Map(MAP_DEFINITION_1, 4);
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_2 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, AXE_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNENNO
	static final Map MAP_2_EQUIPMENT_1 = new Map(MAP_DEFINITION_2);
	
	//NXENNO
	static final Map MAP_2_EQUIPMENT_2 = new Map(MAP_DEFINITION_2, 1);
	
	//NN(X&E)NNO
	static final Map MAP_2_EQUIPMENT_3 = new Map(MAP_DEFINITION_2, 2);
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_3 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, MATCHES_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNENNO
	static final Map MAP_3_EQUIPMENT_1 = new Map(MAP_DEFINITION_3);
	
	//NXENNO
	static final Map MAP_3_EQUIPMENT_2 = new Map(MAP_DEFINITION_3, 1);
	
	//NN(X&E)NNO
	static final Map MAP_3_EQUIPMENT_3 = new Map(MAP_DEFINITION_3, 2);
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_4 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, WOOD_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNENNO
	static final Map MAP_4_EQUIPMENT_1 = new Map(MAP_DEFINITION_4);
	
	//NXENNO
	static final Map MAP_4_EQUIPMENT_2 = new Map(MAP_DEFINITION_4, 1);
	
	//NN(X&E)NNO
	static final Map MAP_4_EQUIPMENT_3 = new Map(MAP_DEFINITION_4, 2);
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_5 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, BOW_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNENNO
	static final Map MAP_5_EQUIPMENT_1 = new Map(MAP_DEFINITION_5);
	
	//NXENNO
	static final Map MAP_5_EQUIPMENT_2 = new Map(MAP_DEFINITION_5, 1);
	
	//NN(X&E)NNO
	static final Map MAP_5_EQUIPMENT_3 = new Map(MAP_DEFINITION_5, 2);
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_6 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, SWORD_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNENNO
	static final Map MAP_6_EQUIPMENT_1 = new Map(MAP_DEFINITION_6);
	
	//NXENNO
	static final Map MAP_6_EQUIPMENT_2 = new Map(MAP_DEFINITION_6, 1);
	
	//NN(X&E)NNO
	static final Map MAP_6_EQUIPMENT_3 = new Map(MAP_DEFINITION_6, 2);

	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_7 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, DITCH_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNDNNO
	static final Map MAP_7_DITCH_1 = new Map(MAP_DEFINITION_7);
	
	//NXDNNO
	static final Map MAP_7_DITCH_2 = new Map(MAP_DEFINITION_7, 1);
	
	
	
	
	static final List<ObjectDefinition> MAP_DEFINITION_8 = Lists.newArrayList(NOTHING_DEFINITION, NOTHING_DEFINITION, TREE_DEFINITION, NOTHING_DEFINITION, NOTHING_DEFINITION, PORTAL_DEFINITION);

	//XNTNNO
	static final Map MAP_8_TREE_1 = new Map(MAP_DEFINITION_8);
	
	//NXTNNO
	static final Map MAP_8_TREE_2 = new Map(MAP_DEFINITION_8, 1);
	
	//NXTNNO
	static final Map MAP_8_TREE_3 = new Map(MAP_DEFINITION_8, 1, Direction.FORWARD, 10, 200, Lists.newArrayList(new Axe(null, -1), new Matches(null, -1)));

	
	
	//se stromem - bez niceho, se zapalkami, se sekyrou
	
	//drevo, vyroba neceho
	
	//rytir
	//archer
	//catapult
	//neco dalsiho??
	//bino+double ditch
	
}
