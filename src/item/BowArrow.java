package item;


public class BowArrow extends BaseItem{

	private int attackdamage;
    private int attackspeed;
    private double bonuswalkspeed;
    private boolean percing;

    public BowArrow(String name, String iURL, Rarity rarity) {
    	super(name, iURL, rarity);
    	setAttackdamage(10);
    	setAttackspeed(2);
    	setBonuswalkspeed(0);
    	percing = false;
    }
    
    public BowArrow(String name, String iURL, Rarity rarity,int attackdamage,int attackspeed,double bonuswalkspeed) {
    	this(name,iURL,rarity);
    	setAttackdamage(attackdamage);
    	setAttackspeed(attackspeed);
    	setBonuswalkspeed(bonuswalkspeed);
    }
    
    public BowArrow(String name, String iURL, Rarity rarity,int attackdamage,int attackspeed,int bonuswalkspeed,boolean percing) {
    	this(name,iURL,rarity,attackdamage,attackspeed,bonuswalkspeed);
    	this.percing = percing;
    }

    public int getAttackdamage() {
        return this.attackdamage;
    }

    public void setAttackdamage(int attackdamage) {
        this.attackdamage = attackdamage;
    }

    public int getAttackspeed() {
        return this.attackspeed;
    }

    public void setAttackspeed(int attackspeed2) {
        this.attackspeed = attackspeed2;
    }

    public double getBonuswalkspeed() {
        return this.bonuswalkspeed;
    }

    public void setBonuswalkspeed(double bonuswalkspeed2) {
        this.bonuswalkspeed = bonuswalkspeed2;
    }

    public boolean isPercing() {
        return this.percing;
    }


    public void setPercing(boolean percing) {
        this.percing = percing;
    }
    

}
