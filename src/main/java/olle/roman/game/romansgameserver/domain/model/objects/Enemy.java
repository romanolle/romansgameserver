package olle.roman.game.romansgameserver.domain.model.objects;

public interface Enemy extends CommonObject {

	void doAction(int distance);
	
	int getHealth();
	
}
