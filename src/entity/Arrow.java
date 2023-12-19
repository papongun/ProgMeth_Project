package entity;

import logic.GameLogic;
import logic.SoundLogic;
import sharedObject.RenderableHolder;

public class Arrow extends FlyingObject{
	
	private boolean Percing = false;
	
	
	public Arrow(String name,double angle,double x, double y) {
		super(angle,x,y);
		this.length = 40;
		this.speed = 7;
		setName(name);
		switch(name) {
		case "Arrow" :
			Image = RenderableHolder.ArrowBasicSprite;
			break;
		case "Heavy Arrow":
			Image = RenderableHolder.ArrowHeavySprite;
			break;
		case "Light Arrow":
			Image = RenderableHolder.ArrowQuickSprite;
			break;
		case "Percing Arrow" :
			Image = RenderableHolder.ArrowPercingSprite;
			break;
		}
	}
	
	@Override
	public void update() {
		this.x += speed * Math.cos(angle);
		this.y += speed * Math.sin(angle);
		UpdateCollideMonster();
		UpdateCollideRect();
		if (CheckCollide()) {
			setDead();
		}
		
	}

	public void UpdateCollideMonster() {
		for (Monster m : GameLogic.getMonsters()) {
			if (!m.isDestroyed() && m.isVisible() && collide(m)) {
				m.setHp(m.getHp()-attackdamage);
				SoundLogic.getInstance().getMonsterHitSound().play();
				if (!isPercing()) {					
					setDead();
					return;
				}
			}
		}	
	}

	public int getAttackdamage() {
		return attackdamage;
	}

	public void setAttackdamage(int attackdamage) {
		this.attackdamage = attackdamage;
	}

	public boolean isPercing() {
		return Percing;
	}

	public void setPercing(boolean percing) {
		Percing = percing;
	}
}
