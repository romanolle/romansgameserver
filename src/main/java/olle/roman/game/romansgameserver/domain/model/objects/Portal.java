package olle.roman.game.romansgameserver.domain.model.objects;

import olle.roman.game.romansgameserver.domain.model.Result;

public class Portal extends ObjectBase {

	public static final String TEXT = "PORTAL";

	public Portal(ObjectActions actions, int index) {
		super(actions, index);
	}

	@Override
	public olle.roman.game.romansgameclient.model.objects.CommonObject toClientObjectModel() {
		return olle.roman.game.romansgameclient.model.objects.Portal.getInstance();
	}

	@Override
	public String asString() {
		return TEXT;
	}

	@Override
	public void onStep() {
		actions.move();
		actions.gameFinished();
		actions.setResult(Result.SUCCESS);
	}
	
}
