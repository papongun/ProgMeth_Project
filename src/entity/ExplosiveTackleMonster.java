package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class ExplosiveTackleMonster extends TackleMonster{

	public ExplosiveTackleMonster(double x,double y) {
		super(x,y);
		this.setHp(10);
		this.speed = 2.1;
		this.setAttckdamage(25);
		this.radius = 16;
		this.refreshcountwalkrate = 15;
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		if (countwalk/5 == 2) {
			Image = RenderableHolder.ExplodeSprites[2];
		} else if (countwalk/5 == 1) {
			Image = RenderableHolder.ExplodeSprites[1];
		} else 	Image = RenderableHolder.ExplodeSprites[0];
		chooseTurn(gc);
	}
	
	public void update() {
		setDead();
		UpdateAim();
		walk();
		if ((Math.hypot(h1.x-nex, h1.y-ney) <= 25) && getHp() > 0) {
			boom();
		}
		setX(nex);
		setY(ney);
	}
	
	public void boom() {
		h1.setHp(h1.getHp() - getAttckdamage(),true);
		nex = x;
		ney = y;
		this.setHp(0);
	}

}
