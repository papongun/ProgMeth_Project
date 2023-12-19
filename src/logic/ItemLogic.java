package logic;

import java.text.DecimalFormat;
import java.util.ArrayList;

import item.Armor;
import item.BowArrow;
import item.BaseItem;
import item.Rarity;
import item.Ring;

public class ItemLogic {

	// instance
	private static final ItemLogic instance = new ItemLogic();

	// field
	private ArrayList<BaseItem> allItems;
	private ArrayList<BowArrow> allArrows;
	private ArrayList<Armor> allArmors;
	private ArrayList<Ring> allRings;

	private ArrayList<BaseItem> lockedCommon;
	private ArrayList<BaseItem> lockedRare;
	private ArrayList<BaseItem> lockedEpic;
	private ArrayList<BaseItem> lockedLegendary;

	private BowArrow equippingArrow;
	private Armor equippingArmor;
	private Ring equippingRing;

	private int attackDamage;
	private int attackSpeed;
	private int maxHealth;
	private double walkSpeed;
	private String ArrowName;
	private boolean Percing;

	// constructor
	public ItemLogic() {
		allArrows = new ArrayList<BowArrow>();
		allArrows.add(
				new BowArrow("Arrow", ClassLoader.getSystemResource("Arrow/Basic Arrow.png").toString(), Rarity.Common));
		allArrows.add(new BowArrow("Heavy Arrow", ClassLoader.getSystemResource("Arrow/Heavy Arrow.png").toString(),
				Rarity.Rare, 20, 3, -0.15));
		allArrows.add(new BowArrow("Light Arrow", ClassLoader.getSystemResource("Arrow/Quick Arrow.png").toString(),
				Rarity.Epic, 8, 1, 0.15));
		allArrows.add(new BowArrow("Percing Arrow", ClassLoader.getSystemResource("Arrow/Poison Arrow.png").toString(),
				Rarity.Legendary, 15, 2, 0,true));

		allArrows.get(0).setUnlocked(true);
		setEquippingArrow(allArrows.get(0));

		allArmors = new ArrayList<Armor>();
		allArmors.add(new Armor("Iron Body", ClassLoader.getSystemResource("Armor/ironbody.png").toString(),
				Rarity.Common, 30, -0.15));
		allArmors.add(new Armor("Light Armor", ClassLoader.getSystemResource("Armor/lightarmor.png").toString(),
				Rarity.Rare, 25, 0.10));
		allArmors.add(new Armor("Heavy Armor", ClassLoader.getSystemResource("Armor/heavyarmor.png").toString(),
				Rarity.Epic, 40, -0.10));
		allArmors.add(new Armor("Feather Suit", ClassLoader.getSystemResource("Armor/feathersuit.png").toString(),
				Rarity.Legendary, 40, 0.20));
		setEquippingArmor(null);

		allRings = new ArrayList<Ring>();
		allRings.add(
				new Ring("Roar", ClassLoader.getSystemResource("Ring/roar.png").toString(), Rarity.Common, 0, 2, 0, 0));
		allRings.add(new Ring("Colossus", ClassLoader.getSystemResource("Ring/colossus.png").toString(), Rarity.Rare,
				15, 0, 0, 0));
		allRings.add(new Ring("Assassinate", ClassLoader.getSystemResource("Ring/assassinate.png").toString(),
				Rarity.Epic, 0, 5, 0, 0.1));
		allRings.add(new Ring("Wind Walk", ClassLoader.getSystemResource("Ring/windwalk.png").toString(),
				Rarity.Legendary, 10, 5, 0, 0.3));
		setEquippingRing(null);

		lockedCommon = new ArrayList<BaseItem>();
		lockedRare = new ArrayList<BaseItem>();
		lockedEpic = new ArrayList<BaseItem>();
		lockedLegendary = new ArrayList<BaseItem>();
		allItems = new ArrayList<BaseItem>();
		allItems.addAll(allArmors);
		allItems.addAll(allArrows);
		allItems.addAll(allRings);
		for (int i = 0; i < allItems.size(); i++) {

			if ((allItems.get(i).getRarity() == Rarity.Common) && !allItems.get(i).isUnlocked()) {
				lockedCommon.add(allItems.get(i));
			}
			if ((allItems.get(i).getRarity() == Rarity.Rare) && !allItems.get(i).isUnlocked()) {
				lockedRare.add(allItems.get(i));
			}
			if ((allItems.get(i).getRarity() == Rarity.Epic) && !allItems.get(i).isUnlocked()) {
				lockedEpic.add(allItems.get(i));
			}
			if ((allItems.get(i).getRarity() == Rarity.Legendary) && !allItems.get(i).isUnlocked()) {
				lockedLegendary.add(allItems.get(i));
			}

		}
	}

