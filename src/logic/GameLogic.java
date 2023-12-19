package logic;

import java.util.ArrayList;
import java.util.List;

import entity.Arrow;
import entity.Entity;
import entity.Field;
import entity.Fireball;
import entity.Hero;
import entity.Monster;
import entity.ShootingMonster;
import input.InputUtility;
import item.BaseItem;
import item.Rarity;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import scene.MainMenu;
import sharedObject.RenderableHolder;

public class GameLogic {
	public static ArrayList<Monster> Monsters;
	
	private ArrayList<Entity> gameCharacter;
	private Hero hero;
	private boolean SubGameEnd;
	private boolean StageGameEnd;
	

	public static List<Monster> getMonsters() {
		return Monsters;
	}
	
	public GameLogic(){
		ResetContainer(StageLogic.getInstance().getStageTotal() % 2 == 0);
	}
	
	public void ResetContainer(boolean resethp) {
		InputUtility.reset();
		setSubGameEnd(false);
		setStageGameEnd(false);
		gameCharacter = new ArrayList<Entity>();
		Monsters = new ArrayList<Monster>();
		RenderableHolder.getInstance().reset();
		ItemLogic.getInstance().updateHeroStat();
		if (resethp) {
			hero = new Hero();
			hero.setMaxhp(ItemLogic.getInstance().getMaxHealth());
		}
		hero.initXY();
		AddHeroStat();
		this.gameCharacter.add(hero);
		RenderableHolder.getInstance().add(hero);
		Field field = new Field(StageLogic.getInstance().getStageTotal());
		RenderableHolder.getInstance().add(field);
		for (Monster m : StageLogic.getInstance().getStageMonster()) {
			m.setHero(hero);
			gameCharacter.add(m);
			Monsters.add(m);
			addNewObject(m);
		}

	}
	
	public void AddHeroStat() {
		hero.setArrowName(ItemLogic.getInstance().getArrowName());
		hero.setPercing(ItemLogic.getInstance().isPercing());
		hero.setAttckdamage(ItemLogic.getInstance().getAttackDamage());
		hero.setAttackspeed(ItemLogic.getInstance().getAttackSpeed());
		hero.setWalkspeed(ItemLogic.getInstance().getWalkSpeed());

	}

	public void addNewObject(Entity entity){
		RenderableHolder.getInstance().add(entity);
	}
	
	public void logicUpdate(){
		if ((GameLogic.Monsters.size() <= 0 && hero.getX() > 900 && StageLogic.getInstance().getSubstage() == 1) || hero.isDestroyed()) {
			StageGameEnd = true;
			CreateAlert();
		}
		else if (SubGameEnd) {
			AddReset();
		}
		else {
			UpdateAllCharacter();
			createarrow();
			createfireballsArray();
		}
		SubGameEnd = GameLogic.Monsters.size() <= 0 && hero.getX() > 900 && StageLogic.getInstance().getSubstage() == 0;
	}
	
	public void createfireballsArray() {
		for (int i =gameCharacter.size()-1 ; i >= 0 ; i--) {
			Entity c1 = gameCharacter.get(i);
			if (c1 instanceof ShootingMonster && !c1.isDestroyed() && ((ShootingMonster)c1).isShoot()) {
				ShootingMonster m1 = (ShootingMonster) c1;
				createfireball(m1);
				((ShootingMonster)c1).setShoot(false);
			}
		}
		
	}

	public void UpdateAllCharacter() {
		for (int i =gameCharacter.size()-1 ; i >= 0 ; i--) {
			Entity c1 = gameCharacter.get(i);
			if (c1.isDestroyed()) {
				gameCharacter.remove(i);
				if (c1 instanceof Monster) {
					Monsters.remove(c1);
				}
			} else {
				Thread t1 = new Thread(() -> {
					c1.update();
				});
				t1.start();
			}
		}	
	}

