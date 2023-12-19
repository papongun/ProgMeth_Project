package item;

public abstract class BaseItem{
	private String name;
	private boolean isUnlocked;
    private Rarity rarity;
    private String iURL;

    public BaseItem(String name, String iURL,Rarity rarity) {
        this.setName(name);
        this.iURL = iURL;
        this.rarity = rarity;
        this.isUnlocked = false;
    }

    public String getName() {
    	return this.name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public Rarity getRarity() {
        return this.rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public String getIURL() {
        return this.iURL;
    }

    public void setIURL(String iURL) {
        this.iURL = iURL;
    }
    
    public boolean isUnlocked() {
		return isUnlocked;
	}

	public void setUnlocked(boolean isUnlocked) {
		this.isUnlocked = isUnlocked;
	}


}
