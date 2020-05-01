package olle.roman.game.romansgameserver.domain.model;

public enum Result {

	FAILED("Failed"),
	SUCCESS("Success"),
	TO_MANY_STEPS("Failed: to many steps"), 
	DIED("Failed: died");
	
	private final String result;

	private Result(String result) {
		this.result = result;
	}
	
	public String getResult() {
		return result;
	}
}
