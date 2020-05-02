package olle.roman.game.romansgameserver.domain.game;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import olle.roman.game.romansgameclient.model.action.Action;
import olle.roman.game.romansgameclient.model.action.Attack;
import olle.roman.game.romansgameclient.model.action.Make;
import olle.roman.game.romansgameclient.model.action.Use;
import olle.roman.game.romansgameclient.model.report.GameResult;
import olle.roman.game.romansgameclient.model.state.Notification;
import olle.roman.game.romansgameclient.model.state.NotificationCode;
import olle.roman.game.romansgameclient.model.state.Severity;
import olle.roman.game.romansgameclient.model.state.State;
import olle.roman.game.romansgameserver.domain.model.Direction;
import olle.roman.game.romansgameserver.domain.model.Game;
import olle.roman.game.romansgameserver.domain.model.History;
import olle.roman.game.romansgameserver.domain.model.HistoryState;
import olle.roman.game.romansgameserver.domain.model.Result;
import olle.roman.game.romansgameserver.domain.model.exception.InvalidPositionException;
import olle.roman.game.romansgameserver.domain.model.exception.NotPossibleToMakeException;
import olle.roman.game.romansgameserver.domain.model.map.Map;
import olle.roman.game.romansgameserver.domain.model.map.Maps;
import olle.roman.game.romansgameserver.domain.model.objects.CommonObject;
import olle.roman.game.romansgameserver.domain.model.objects.Enemy;
import olle.roman.game.romansgameserver.domain.model.objects.Equipment;
import olle.roman.game.romansgameserver.domain.model.objects.Nothing;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectActions;
import olle.roman.game.romansgameserver.domain.model.objects.ObjectBase;
import olle.roman.game.romansgameserver.domain.model.objects.Wall;
import olle.roman.game.romansgameserver.domain.model.objects.Weapon;
import olle.roman.game.romansgameserver.domain.model.objects.converter.ObjectConverter;
import olle.roman.game.romansgameserver.domain.model.objects.weapon.Kick;

public class DefaultGame implements Game, Serializable, ObjectActions {

