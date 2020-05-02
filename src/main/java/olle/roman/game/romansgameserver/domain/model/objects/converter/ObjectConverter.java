package olle.roman.game.romansgameserver.domain.model.objects.converter;

import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Axe;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Bridge;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Matches;
import olle.roman.game.romansgameserver.domain.model.objects.equipment.Wood;
import olle.roman.game.romansgameserver.domain.model.objects.weapon.Bow;
import olle.roman.game.romansgameserver.domain.model.objects.weapon.Sword;

public class ObjectConverter {

	public static final ObjectConverter INSTANCE = new ObjectConverter();
	
	private ObjectConverter() {
	}
	
	public Equipment fromClientModel(olle.roman.game.romansgameclient.model.objects.CommonObject clientModel, ObjectActions actions) {
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.equipment.Axe) {
			return new Axe(actions, -1);
		}
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.equipment.Matches) {
			return new Matches(actions, -1);
		}
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.equipment.Wood) {
			return new Wood(actions, -1);
		}
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.equipment.Bridge) {
			return new Bridge(actions, -1);
		}
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.weapon.Sword) {
			return new Sword(actions, -1);
		}
		if(clientModel instanceof olle.roman.game.romansgameclient.model.objects.weapon.Bow) {
			return new Bow(actions, -1);
		}
		throw new RuntimeException("Invalid model was send on server");
	}
}
