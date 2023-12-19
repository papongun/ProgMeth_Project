package entity;

import sharedObject.RenderableHolder;

public class Fireball extends FlyingObject{
	
	private Hero h1;
	
	public Fireball(double angle,double x, double y) {
		super(angle,x,y);
		this.length = 40;
		this.speed = 5;
		Image = RenderableHolder.FireballSprite;
	}
	
	@Override
	public void update() {
		this.x += speed * Math.cos(angle);
		this.y += speed * Math.sin(angle);
		UpdateCollideRect();
		if (CheckCollide() || collide(h1)) {
			if (collide(h1)) {
				h1.setHp(h1.getHp() - attackdamage,false);
			}
			setDead();
		}
		
	}
	
	public Hero getH1() {
		return h1;
	}

	public void setH1(Hero h1) {
		this.h1 = h1;
	}

	public int getAttackdamage() {
		return attackdamage;
	}

	public void setAttackdamage(int attackdamage) {
		this.attackdamage = attackdamage;
	}
	
}
