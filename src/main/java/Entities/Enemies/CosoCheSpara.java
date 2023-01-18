package Entities.Enemies;

import static java.lang.Math.abs;

public class CosoCheSpara extends Enemy{

	public CosoCheSpara(int x, int y){
		super(x, y);
	}

	@Override
	public void init() {
		//l'estremo è escluso, velocità a cui viene sommata maximumSpeed
		//verrà sommato a minimumSpeed
		setRandomSpeed(1, 8);
		setWidth(64);
		setHeight(64);
		setCBwidthScalar(0.7);
		setCBheightScalar(0.9);
		setRandomHealth(2, 1);
		setSprite("src/resources/sprites/png/player_sample.png");
	}

	@Override
	public void updateBehaviour(int playerX, int playerY) {
		/*
		 * angolo min = arctan(cateto min / cateto magg)
		 * */
		int deltaX = playerX - getX();
		int deltaY = playerY - getY();

		double angle;
		int traslX, traslY;

		if(abs(deltaX) >= abs(deltaY)){
			angle = Math.atan((double) abs(deltaY) / abs(deltaX));
			traslX = (int) (getSpeed() * Math.cos(angle));
			traslY = (int) (getSpeed() * Math.sin(angle));
		}
		else {
			angle = Math.atan((double) abs(deltaX) / abs(deltaY));
			traslX = (int) (getSpeed() * Math.sin(angle));
			traslY = (int) (getSpeed() * Math.cos(angle));
		}

		if(traslX < 1 || traslY < 1) {
			traslX = traslX * -1;
			traslY = traslY * -1;
		}
		if(deltaX < 0){
			traslX = traslX * -1;
		}
		if(deltaY < 0){
			traslY = traslY * -1;
		}
		setX(keepXBoundaries(getX() + traslX));
		setY(keepYBoundaries(getY() + traslY));
	}
}
