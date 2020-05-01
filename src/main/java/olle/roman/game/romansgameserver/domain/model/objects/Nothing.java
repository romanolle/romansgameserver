package olle.roman.game.romansgameserver.domain.model.objects;

public class Nothing extends ObjectBase {

	public static final String PRINTABLE_STRING = "___";

	public Nothing(ObjectActions actions, int index) {
		super(actions, index);
	}	

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.objects.Nothing.getInstance();
	}

	@Override
	public String asString() {
		return PRINTABLE_STRING;
	}

}
