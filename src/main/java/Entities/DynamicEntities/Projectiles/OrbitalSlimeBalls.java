package Entities.DynamicEntities.Projectiles;

import Components.EntityManager;
import Entities.GenericEntity;

import java.awt.*;

import static java.lang.Math.*;

public class OrbitalSlimeBalls extends Projectile{
	
	private Image SLIME_BALL;
	
	private int centerX, centerY;
	private double distanceFromCenter;
	private double angulationFromCenter;
	private int duration;
	
	private final double EPSILON = toRadians(1);
	
	public OrbitalSlimeBalls(int centerX, int centerY, double distanceFromCenter,
							 double startingAngulation, int duration, EntityManager entityManager){
		this.centerX = centerX;
		this.centerY = centerY;
		this.distanceFromCenter = distanceFromCenter;
		this.angulationFromCenter = startingAngulation;
		this.duration = duration;
		this.entityManager = entityManager;
		init();
	}
	
	public void updateCoordinates(){
		double tX = distanceFromCenter * cos(angulationFromCenter);
		double tY = distanceFromCenter * sin(angulationFromCenter);
		setX((int)(centerX + round(tX)));
		setY((int)(centerY + round(tY)));
	}
	
	@Override
	public void init() {
		SLIME_BALL = GenericEntity.setSpriteFromPath("src/resources/sprites/projectiles/slimeball.png");
		setActiveSprite(SLIME_BALL);
		
		updateCoordinates();
		
		setWidth(32);
		setHeight(32);
		setCBwidthScalar(0.9);
		setCBheightScalar(0.9);
		initCollisionBox();
	}
	
	@Override
	public void moveEntity() {
		if(duration > 0) {
			duration -= 1;
			angulationFromCenter += EPSILON;
			updateCoordinates();
		}
		else {
			setInactive();
		}
	}
}
