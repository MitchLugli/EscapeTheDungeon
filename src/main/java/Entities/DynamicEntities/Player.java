package Entities.DynamicEntities;

import Components.EntityManager;
import Components.Vector2D;
import Entities.GenericEntity;

import java.awt.*;

public class Player extends DynamicEntity {

	private Image LEFT_PLAYER;
	
	private int shootCoolDown; //valore fisso che indica ogni quanti frame il giocatore può sparare una freccia
	private int shootCoolDownValue; //valore che incrementa
	private boolean hasShot;

	public Player(EntityManager entityManager) {
		init();
		super(512, 256, entityManager);//tmp
	}
	
	@Override
	public void init() {
		//CARICAMENTO SPRITE
		LEFT_PLAYER = setSpriteFromPath("src/resources/sprites/png/player_front.png");
		setActiveSprite(LEFT_PLAYER);
		
		setHeight(64); //tmp
		setWidth(64); //tmp
		setSpeed(10); //tmp
		setCBwidthScalar(0.8);
		setCBheightScalar(0.8);
		shootCoolDown = 15;
		shootCoolDownValue = 0;
		hasShot = false;
		setCanPassThroughWalls(false);
		
		translation = new Vector2D(getSpeed());
	}

	public void updateCoolDown(){
		shootCoolDownValue += 1;
	}

	public boolean canShoot() {
		if (hasShot) {
			if (shootCoolDown - shootCoolDownValue > 0) {
				//shootCoolDownValue += 1;
			}
			else {
				hasShot = false;
				shootCoolDownValue = 0;
			}
			return false;
		}
		//hasShoot = false, quindi il player può sparare
		else {
			hasShot = true;
			shootCoolDownValue = 0;
			return true;
		}
	}
	
	@Override
	public void move() {
		boolean canMove = true;
		switch (entityManager.getNextPlayerInstruction()){
			case "up" -> {
				translation.setAngulationToObjective(0, -1);
			}
			case "down" -> {
				translation.setAngulationToObjective(0, 1);
			}
			case "right" -> {
				translation.setAngulationToObjective(1, 0);
			}
			case "left" -> {
				translation.setAngulationToObjective(-1, 0);
			}
			case "up-right" -> {
				translation.setAngulationToObjective(1, -1);
			}
			case "up-left" -> {
				translation.setAngulationToObjective(-1, -1);
			}
			case "down-right" -> {
				translation.setAngulationToObjective(1, 1);
			}
			case "down-left" -> {
				translation.setAngulationToObjective(-1, 1);
			}
			case "stop" -> {
				translation.setAngulationToObjective(0, 0);
				canMove = false;
			}
		}
		if(canMove) {
			setX(getX() + translation.getXTranslation());
			setY(getY() + translation.getYTranslation());
		}
	}
}
