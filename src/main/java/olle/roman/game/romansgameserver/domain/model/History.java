package olle.roman.game.romansgameserver.domain.model;

import java.util.List;

import org.checkerframework.checker.nullness.qual.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import olle.roman.game.romansgameserver.domain.model.objects.Nothing;
import olle.roman.game.romansgameserver.domain.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.Player;
import olle.roman.game.romansgameserver.domain.model.objects.Portal;
import olle.roman.game.romansgameserver.domain.model.objects.Wall;

public class History {

	private final List<CommonObject> map;
	private final HistoryState state;
	
	public History(List<CommonObject> map, HistoryState state) {
		this.map = Lists.newArrayList(
				Iterables.transform(
						map, 
						new Function<CommonObject, CommonObject>() {

							@Override
							public @Nullable CommonObject apply(@Nullable CommonObject obj) {
								try {
									return obj.clone();
								} catch (CloneNotSupportedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								return obj;
							}
						})
		);
		this.state = state;
	}
	
	public List<CommonObject> getMap() {
		return map;
	}
	
	public HistoryState getState() {
		return state;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(Wall.getInstance().asString());
		for(int index = 0 ; index < map.size() ; index++) {
			
			String out = " ";
			if(index == state.getCurrentPosition() && (map.get(index) instanceof Portal || map.get(index) instanceof Nothing)) {
				if(map.get(index) instanceof Portal) {
					out = "(" + Player.PLAYER_INSTANCE.asString() + " ON " + Portal.TEXT + ")";
				} else {
					out = Player.PLAYER_INSTANCE.asString();
				}
			} else {
				if(index == state.getCurrentPosition()) {
					out = "(" + Player.PLAYER_INSTANCE.asString() + "&" + map.get(index).asString() + ")";
				} else {
					out = map.get(index).asString();
				}
			}
				
			builder.append(" ").append(out).append(" ");
		}
		builder.append(Wall.getInstance().asString());

		builder.append(" - ")
				.append("Health: ").append(state.getHealth())
				.append(" ; Equipment: ").append(Joiner.on(",").join(state.getEquipments()))
				.append(" ; Action: ").append(state.getAction() == null ? " - " : state.getAction().getActionType().name());
		
		return builder.toString();
	}
	
}
