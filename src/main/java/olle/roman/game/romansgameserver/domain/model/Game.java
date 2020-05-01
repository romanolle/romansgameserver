package olle.roman.game.romansgameserver.domain.model;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameclient.model.report.GameResult;
import olle.roman.game.romansgameclient.model.state.State;

public interface Game {

	void changeDirection();

	CommonObject lookAhead();

	boolean isFinished();

	State action(Action action);

	GameResult getResult();
	
	State getCurrentState();
	
}
