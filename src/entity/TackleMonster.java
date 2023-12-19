package entity;

import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;

public class TackleMonster extends Monster{
	protected int  refreshcountwalkrate;
	protected int countwalk,attackcount;
	protected double speed;
	protected double nex,ney;
	protected Image initImage;
	
    public TackleMonster(double x,double y) {
    	setX(x);
    	setY(y);
    	nex = x;
    	ney = y;
    	this.speed = 1.2;
    	this.refreshcountwalkrate = 75;
    	this.setAttckdamage(10);
        this.setHp(15);
        this.initImage = RenderableHolder.tackleSprite;
    }
    

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.RED);
		Image = new WritableImage(initImage.getPixelReader(),countwalk/25*64,0,64,64);
		chooseTurn(gc);
	}

	@Override
	public void update() {
		setDead();
		UpdateAim();
		walk();
		AttackandCooldown();
		setX(nex);
		setY(ney);
	}

	public void AttackandCooldown() {
		if (Math.hypot(h1.x-nex, h1.y-ney) <= 25) {
			if (attackcount % 100 == 0) {				
				h1.setHp(h1.getHp() - getAttckdamage(),true);
			} else {
				attackcount = attackcount%100;
			}
			attackcount++;
			nex = x;
			ney = y;
		} else attackcount = 0;	
	}


	public void walk() {
		nex = this.getX()+Math.cos(aimangle) * speed;
		ney = this.getY()+Math.sin(aimangle) * speed;
		setCountwalk(++countwalk);
	}
	
	public int getCountwalk() {
		return countwalk;
	}

	public void setCountwalk(int count) {
		this.countwalk = countwalk%refreshcountwalkrate;
	}
	
	
	
	
	




    
}
