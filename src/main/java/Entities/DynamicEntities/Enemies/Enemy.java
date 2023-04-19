package Entities.DynamicEntities.Enemies;

import Entities.StaticEntities.BloodStain;
import Entities.DynamicEntities.DynamicEntity;

import java.awt.*;
import java.util.Random;

import static java.lang.Math.abs;

public abstract class Enemy extends DynamicEntity {

	protected int activationWaiting = 10;
	private String currentBehaviour = "default";
	public boolean canGenerateBloodStain = false;
	protected boolean performDeathActions = true;
	
	public boolean checkActivation(){
		if(activationWaiting > 0){
			activationWaiting -= 1;
		}
		else {
			return true;
		}
		return false;
	}

	public void setRandomHealth(int minimumHealth, int maximumHealth){
		Random random = new Random();
		int health = random.nextInt(minimumHealth) + maximumHealth;
		setHealth(health);
	}
	public void setRandomSpeed(int minimumSpeed, int maximumSpeed){
		Random random = new Random();
		int speed = random.nextInt(minimumSpeed) + maximumSpeed;
		setSpeed(speed);
	}
	public BloodStain generateBloodStain(){
		canGenerateBloodStain = false;
		return new BloodStain(this.getCenterX(), this.getCenterY());
	}
	//metodi riguardanti lo stato del nemico----------------------------------------------------------------------------
	public String getCurrentBehaviour(){
		return currentBehaviour;
	}

	public void changeBehaviourTo(String newBehaviour){
		this.currentBehaviour = newBehaviour;
	}
	
	public abstract void updateBehaviour();
	//------------------------------------------------------------------------------------------------------------------
}
