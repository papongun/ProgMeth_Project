package entity;

public abstract class Monster extends Character{
	protected Hero h1;
	
	public void setHero(Hero h1) {
		this.h1 = h1;
	}
	
	public void UpdateAim() {
		aimangle = aim(this,this.h1);
		if (Math.cos(aimangle) < 0) turnright= false;
		else turnright= true;
	}
	
}
