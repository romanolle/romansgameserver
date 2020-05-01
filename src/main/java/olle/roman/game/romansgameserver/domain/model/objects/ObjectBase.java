package olle.roman.game.romansgameserver.domain.model.objects;

public abstract class ObjectBase implements CommonObject {

	protected final ObjectActions actions;
	protected final int index;

	public ObjectBase(ObjectActions actions, int index) {
		//TODO can be null or not??
		//		Assert.notNull(actions, "Actions cannot be null");
		this.actions = actions;
		this.index = index;
	}
	

	
	@Override
	public ObjectBase clone() throws CloneNotSupportedException {
		return (ObjectBase) super.clone();
	}

	protected int getIndex() {
		return index;
	}

	@Override
	public void onUse(Equipment usedEquipment) {
	}

	@Override
	public void onTake() {		
	}
	
	@Override
	public void onJump() {
		actions.move();
		CommonObject objectAhead = actions.look();
		objectAhead.onStep();
	}
	
	@Override
	public void onStep() {
		actions.move();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actions == null) ? 0 : actions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ObjectBase other = (ObjectBase) obj;
		if (actions == null) {
			if (other.actions != null)
				return false;
		} else if (!actions.equals(other.actions))
			return false;
		return true;
	}
	
	
	
}