	// get instance
	public static ItemLogic getInstance() {
		return instance;
	}

	// method
	public void updateHeroStat() {
		int totalAtt = ItemLogic.getInstance().getEquippingArrow().getAttackdamage();
		String arrowname = ItemLogic.getInstance().getEquippingArrow().getName();
		boolean percing = ItemLogic.getInstance().getEquippingArrow().isPercing();
		int totalAttSpeed = ItemLogic.getInstance().getEquippingArrow().getAttackspeed();
		int totalHp = 50;
		double totalWalkSpeedBonus = 0;

		if (ItemLogic.getInstance().getEquippingRing() != null) {
			totalWalkSpeedBonus += ItemLogic.getInstance().getEquippingRing().getBonuswalkspeed();
			totalHp += ItemLogic.getInstance().getEquippingRing().getBonushp();
			totalAtt += ItemLogic.getInstance().getEquippingRing().getAttackdamage();
		}

		if (ItemLogic.getInstance().getEquippingArmor() != null) {
			totalWalkSpeedBonus += ItemLogic.getInstance().getEquippingArmor().getBonuswalkspeed();
			totalHp += ItemLogic.getInstance().getEquippingArmor().getBonushp();
		}
		DecimalFormat df = new DecimalFormat("0.00");      
		ItemLogic.getInstance().setArrowName(arrowname);
		ItemLogic.getInstance().setPercing(percing);
		ItemLogic.getInstance().setAttackSpeed(totalAttSpeed);
		ItemLogic.getInstance().setMaxHealth(totalHp);
		ItemLogic.getInstance().setAttackDamage(totalAtt);
		ItemLogic.getInstance().setWalkSpeed(Double.valueOf(df.format(3 * (1 + totalWalkSpeedBonus))));

	}

