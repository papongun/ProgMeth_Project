package entity;

import sharedObject.RenderableHolder;

public class SpeedShootingMonster extends ShootingMonster {

	public SpeedShootingMonster(double x,double y) {
		super(x,y);
        this.images = RenderableHolder.SpeedShootSprites;
        this.speed = 1;
        this.setAttckdamage(5);
	}

}
