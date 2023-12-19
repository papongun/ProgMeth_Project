package item;

public class Armor extends BaseItem{
	private int bonushp;
	private double bonuswalkspeed;
	
	public Armor(String name, String iURL, Rarity rarity) {
		super(name, iURL, rarity);
		setBonushp(0);
		setBonuswalkspeed(0);
	}
	
	public Armor(String name, String iURL, Rarity rarity,int bonushp,double bonuswalkspeed) {
		this(name, iURL, rarity);
		setBonushp(bonushp);
		setBonuswalkspeed(bonuswalkspeed);
	}

	public int getBonushp() {
		return bonushp;
	}

	public void setBonushp(int bonushp) {
		this.bonushp = bonushp;
	}

	public double getBonuswalkspeed() {
		return bonuswalkspeed;
	}

	public void setBonuswalkspeed(double bonuswalkspeed) {
		this.bonuswalkspeed = bonuswalkspeed;
	}


}
