package Entities.DynamicEntities.Projectiles;

import Components.AudioManager;
import Components.EntityManager;
import Entities.GenericEntity;

import java.awt.*;

public class SlimeTrailBall extends Projectile{
	
	private String direction;
	private EntityManager entityManager;
	private int slimePuddleCountdown;

	public SlimeTrailBall(int centerX, int centerY, String direction, EntityManager entityManager){

		this.direction = direction;
		this.entityManager = entityManager;

		setXFromCenter(centerX);
		setYFromCenter(centerY);

		init();
	}

	@Override
	public void init() {
		slimePuddleCountdown = 0;

		setWidth(24);
		setHeight(24);
		setCBwidthScalar(0.9);
		setCBheightScalar(0.9);
		activateCollisionBox();
	}

	@Override
	public void moveEntity() {
		if (slimePuddleCountdown <= 0){
			if(direction.equals("up") || direction.equals("right")) {
				entityManager.mainGameReference.audioManager.playSoundOnce(AudioManager.SLIME_SOUND_1_INDEX);
			}
			entityManager.generateSlimePuddle(this.getCenterX(), this.getCenterY(),
					direction.equals("up") || direction.equals("down"));
			slimePuddleCountdown = 10;
		}
		slimePuddleCountdown -= 1;
		if(entityManager.checkWallsCollisions(this)){
			entityManager.generateSlimePuddle(this.getCenterX(), this.getCenterY(),
					direction.equals("up") || direction.equals("down"));
			setInactive();
		}
		switch (direction){
			case "right" ->{
				setX(getX() + 5);
			}
			case "left" ->{
				setX(getX() - 5);
			}
			case "up" ->{
				setY(getY() - 5);
			}
			case "down" ->{
				setY(getY() + 5);
			}
		}
	}
}
