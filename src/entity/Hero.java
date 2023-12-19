package entity;



import block.Rectangle;
import input.InputUtility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameLogic;
import logic.SoundLogic;
import logic.StageLogic;
import logic.Timer;
import sharedObject.RenderableHolder;

public class Hero extends Character{	
	private String ArrowName;
	private int countwalk = 0;
	private int maxhp;
	private int attackspeed;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;
	private double walkspeed;
	private double nex,ney;
	private boolean standstill = true;
	private boolean shoot = false;
	private boolean flashing = false;
	private boolean Percing;
	private Timer time = new Timer();
	private Monster target;

	public Hero() {
		super();
		this.z = 10;
		this.setName("Hero");
		initXY();
	}
	
	
	public void initXY() {
		setX(160);
		setY(320);
		nex = x;
		ney = y;
		time.reset();
	}
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLUE);
		if (!standstill) {		
			Image = new WritableImage(RenderableHolder.herowalkSprite.getPixelReader(),countwalk/10*64,0,64,64);
		} else {
			int count ;
			if (time.getSeconds() >= 1 && !isEmpty()) {
				count = 2;
			} else if (time.getMs() > 10 && !isEmpty()) {
				count = 1;
			} else {
				count = 0;
			}
			Image = new WritableImage(RenderableHolder.herostillSprite.getPixelReader(),count*64,0,64,64);
		}
		chooseTurn(gc);
		gc.setFill(Color.FIREBRICK);
		double hpbar = (double) getHp()/maxhp * 180;
		gc.fillRect(70, 580, hpbar, 50);
		
		gc.setFill(Color.WHITE);
		gc.setFont(Font.loadFont(ClassLoader.getSystemResource("Macondo-Regular.ttf").toString(), 32));
		gc.fillText(String.valueOf(getHp()) + " / " + maxhp, 280, 615);
	}




	@Override
	public void update(){
		if (this.getHp() <= 0) {
			setDead();
			return;
		}		
		UpdateVisible();		
		UpdateCountWalk();
		time.increaseTimer(2);
		MoveCharacter();
		UpdateCollideRect();
		setY(ney);
		setX(nex);
		setShootandTarget();
	}
	


	private void setShootandTarget() {
		if (!isEmpty()) {
			if (time.getSeconds() >= attackspeed && standstill) {
				setAimangle(aim(this,this.target));
				standstill = false;
				setShoot(true);
				time.reset();
			};
			target = null;
			for (Monster m : GameLogic.Monsters) {
				if (target == null) {
					target = m;
				} else if (Math.hypot(this.x-m.x, this.y-m.y) < Math.hypot(this.x-target.x, this.y-target.y)) {
					target = m;
				}
			}			
			if (standstill) {
				if (target != null && (target.x - this.x) < 0) {
					this.turnright = false;
				}else this.turnright = true;
			}
		} else setShoot(false);	
	}


	public void UpdateCollideRect() {
		for (Rectangle rc : StageLogic.getInstance().getRects()) {
			if (rc.collide(nex,ney, radius)) {				
				ney = y;
				nex = x;
				return;
			}
		}
	}


	public void UpdateCountWalk() {
		if (!standstill) {	
			standstill = true;
		} else {
			countwalk = 0;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}


	public void MoveCharacter() {
		if (InputUtility.getKeyPressed(KeyCode.W)) {
			if (!InputUtility.getKeyPressed(KeyCode.S)) {			
				ney = getY() - walkspeed;
				setCountwalk(++countwalk);
				this.standstill = false;
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.S)) {
			if (!InputUtility.getKeyPressed(KeyCode.W)) {		
				ney = getY() + walkspeed;
				setCountwalk(++countwalk);
				this.standstill = false;
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.D)) {
			if (!InputUtility.getKeyPressed(KeyCode.A)) {
				nex = getX() + walkspeed;
				this.standstill = false;
				setCountwalk(++countwalk);
				this.turnright = true;		
			}
		}
		if (InputUtility.getKeyPressed(KeyCode.A)) {
			if (!InputUtility.getKeyPressed(KeyCode.D)) {				
				nex = getX() - walkspeed;
				this.standstill = false;
				setCountwalk(++countwalk);
				this.turnright = false;
			}
		}
	}


	public void UpdateVisible() {
		if (flashing) {
			if (flashCounter == 0) {
				this.visible = true;
				flashing = false;
			} else {
				if (flashDurationCounter > 0) {
					this.visible = flashCounter <= 6;
					flashDurationCounter--;
				} else {
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		}	
	}


	public void setHp(int hp,boolean CannotWait) {
		if (!flashing || CannotWait) {		
			SoundLogic.getInstance().getHitSound().play();
			flashing = true;
			flashCounter = 8;
			flashDurationCounter = 8;
			super.setHp(hp);
		}
	}
	
	public double getWalkspeed() {
		return this.walkspeed;
	}
	
	public void setWalkspeed(double walkspeed) {
		this.walkspeed = walkspeed;
	}


	public boolean isTurnright() {
		return turnright;
	}


	public void setTurnright(boolean turnright) {
		this.turnright = turnright;
	}


	public boolean isStandstill() {
		return standstill;
	}


	public void setStandstill(boolean standstill) {
		this.standstill = standstill;
	}


	public int getCountwalk() {
		return countwalk;
	}


	public void setCountwalk(int countwalk) {
		this.countwalk = countwalk%60;
	}


	public boolean isShoot() {
		return shoot;
	}


	public void setShoot(boolean shoot) {
		this.shoot = shoot;
	}


	public int getAttackspeed() {
		return attackspeed;
	}


	public void setAttackspeed(int attckspeed) {
		this.attackspeed = attckspeed;
	}
	
	public boolean isEmpty() {
		return GameLogic.Monsters.size() <= 0;
	}

	public int getMaxhp() {
		return maxhp;
	}
	
	
	public void setMaxhp(int maxhp) {
		super.setHp(maxhp);
		this.maxhp = maxhp;
	}


	public String getArrowName() {
		return ArrowName;
	}


	public void setArrowName(String arrowName) {
		ArrowName = arrowName;
	}


	public boolean isPercing() {
		return Percing;
	}


	public void setPercing(boolean percing) {
		this.Percing = percing;
	}

}
