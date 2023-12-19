package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public abstract class Character extends Entity{
	private int hp;
	private int attckdamage;
	protected int radius = 32;
	protected int DeadCountDown = 0;
	protected double aimangle;
	protected boolean turnright;
    
	public Character() {
    	super();
    	this.z = 1;
	}
    
    
    public void chooseTurn(GraphicsContext gc) {
    	if (turnright) {		
			gc.drawImage(Image,getX() - radius, getY() - radius);
		} else {
			gc.drawImage(Image,getX() + radius, getY() - radius,-2*radius,2*radius);
		}
    }

	public int getHp() {
		return this.hp;
	}

	public void setHp(int hp) {
		if (hp < 0) {
			this.hp = 0;
		} else this.hp = hp;
	}

	public int getAttckdamage() {
		return this.attckdamage;
	}

	public void setAttckdamage(int attckdamage) {
		this.attckdamage = attckdamage;
	}
	
	public void setDead() {
		if (this.getHp() <= 0) {
			this.destroyed = true;
		}
	}
	
	public void drawDead(GraphicsContext gc) {
		if (DeadCountDown > 10) {
			gc.drawImage(RenderableHolder.DeadSprites[2], x-radius, y-radius);
		} else if (DeadCountDown > 5) {
			gc.drawImage(RenderableHolder.DeadSprites[1], x-radius, y-radius);
		} else 	gc.drawImage(RenderableHolder.DeadSprites[0], x-radius, y-radius);
		++DeadCountDown;
		if (DeadCountDown > 15) {
			visible = false;
			DeadCountDown = 0;
		}
	}
	
	public double aim(Character attacker,Character defender) {
        double dx = defender.getX() - attacker.getX();
        double dy = defender.getY() - attacker.getY();
    	double angle = Math.atan2(dy, dx);
    	if (angle < 0) {
    		angle += 2 *Math.PI;
    	}
        return angle;
    }


	public double getAimangle() {
		return aimangle;
	}


	public void setAimangle(double aimangle) {
		this.aimangle = aimangle;
	}
}