	public BaseItem randomUnlock(Rarity rarity) {
		switch (rarity) {
			case Common:
				if (lockedCommon.size() > 0) {
					int index = getRandomNumber(0, lockedCommon.size() - 1);
					lockedCommon.get(index).setUnlocked(true);
					BaseItem unlock = lockedCommon.get(index);
					System.out.println("Item Unlock : " + unlock.getName());
					lockedCommon.remove(index);
					return unlock;
				} else {
					System.out.println("Unlock Nothing");
					return null;
				}
			case Rare:
				if (lockedRare.size() > 0) {
					int index = getRandomNumber(0, lockedRare.size() - 1);
					lockedRare.get(index).setUnlocked(true);
					BaseItem unlock = lockedRare.get(index);
					System.out.println("Item Unlock : " + unlock.getName());
					lockedRare.remove(index);
					return unlock;
				} else {
					System.out.println("Unlock Nothing");
					return null;
				}
			case Epic:
				if (lockedEpic.size() > 0) {
					int index = getRandomNumber(0, lockedEpic.size() - 1);
					lockedEpic.get(index).setUnlocked(true);
					BaseItem unlock = lockedEpic.get(index);
					System.out.println("Item Unlock : " + unlock.getName());
					lockedEpic.remove(index);
					return unlock;
				} else {
					System.out.println("Unlock Nothing");
					return null;
				}
			case Legendary:
				if (lockedLegendary.size() > 0) {
					int index = getRandomNumber(0, lockedLegendary.size() - 1);
					lockedLegendary.get(index).setUnlocked(true);
					BaseItem unlock = lockedLegendary.get(index);
					System.out.println("Item Unlock : " + unlock.getName());
					lockedLegendary.remove(index);
					return unlock;
				} else {
					System.out.println("Unlock Nothing");
					return null;
				}
			default:
				return null;

		}
	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

	public Rarity randomRarityToUnlock(int stage) {
		double chance = Math.random();
		switch (stage) {
			case 1:
				if (chance > 0.9) {
					return Rarity.Rare;
				} else {
					return Rarity.Common;
				}
			case 2:
				if (chance > 0.9) {
					return Rarity.Epic;
				} else if (chance > 0.7) {
					return Rarity.Rare;
				} else {
					return Rarity.Common;
				}
			case 3:
				if (chance > 0.95) {
					return Rarity.Legendary;
				} else if (chance > 0.8) {
					return Rarity.Epic;
				} else if (chance > 0.5) {
					return Rarity.Rare;
				} else {
					return Rarity.Common;
				}
			case 4:
				if (chance > 0.8) {
					return Rarity.Legendary;
				}
				if (chance > 0.5) {
					return Rarity.Epic;
				} else {
					return Rarity.Rare;
				}
			default:
				return Rarity.Common;
		}
	}

	// getter setter

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(double walkSpeed) {
		this.walkSpeed = walkSpeed;

	}

	public BowArrow getEquippingArrow() {
		return equippingArrow;
	}

	public void setEquippingArrow(BowArrow equipingArrow) {
		this.equippingArrow = equipingArrow;
	}

	public Armor getEquippingArmor() {
		return equippingArmor;
	}

	public void setEquippingArmor(Armor equipingArmor) {
		this.equippingArmor = equipingArmor;
	}

	public Ring getEquippingRing() {
		return equippingRing;
	}

	public void setEquippingRing(Ring equipingRing) {
		this.equippingRing = equipingRing;
	}

	public ArrayList<BowArrow> getAllArrows() {
		return allArrows;
	}

	public void setAllArrows(ArrayList<BowArrow> allArrows) {
		this.allArrows = allArrows;
	}

	public ArrayList<Armor> getAllArmors() {
		return allArmors;
	}

	public void setAllArmors(ArrayList<Armor> allArmors) {
		this.allArmors = allArmors;
	}

	public ArrayList<Ring> getAllRings() {
		return allRings;
	}

	public void setAllRings(ArrayList<Ring> allRings) {
		this.allRings = allRings;
	}

	public ArrayList<BaseItem> getAllItems() {
		return allItems;
	}

	public void setAllItems(ArrayList<BaseItem> allItems) {
		this.allItems = allItems;
	}

	public ArrayList<BaseItem> getLockedCommon() {
		return lockedCommon;
	}

	public void setLockedCommon(ArrayList<BaseItem> lockedCommon) {
		this.lockedCommon = lockedCommon;
	}

	public ArrayList<BaseItem> getLockedRare() {
		return lockedRare;
	}

	public void setLockedRare(ArrayList<BaseItem> lockedRare) {
		this.lockedRare = lockedRare;
	}

	public ArrayList<BaseItem> getLockedEpic() {
		return lockedEpic;
	}

	public void setLockedEpic(ArrayList<BaseItem> lockedEpic) {
		this.lockedEpic = lockedEpic;
	}

	public ArrayList<BaseItem> getLockedLegendary() {
		return lockedLegendary;
	}

	public void setLockedLegendary(ArrayList<BaseItem> lockedLegendary) {
		this.lockedLegendary = lockedLegendary;
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
		Percing = percing;
	}

}
