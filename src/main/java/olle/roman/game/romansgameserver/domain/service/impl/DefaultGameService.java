package olle.roman.game.romansgameserver.domain.service.impl;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Maps;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.objects.CommonObject;
import olle.roman.game.romansgameclient.model.report.GameResult;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameclient.service.GameService;
import olle.roman.game.romansgameserver.domain.game.DefaultGame;
import olle.roman.game.romansgameserver.domain.model.Game;

public class DefaultGameService implements GameService {

	private final Map<UUID, DefaultGame> games = Maps.newHashMap();
	
	@Override
	public UUID createNewGame(String name) {
		UUID id = UUID.randomUUID();
		games.put(id, new DefaultGame(name, id));
		return id;
	}

	@Override
	public boolean isFinished(UUID id) {
		return findGame(id).isFinished();
	}

	private Game findGame(UUID id) {
		Game game = games.get(id);
		if(game == null) {
			throw new RuntimeException("Invalid id");
		}
		return game;
	}

	@Override
	public State action(UUID id, Action action) {
		return findGame(id).action(action);
	}

	@Override
	public CommonObject look(UUID id) {
		return findGame(id).lookAhead();
	}

	@Override
	public GameResult getResult(UUID id) {
		return findGame(id).getResult();
	}

	@Override
	public State getCurrentState(UUID id) {
		return findGame(id).getCurrentState();
	}

	@Override
	public void changeDirection(UUID id) {
		findGame(id).changeDirection();
	}


}
