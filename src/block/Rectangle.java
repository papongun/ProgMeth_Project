package block;

import entity.Entity;

public class Rectangle {
	private int x,y,width,height;
	
	public Rectangle(int x,int y,int width,int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean collide(Entity obj) {
		return ((obj.getX()> x && obj.getX() < x+width) && (obj.getY() > y && obj.getY() < y+height));
	}
	
	public boolean collide(double dx,double dy,int radius) {
		return (dx > x && dx < x+width && dy+20 > y && dy < y+height);
	}
}
