package entity;

import javafx.scene.image.Image;
import sharedObject.IRenderable;

public abstract class Entity implements IRenderable{
    private String name;
    protected int z;
	protected double x,y;
	protected boolean visible,destroyed;
	protected Image Image;
	
	protected Entity(){
		visible = true;
		destroyed = false;
	}
	
	@Override
	public boolean isDestroyed(){
		return destroyed;
	}
	
	@Override
	public boolean isVisible(){
		return visible;
	}
	
	@Override
	public int getZ(){
		return z;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		if (x < 20) this.x = 20;
		else if (x > 940) this.x = 940;
		else this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		if (y < 20) this.y = 20;
		else if (y > 608) this.y = 608;
		else this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void update() ;
	
	
}
