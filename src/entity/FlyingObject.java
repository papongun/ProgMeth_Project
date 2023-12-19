package entity;

import block.Rectangle;
import block.WaterRectangle;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import logic.StageLogic;

public abstract class FlyingObject extends Entity{
	protected int attackdamage; 
	protected int length;
	protected double angle,speed;
	protected boolean turnright;
	
	
	public FlyingObject(double angle,double x, double y) {
		this.angle = angle;
		setX(x);
		setY(y);
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		ImageView iv = new ImageView(Image);
		iv.setRotate(Math.toDegrees(angle));
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = iv.snapshot(params, null);
		if (turnright && Math.sin(angle) > 0 ) {		
			gc.drawImage(rotatedImage,getX() - length*Math.cos(angle), getY() - length*Math.sin(angle));
		} else if (turnright && !(Math.sin(angle) > 0 )){
			gc.drawImage(rotatedImage,getX() - length*Math.cos(angle), getY());
		} else if (!turnright && !(Math.sin(angle) > 0 )) {
			gc.drawImage(rotatedImage,getX(), getY());
		} else if (!turnright && Math.sin(angle) > 0 ) {
			gc.drawImage(rotatedImage,getX(), getY() - length*Math.sin(angle));
		}
	}

	
	public boolean collide(Character other) {
		if (other.isDestroyed()) return false;
		else return Math.hypot(this.x-other.x, this.y-other.y) <= other.radius+5;	
	}
	
	public boolean CheckCollide() {
		return x <= 0 || x >= 960 || y <= 0 || y >= 640 ;
	}
	
	public void UpdateCollideRect() {
		for (Rectangle rc : StageLogic.getInstance().getRects()) {
			if (rc.collide(this) && !(rc instanceof WaterRectangle)) {
				setDead();
			}
		}
	}

	public boolean isTurnright() {
		return turnright;
	}
	
	public void setTurnright(boolean turnright) {
		this.turnright = turnright;
	}
	
	protected void setDead() {
		this.destroyed = true;
		this.visible = false;
	}

}