	private static final long serialVersionUID = 5302699223851426200L;

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGame.class);

	private final UUID id;
	private final String name;
	
	private final List<ObjectBase> map;
	private final List<History> history = Lists.newArrayList();
	private final int maxStepsLimit;

	private GameResult gameResult = null;
	
	private int stepCounter = 0;
	private boolean finished = false;
	
	private int currentPosition;
	private int health;
	private int maxHealth;
	private Direction direction = Direction.FORWARD;

	private List<Equipment> equipments;

	private Result result = null;

	private Collection<Notification> notifications = Lists.newArrayList();


	public DefaultGame(String name, UUID id, Map map) {
		this.name = name;
		this.id = id;
		LOGGER.debug("New game {} with id {} launched", this.name, id);

		this.currentPosition = map.getDefaultPosition();
		this.map = Maps.createMap(map.getMap(), this);
		this.maxStepsLimit = map.getMaxStepLimit();
		this.direction = map.getDirection();
		this.health = map.getMaxHealth();
		this.maxHealth = map.getMaxHealth();
		this.equipments = Lists.newArrayList(map.getDefaultEquipments());
		
		try {
			this.map.get(currentPosition).staysOn();
		} catch (InvalidPositionException e) {
			throw new RuntimeException(e);
		}
		
		storeCurrentStateToHistory(null);
	}
	
	//--------- GAME METHODS ---------------------
	@Override
	public synchronized olle.roman.game.romansgameclient.model.objects.CommonObject lookAhead() {
		return look(1).toClientObjectModel();
	}

	@Override
	public synchronized boolean isFinished() {
		return finished;
	}

	@Override
	public synchronized GameResult getResult() {
		return gameResult;
	}

	@Override
	public synchronized void changeDirection() {
		direction = direction.changeDirection();
	}

	@Override
	public synchronized State getCurrentState() {
		return new State(
				currentPosition, 
				health, 
				maxHealth,
				map.get(currentPosition) instanceof Equipment ? ((Equipment)map.get(currentPosition)).toClientEquipment() : null,
				Collections2.transform(
						equipments, 
						new Function<Equipment, olle.roman.game.romansgameclient.model.objects.Equipment>() {

							@Override
							public olle.roman.game.romansgameclient.model.objects.@Nullable Equipment apply(@Nullable Equipment equipment) {
								return equipment.toClientEquipment();
							}
						}
				),
				notifications
		);
	}

	@Override
	public synchronized State action(Action action) {
		if(isFinished()) {
			throw new RuntimeException("Game " + name + " with id " + id + " already finished");
		}
		notifications = Lists.newArrayList();
		
		stepCounter++;
		
		LOGGER.debug("Game {} ({}) counter: " + stepCounter, name, id);
		LOGGER.info("Game {} ({}) : " + direction.name());

		CommonObject objectAhead =  look();
		
		switch (action.getActionType()) {
			case STEP:
				doStep(objectAhead);
				break;
			case HEAL:
				changeHealth(1);
				break;
			case JUMP:
				doJump(objectAhead);
				break;
			case MAKE:
				doMake(action);
				break;
			case TAKE:
				doTake();
				break;
			case USE:
				doUse(action, objectAhead);
				break;
			case ATTACK:
				doAttack(action, objectAhead);
				break;
		}
		

		//player made action first, after that enemies
		for(int index = 0 ; index < map.size() ; index++) {
			if(map.get(index) instanceof Enemy) {
				((Enemy)map.get(index)).doAction(Math.abs(index - currentPosition));
			}
		}
		
		
		
		//vyzkouset notifikace a akce
		
		//TODO upravit kod (metody action) na mensi kod pro jednodussi testovani (zjistit jak na promenne - parametry || class properties
		//TODO pridat casovac, hra by nemela trvat dele jak X ms - duvod: slo by debugovat
		
		//TODO add new thing objects
//		pridat veci - dalekohled
					
//		mapa zapalky,sekyra,strom,dalekohled,dira,lucisnik - reseni-pred dirou dalekohled, vytvorit luk, zastrelit, preskocit || postavit most,bojovat
		
		//TODO udelat testy
		
		
		
		storeCurrentStateToHistory(action);
		
		if(health == 0) {
			gameFinished();
			if(result == null) {
				setResult(Result.DIED);
			}
		}
		
		if(stepCounter == maxStepsLimit) {
			gameFinished();
			setResult(Result.TO_MANY_STEPS);
		}

		
		if(isFinished()) {
			createResult();
			if(result == Result.SUCCESS) {
				notifications.add(new Notification(NotificationCode.GAME_SUCCESS));
			} else {
				notifications.add(new Notification(NotificationCode.DIED));
			}
			return getCurrentState();
		}
		
		return getCurrentState();
	}

	//--------- HANDLE ACTIONS ---------------------
	private void doStep(CommonObject objectAhead) {
		objectAhead.onStep();
	}

	private void doAttack(Action action, CommonObject objectAhead) {
		if(action instanceof Attack) {
			Attack attackAction = (Attack) action;
			Equipment equipmentToUse;
			if(attackAction.getEquipmentToUse() == null) {
				LOGGER.info("Equipment is null. As default will be used Kick");
				equipmentToUse = Kick.createKick(this);
			} else {
				equipmentToUse = findEquipment(attackAction.getEquipmentToUse());
				if(equipmentToUse == null) {
					LOGGER.warn("Could not find equipment {} in inventory. As default will be used Kick", attackAction.getEquipmentToUse());
					notifications.add(new Notification(
								NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT, 
								NotificationCode.INVENTORY_NOT_CONTAIN_EQUIPMENT.getMessage() + " (as default was used basic attack)", 
								Severity.WARNING
							)
					);
					equipmentToUse = Kick.createKick(this);
				}
			}
			Weapon weapon = null;
			if(equipmentToUse instanceof Weapon) {
				weapon = (Weapon) equipmentToUse;
			} else {
				weapon = Kick.createKick(this);
			}

			for(int distance = 1 ; distance <= weapon.distance() ; distance++) {
				objectAhead = look(distance);
				if(objectAhead instanceof Enemy) {
					objectAhead.onUse(weapon);
					break;
				}
			}
			
		} else {
			LOGGER.error("Invalid Make class. Was used {}", action.getClass().getSimpleName());
			notifications.add(Notification.createInvalidClassNotification(Make.class, action.getClass()));
		}		
	}

	private void doUse(Action action, CommonObject objectAhead) {
		if(action instanceof Use) {
			Use useAction = (Use) action;
			if(useAction.getEquipmentToUse() == null) {
				LOGGER.error("Equipment to make cannot be null");
				notifications.add(new Notification(NotificationCode.EQUIPMENT_NULL));
				return;
			}
			
			Equipment equipmentToUse = findEquipment(useAction.getEquipmentToUse());
			if(equipmentToUse == null) {
				LOGGER.error("Could not find equipment {} in inventory", useAction.getEquipmentToUse());
				notifications.add(Notification.createEquipmentNotFound(useAction.getEquipmentToUse()));
				return;
			}
			
			if(!containsError(notifications)) {
				objectAhead.onUse(equipmentToUse);
			}
		} else {
			LOGGER.error("Invalid Make class. Was used {}", action.getClass().getSimpleName());
			notifications.add(Notification.createInvalidClassNotification(Make.class, action.getClass()));
		}		
	}

	private void doTake() {
		CommonObject equipment = look(0);
		if(equipment instanceof Equipment) {
			equipments.add((Equipment) equipment);
			removeObjectFromMap(currentPosition);
		}		
	}

	private void doJump(CommonObject objectAhead) {
		objectAhead.onJump();	
	}

	private void doMake(Action action) {

		if(action instanceof Make) {
			Make makeAction = (Make) action;

			if(makeAction.getEquipment() == null) {
				LOGGER.error("Final equipment to make cannot be null");
				notifications.add(new Notification(NotificationCode.EQUIPMENT_NULL));
				return;
			}
			
			Equipment newEquipment = findEquipment(makeAction.getEquipment());
			if(newEquipment == null) {
				LOGGER.error("Could not find equipment {} in inventory", makeAction.getEquipment());
				notifications.add(Notification.createEquipmentNotFound(makeAction.getEquipment()));
				return;
			}
			
			List<Equipment> originEquipmentsState = Lists.newArrayList(equipments);
			Collection<Equipment> initialMaterials;
			try {
				initialMaterials = newEquipment.madeFrom();
				for(Equipment initialMaterial : initialMaterials) {
					if(!equipments.contains(initialMaterial)) {
						LOGGER.error("Could not find initial material {} in inventory", initialMaterial);
						notifications.add(new Notification(
									NotificationCode.INVENTORY_NOT_CONTAIN_INITIAL_MATERIAL, 
									"Could not find initial material " + initialMaterial + " in inventory"
								)
						);
					} else {
						equipments.remove(initialMaterial);
					}
				}
				
				if(!containsError(notifications)) {
					equipments.add(newEquipment);
				} else {
					equipments = originEquipmentsState;
				}

			} catch (NotPossibleToMakeException e) {
				LOGGER.error("New equipment {} can not be created.", newEquipment);
				notifications.add(new Notification(
							NotificationCode.EQUIPMENT_CANNOT_CREATE, 
							"Equipment " + newEquipment + " cannot be created"
						)
				);
			}
		} else {
			LOGGER.error("Invalid Make class. Was used {}", action.getClass().getSimpleName());
			notifications.add(Notification.createInvalidClassNotification(Make.class, action.getClass()));
		}		
	}

	//--------- OBJECT ACTIONS ---------------------
	@Override
	public void gameFinished() {
		setFinished(true);		
	}

	@Override
	public void setResult(Result result) {
		this.result = result;		
	}

	@Override
	public void move() {
		move(1);	
	}

	@Override
	public void changeHealth(int value) {
		int newHealth = health + value;
		health = newHealth > maxHealth ? maxHealth : (newHealth < 0 ? 0 : newHealth);	
	}

	@Override
	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
	}
	
	@Override
	public CommonObject look() {
		return look(1);
	}

	@Override
	public void removeObjectFromMap(int position) {
		changeObjectInMap(position, new Nothing(this, position));
	}

	@Override
	public void changeObjectInMap(int position, ObjectBase object) {
		map.remove(position);
		map.add(position, object);
	}

	//--------- PRIVATE METHODS ---------------------
	private void move(int steps) {
		currentPosition = currentPosition + steps * direction.getDirection();		
	}

	private CommonObject look(int offset) {
		int nextPosition = currentPosition + offset * direction.getDirection();
		if(nextPosition < 0 || nextPosition >= map.size()) {
			return Wall.getInstance();
		}
		return map.get(nextPosition);
	}
	
	private void setFinished(boolean finished) {
		this.finished = finished;
	}

	private Equipment findEquipment(olle.roman.game.romansgameclient.model.objects.Equipment equipment) {
		return findEquipment(ObjectConverter.INSTANCE.fromClientModel(equipment, this));
	}

	private Equipment findEquipment(Equipment equipment) {
		return equipments.contains(equipment) ? equipment : null;
	}

	private void storeCurrentStateToHistory(Action action) {
		history.add(new History(Lists.newArrayList(map), getCurrentHistoryState(action)));
	}

	private HistoryState getCurrentHistoryState(Action action) {
		return new HistoryState(
				currentPosition, 
				health, 
				maxHealth,
				map.get(currentPosition) instanceof Equipment ? ((Equipment)map.get(currentPosition)) : null,
				Lists.newArrayList(equipments),
				action
		);
	}

	
	private boolean containsError(Collection<Notification> notifications) {
		for(Notification notification : notifications) {
			if(notification.getSeverity() == Severity.ERROR) {
				return true;
			}
		}
		return false;
	}

	private GameResult createResult() {
		List<String> stringHistory = Lists.newArrayList();
		for(int index = 0 ; index < history.size() ; index++) {
			stringHistory.add(index + ":" + history.get(index).toString());
		}

		gameResult = new GameResult(
						result == null ? Result.FAILED.getResult() : result.getResult(), 
						stringHistory
		);
		return gameResult;
	}

}
