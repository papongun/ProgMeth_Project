package item;

public class Ring extends BaseItem{
	
	private int bonushp;
	private int attackdamage;
    private int attackspeed;
    private double bonuswalkspeed;
    
    public Ring(String name, String iURL, Rarity rarity) {
    	super(name, iURL, rarity);
    	setAttackdamage(0);
    	setAttackspeed(0);
    	setBonuswalkspeed(0);
    }
    
    public Ring(String name, String iURL, Rarity rarity,int bonushp,int attackdamage,int attackspeed,double bonuswalkspeed) {
    	this(name, iURL, rarity);
    	setBonushp(bonushp);
    	setAttackdamage(attackdamage);
    	setAttackspeed(attackspeed);
    	setBonuswalkspeed(bonuswalkspeed);
    }

	public int getBonushp() {
		return bonushp;
	}

	public void setBonushp(int bonushp) {
		this.bonushp = bonushp;
	}

	public int getAttackdamage() {
		return attackdamage;
	}

	public void setAttackdamage(int attackdamage) {
		this.attackdamage = attackdamage;
	}

	public int getAttackspeed() {
		return attackspeed;
	}

	public void setAttackspeed(int attackspeed) {
		this.attackspeed = attackspeed;
	}

	public double getBonuswalkspeed() {
		return bonuswalkspeed;
	}

	public void setBonuswalkspeed(double bonuswalkspeed) {
		this.bonuswalkspeed = bonuswalkspeed;
	}
}