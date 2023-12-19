package entity;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class HeavyTackleMonster extends TackleMonster{

	public HeavyTackleMonster(double x,double y) {
		super(x,y);
		this.setAttckdamage(20);
		this.speed = 1;
		this.setHp(25);
		this.refreshcountwalkrate = 100; 
		this.radius = 16;
	}
	
	public void draw(GraphicsContext gc) {
		if (countwalk > 90) {
			Image = RenderableHolder.HeavyTackleSprites[3];
		} else if (countwalk > 80) {
			Image = RenderableHolder.HeavyTackleSprites[2];
		} else if (countwalk > 40) {
			Image = RenderableHolder.HeavyTackleSprites[1];
		} else {
			Image = RenderableHolder.HeavyTackleSprites[0];
		}
		chooseTurn(gc);
	}

}