	public void CreateAlert() {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				if (hero.isDestroyed()) {
					StageBadEnd();
				} else {
					StageLogic.getInstance().getStageUnlocked().set(Math.min(StageLogic.getInstance().getStage() + 1, 3), true);
					StageEndAlert();
				}
			}
		});		
	}

	public void AddReset() {
		StageLogic.getInstance().setSubstage(StageLogic.getInstance().getSubstage()+1);
		ResetContainer(StageLogic.getInstance().getStageTotal() % 2 == 0);
		InputUtility.reset();
	}
	
	public void StageEndAlert() {
		SoundLogic.getInstance().getWinSound().play();
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		DialogPane dP = al.getDialogPane();
		al.setTitle("Congratulations");
		Rarity rarity = ItemLogic.getInstance().randomRarityToUnlock(StageLogic.getInstance().getStage() + 1);
		BaseItem unlock = ItemLogic.getInstance().randomUnlock(rarity);
		if (unlock != null) {	
			al.setContentText("Item Unlock : " + unlock.getName());
			ImageView img = new ImageView(unlock.getIURL());
			img.setFitHeight(64);
			img.setFitWidth(64);
			al.setGraphic(img);
			dP.setPrefHeight(100);
		} else {
			al.setContentText("Stage Clear\n You got " + rarity.toString() +" item!\nBut, all of them is unlocked.\n" + "So, you got no reward!");
			ImageView img = new ImageView(ClassLoader.getSystemResource("GUI/lol.png").toString());
			img.setFitHeight(64);
			img.setFitWidth(64);
			al.setGraphic(img);
			dP.setPrefHeight(250);
		}
		al.setHeaderText(null);
		ButtonType yesbtn = new ButtonType("Next Stage");
		ButtonType nobtn = new ButtonType("Exit");
		al.getButtonTypes().setAll(yesbtn,nobtn);
		
		dP.setId("root");
		dP.getStylesheets().add(ClassLoader.getSystemResource("Alert.css").toString());
		al.showAndWait();
		if (al.getResult() == yesbtn) {
			SoundLogic.getInstance().getClickSound().play();
			StageLogic.getInstance().setStage(StageLogic.getInstance().getStage()+1);
			AddReset();
		} else if (al.getResult() == nobtn) {
			SoundLogic.getInstance().getClickSound().play();
			BackMainMenu();

		}
	}
	
	public void StageBadEnd() {
		SoundLogic.getInstance().getLoseSound().play();
		Alert al = new Alert(Alert.AlertType.CONFIRMATION);
		al.setTitle("YOU DEAD?!");
		al.setHeaderText(null);
		al.setContentText("Better luck next time");
		ImageView img = new ImageView(new Image(ClassLoader.getSystemResource("DeadIcon.png").toString()));
		img.setFitHeight(64);
		img.setFitWidth(64);
		al.setGraphic(img);
		ButtonType yesbtn = new ButtonType("Restart Stage");
		ButtonType nobtn = new ButtonType("Exit");
		al.getButtonTypes().setAll(yesbtn,nobtn);
		DialogPane dP = al.getDialogPane();
		dP.setId("root");
		dP.getStylesheets().add(ClassLoader.getSystemResource("Alert.css").toString());
		al.showAndWait();
		if (al.getResult() == yesbtn) {
			SoundLogic.getInstance().getClickSound().play();
			AddReset();
			if (StageLogic.getInstance().getSubstage() == 1) AddReset();
		} else if (al.getResult() == nobtn) {
			SoundLogic.getInstance().getClickSound().play();
			BackMainMenu();

		}
	}

	public void BackMainMenu() {
		
		Scene scene = new Scene(new MainMenu());
		application.Main.getGuiStage().setScene(scene);
		application.Main.getGuiStage().show();
		SoundLogic.getInstance().setSceneMode(SceneMode.Menu);
	}

	public void createarrow() {
		if (hero.isShoot()) {
			SoundLogic.getInstance().getShootSound().play();
			Arrow a1 = new Arrow(hero.getArrowName(),hero.getAimangle(),hero.getX(),hero.getY());
			a1.setAttackdamage(hero.getAttckdamage());
			a1.setPercing(hero.isPercing());
			gameCharacter.add(a1);
			addNewObject(a1);
			a1.setTurnright(hero.isTurnright());
			hero.setShoot(false);
		}
	}
	
	public void createfireball(ShootingMonster m1) {
		SoundLogic.getInstance().getMonsterShootSound().play();
		Fireball a1 = new Fireball(m1.getAimangle(),m1.getX(),m1.getY());
		a1.setH1(hero);
		a1.setAttackdamage(m1.getAttckdamage());
		gameCharacter.add(a1);
		addNewObject(a1);
		a1.setTurnright(m1.isTurnright());
	}

	public boolean isSubGameEnd() {
		return SubGameEnd;
	}

	public void setSubGameEnd(boolean gameEnd) {
		SubGameEnd = gameEnd;
	}
	
	public boolean isStageGameEnd() {
		return StageGameEnd;
	}
	
	public void setStageGameEnd(boolean stageGameEnd) {
		StageGameEnd = stageGameEnd;
	}
}
