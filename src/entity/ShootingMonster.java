	package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import logic.Timer;
import sharedObject.RenderableHolder;

public class ShootingMonster extends Monster{
	protected int speed;
	protected boolean shoot;
	protected Timer time = new Timer();
	protected Image[] images;
	public ShootingMonster(double x,double y) {
		setX(x);
    	setY(y);
    	this.setAttckdamage(8);
        this.setHp(20);
        this.images = RenderableHolder.ShootSprites;
        this.radius = 22;
        this.speed = 3;
        this.shoot = false;
    }
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		if (time.getSeconds() == speed-1 && time.getMs() > 40) {
			Image = images[3];
		} else if (time.getSeconds() == speed-1 && time.getMs() > 20) {
			Image = images[2];
		} else if (time.getMs() == speed-1 && time.getMs() > 0) {
			Image = images[1];
		} else Image = images[0];
		chooseTurn(gc);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		setDead();	
		UpdateAim();
		time.increaseTimer(2);
		UpdateShoot();
		
	}
	

	public void UpdateShoot() {
		if (time.getSeconds() > 0 && time.getSeconds() % speed == 0) {
			time.reset();
			shoot = true;
		}
	}

	public boolean isShoot() {
		return shoot;
	}

	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}

	public boolean isTurnright() {
		return turnright;
	}

	public void setTurnright(boolean turnright) {
		this.turnright = turnright;
	}

	public double getAimangle() {
		return this.aimangle;
	}

	public void setAimangle(double aimangle) {
		this.aimangle = aimangle;
	}
	
	
	
	
	

}
