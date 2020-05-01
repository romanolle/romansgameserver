package olle.roman.game.romansgameserver.domain.model.objects.definition;

import java.util.Map;

import com.google.common.collect.Maps;

public class ObjectDefinition {

	private final ObjectType type;
	private final Map<String, String> properties;
	
	public ObjectDefinition(ObjectType type) {
		this(type, Maps.newHashMap());
	}
	
	public ObjectDefinition(ObjectType type, Map<String, String> properties) {
		this.type = type;
		this.properties = properties;
	}
	
	public ObjectType getType() {
		return type;
	}
	public Map<String, String> getProperties() {
		return properties;
	}
	
}
