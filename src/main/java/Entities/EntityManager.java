package Entities;

import Entities.Boundaries.HorizontalWall;
import Entities.Boundaries.VerticalWall;
import Entities.Enemies.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

	//private CollisionBox boundaries;
	
	private Player player;
	private Door door;
	private List<Enemy> enemies;
	private List<Projectile> friendlyProjectiles;
	private List<Projectile> hostileProjectiles;
	private HorizontalWall upperWall, lowerWall;
	private VerticalWall leftWall, rightWall;
	//TODO lista di ostacoli
	
	public EntityManager(){
		//boundaries = new CollisionBox(64, 32, 1088 - 128, 576 + 32, 1.0, 1.0);
		
		player = new Player(this);
		door = new Door(64 * 7, 0, this);
		enemies = new ArrayList<>();
		friendlyProjectiles = new ArrayList<>();
		hostileProjectiles = new ArrayList<>();
		upperWall = new HorizontalWall(0, 0, this);
		lowerWall = new HorizontalWall(0, -512, this);
		leftWall = new VerticalWall(0, 0, this);
		rightWall = new VerticalWall(1024, 0, this);
	}
	
	public Player getPlayer() {
		return player;
	}
	public int getPlayerX() {
		return player.getX();
	}
	public int getPlayerY(){
		return player.getY();
	}
	
	public boolean checkObstaclesCollisions(GenericEntity entity){
		if(entity.getCollisionBox().checkCollision(upperWall.getCollisionBox())
				&& entity.getCollisionBox().checkCollision(lowerWall.getCollisionBox())
				&& entity.getCollisionBox().checkCollision(leftWall.getCollisionBox())
				&& entity.getCollisionBox().checkCollision(rightWall.getCollisionBox())){
			System.out.println("STAI COLLIDENDO CON UN OGGETTO, FERMATI PER FAVORFE CHE NON FUNZIONANO I MURI");
			return true;
		}
		return false;
	}
	
	public void checkDynamicCollisions(){
		if(door.checkIfActive() && player.getCollisionBox().checkCollision(door.getCollisionBox())){
			//clearedTotalStages++;
			//loadNextStage();
		}
		for(Enemy enemy: enemies){
			if(enemy.checkIfActive() && enemy.getCollisionBox().checkCollision(player.getCollisionBox())){
				player.setInactive();
			}
			for(Projectile arrow: friendlyProjectiles){
				if(enemy.checkIfActive() && enemy.getCollisionBox().checkCollision(arrow.getCollisionBox())){
					arrow.setInactive();
					enemy.lowerHealth();
				}
			}
		}
	}
	
	public void updateArrows(){
		for(int i = 0; i < friendlyProjectiles.size(); i++){
			friendlyProjectiles.get(i).checkBoundaries();
			if (friendlyProjectiles.get(i).checkIfActive()){
				if(friendlyProjectiles.get(i).getAxis() && friendlyProjectiles.get(i).getDirection()){
					//la freccia si muove a destra
					friendlyProjectiles.get(i).calculateTranslations(friendlyProjectiles.get(i).getSpeed(), 0);
				}
				else if(friendlyProjectiles.get(i).getAxis() && friendlyProjectiles.get(i).getDirection() == false){
					//la freccia si muove a sinistra
					friendlyProjectiles.get(i).calculateTranslations(friendlyProjectiles.get(i).getSpeed() * -1, 0);
				}
				else if (friendlyProjectiles.get(i).getAxis() == false && friendlyProjectiles.get(i).getDirection()){
					//la freccia si muove verso il basso
					friendlyProjectiles.get(i).calculateTranslations(0, friendlyProjectiles.get(i).getSpeed());
				}
				else if (friendlyProjectiles.get(i).getAxis() == false && friendlyProjectiles.get(i).getDirection() == false){
					//la freccia si muove verso l'alto
					friendlyProjectiles.get(i).calculateTranslations(0, friendlyProjectiles.get(i).getSpeed() * -1);
				}
			}
			else {
				friendlyProjectiles.remove(i);
				i-= 1;
			}
		}
	}
	
	public void updateEnemies(){
		for(Enemy enemy: enemies){
			enemy.updateBehaviour();
		}
	}
	
	public boolean isGameOver(){
		if(!player.checkIfActive()){
			return true;
		}
		return false;
	}
	
	public boolean checkStageCompletion(){
		for(Enemy enemy: enemies){
			if(enemy.checkIfActive()){
				return false;
			}
		}
		return true;
	}
	
	/*public void loadNextStage(){
		player.setY(512 - 64);
		player.setX(512);
		stage.loadRandomStage(enemies);
		door.setInactive();
	}/
	 */
	
	public void renderEntities(Graphics g){
		if(door.checkIfActive()){
			door.paint(g);
		}
		player.paint(g);
		for (Projectile arrow: friendlyProjectiles){
			arrow.paint(g);
		}
		for (Enemy enemy: enemies) {
			if(enemy.checkIfActive()) {
				enemy.paint(g);
			}
		}
	}
}
