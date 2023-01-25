package GameStates;

import Application.KeyHandler;
import Components.EntityManager;
import Components.Stage;

import java.awt.*;


public class MainGame extends GameState{

	private Stage stage;
	
	//private CollisionBox boundaries;
	
	private int clearedTotalStages;
	
	private EntityManager entityManager;
	
	public MainGame(KeyHandler keyH){
		this.keyH = keyH;
		setActive();
		background.loadMainGameBackground();
		
		entityManager = new EntityManager();
		
		stage = new Stage(entityManager);
		//stage.loadRandomStage(enemies);
		
		clearedTotalStages = 0;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		entityManager.renderEntities(g);
	}
	
	@Override
	public void processInput() {

		//movimento
		if(keyH.upPressed && keyH.rightPressed){
			entityManager.setNextPlayerInstruction("up-right");
		}
		else if(keyH.upPressed && keyH.leftPressed){
			entityManager.setNextPlayerInstruction("up-left");
		}
		else if(keyH.downPressed && keyH.rightPressed){
			entityManager.setNextPlayerInstruction("down-right");
		}
		else if(keyH.downPressed && keyH.leftPressed) {
			entityManager.setNextPlayerInstruction("down-left");
		}
		else if(keyH.upPressed){
			entityManager.setNextPlayerInstruction("up");
		}
		else if(keyH.downPressed){
			entityManager.setNextPlayerInstruction("down");
		}
		else if(keyH.leftPressed){
			entityManager.setNextPlayerInstruction("left");
		}
		else if(keyH.rightPressed) {
			entityManager.setNextPlayerInstruction("right");
		}
		else {
			entityManager.setNextPlayerInstruction("stop");
		}
		
		//shooting
		if (keyH.shootUp && entityManager.getPlayer().canShoot()) {
			entityManager.newArrow("up");
		}
		else if (keyH.shootDown && entityManager.getPlayer().canShoot()) {
			entityManager.newArrow("down");
		}
		else if (keyH.shootLeft && entityManager.getPlayer().canShoot()) {
			entityManager.newArrow("right");
		}
		else if (keyH.shootRight && entityManager.getPlayer().canShoot()) {
			entityManager.newArrow("left");
		}

		//DEBUG ONLY
		/*if(keyH.killAll){
			for(Enemy enemy: enemies){
				enemy.setInactive();
			}
		}*/
		
	}
	
	@Override
	public void update() {
		if (clearedTotalStages >= 10){
			setInactive();
		}
		processInput();
		entityManager.getPlayer().move();
		entityManager.getPlayer().updateCoolDown();
		entityManager.updateArrows();
		entityManager.updateEnemies();
		entityManager.checkDynamicCollisions();
		/*if(isGameOver()){
			setInactive();
		}
		else if(checkStageCompletion()){
			door.setActive();
		}*/
	}
}
