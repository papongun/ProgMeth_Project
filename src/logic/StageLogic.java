package logic;

import java.util.ArrayList;

import block.Rectangle;
import block.WaterRectangle;
import entity.ExplosiveTackleMonster;
import entity.HeavyTackleMonster;
import entity.Monster;
import entity.ShootingMonster;
import entity.SpeedShootingMonster;
import entity.TackleMonster;

public class StageLogic {
	private static StageLogic instance = new StageLogic();
	
	private int stage,substage;
	private ArrayList<Boolean> stageUnlocked;
	private final Monster[][] MonstersArray = {
			{	//1-1
				new TackleMonster(500,100),new TackleMonster(500,200),new TackleMonster(800,300),new ShootingMonster(800, 320)},
			{	//1-2
				new ExplosiveTackleMonster(300, 320), new ShootingMonster(700, 320), new ShootingMonster(700, 100), new ShootingMonster(700, 540)},
			{	//2-1
				new ExplosiveTackleMonster(10, 10), new ExplosiveTackleMonster(10, 630), new SpeedShootingMonster(200, 40), new SpeedShootingMonster(440, 640), new ShootingMonster(600, 30)},
			{	//2-2
				new SpeedShootingMonster(280, 120), new ShootingMonster(700, 100), new SpeedShootingMonster(700, 550), new ShootingMonster(220, 570)},
			{	//3-1
				new TackleMonster(20,20), new ShootingMonster(230,70), new ShootingMonster(500, 320), new SpeedShootingMonster(700, 30), new TackleMonster(480, 600), new TackleMonster(700, 600), new SpeedShootingMonster(510, 600)},
			{	//3-2
				new ShootingMonster(900, 320), new SpeedShootingMonster(900, 220), new SpeedShootingMonster(900, 420), new ShootingMonster(620, 370), new SpeedShootingMonster(260,160), new SpeedShootingMonster(320,440)},
			{	//4-1
				new ExplosiveTackleMonster(544, 320),new ExplosiveTackleMonster(704, 352), new SpeedShootingMonster(192, 544), new SpeedShootingMonster(192, 64), new HeavyTackleMonster(488,224), new HeavyTackleMonster(488,488), new HeavyTackleMonster(480,480), new ShootingMonster(736, 96), new SpeedShootingMonster(352, 160), new ShootingMonster(760, 280), new ShootingMonster(760, 416)},
			{	//4-2
				new ExplosiveTackleMonster(416, 320), new ExplosiveTackleMonster(480, 320), new ExplosiveTackleMonster(544, 320), new SpeedShootingMonster(608, 360), new SpeedShootingMonster(608, 410), new SpeedShootingMonster(608, 460), new SpeedShootingMonster(352, 192), new TackleMonster(64,64), new TackleMonster(160,64), new HeavyTackleMonster(480,576), new HeavyTackleMonster(640,488), new ShootingMonster(768, 288)}
			};
	private final Rectangle[][] RectsArray = {
		{	
			//1-1
			new Rectangle(416, 128, 96, 96),
			new Rectangle(608, 448, 96, 96),
			new Rectangle(256, 480, 96, 96)},
		{
			//1-2
			new Rectangle(480, 224, 96, 32),
			new Rectangle(448, 256, 160, 96),
			new Rectangle(480, 352, 96, 64)	},
		{
			//2-1
			new Rectangle(96, 192, 128, 96),
			new Rectangle(128, 288, 64, 32),
			new Rectangle(320, 256, 128, 96),
			new Rectangle(352, 352, 64, 32),
			new Rectangle(448, 480, 128, 96),
			new Rectangle(480, 576, 64, 32),
			new Rectangle(608, 608, 64, 32),
			new Rectangle(672, 0, 128, 96),
			new Rectangle(64, 32, 64, 32)},
		{	
			//2-2
			new Rectangle(32, 0, 128, 96),
			new Rectangle(64, 96, 64, 32),
			new Rectangle(64, 480, 128, 96),
			new Rectangle(96, 576, 64, 32),
			new Rectangle(800, 0, 128, 96),
			new Rectangle(832, 96, 64, 32),
			new Rectangle(800, 480, 128, 96),
			new Rectangle(832, 576, 64, 32),
			new Rectangle(800, 160, 32, 64),
			new Rectangle(800, 352, 32, 64)},
		{	
			//3-1
			new Rectangle(128, 32, 64, 96),
			new Rectangle(0, 448, 416, 192),
			new Rectangle(544, 256, 224, 96),
			new Rectangle(736, 0, 224, 96),
			new Rectangle(736, 480, 224, 160)},
		{
			//3-2
			new Rectangle(0, 0, 960, 64),
			new Rectangle(160, 128, 64, 64),
			new Rectangle(224, 416, 64, 64),
			new Rectangle(448, 256, 64, 64),
			new Rectangle(736, 96, 64, 96),
			new Rectangle(672, 320, 160, 96),
			new Rectangle(0, 576, 960, 64)},
		{
			//4-1 
			
			new Rectangle(352, 320, 32, 96),
			new Rectangle(544, 256, 96, 64),
			new Rectangle(544, 352, 96, 64),
			new WaterRectangle(288, 0, 128, 256), 
			new WaterRectangle(640, 32, 192, 192)},
		{
			//4-2
			new Rectangle(128, 224, 64, 64),
			new Rectangle(352, 512, 32, 64),
			new Rectangle(384, 64, 64, 32),
			new Rectangle(736, 416, 224, 64),
			new Rectangle(896, 64, 32, 64),
			new Rectangle(704, 480, 256, 160),
			new Rectangle(544, 0, 288, 160),
			new Rectangle(640, 128, 96, 64),} 
	};
	
//	private final WaterRectangle[][] WaterArray = {
//		{},
//		{},
//		{},
//		{},
//		{},
//		{},
//		{
//			new WaterRectangle(288, 0, 128, 256), 
//			new WaterRectangle(640, 32, 192, 192)
//		},
//		{
//			
//		}
//	};

	private Monster[] StageMonster;
	
	public static StageLogic getInstance() {
		return instance;
	}	
	
	public StageLogic() {
		this.stage = 0;
		this.substage = 0;
		this.stageUnlocked = new ArrayList<Boolean>();
		this.stageUnlocked.add(true);
		this.stageUnlocked.add(false);
		this.stageUnlocked.add(false);
		this.stageUnlocked.add(false);

	}
	
	public ArrayList<Boolean> getStageUnlocked() {
		return stageUnlocked;
	}

	public void setStageUnlocked(ArrayList<Boolean> stageUnlocked) {
		this.stageUnlocked = stageUnlocked;
	}

	public StageLogic(int stage ,int substage) {
		this.stage = stage;
		this.substage = substage;
	}
	
	public Monster[] getStageMonster() {
		StageMonster = copy(MonstersArray[stage*2+substage]);
		return StageMonster;
	}
	
	public Rectangle[] getRects() {
		return RectsArray[stage*2+substage];
	}
	
//	public WaterRectangle[] getWaters() {
//		return WaterArray[stage*2+substage];
//	}
	
	private Monster[] copy(Monster[] marray) {
		Monster[] monster  = new Monster[marray.length];
		for (int i = 0; i < marray.length; i++) {
			
			if (marray[i] instanceof ExplosiveTackleMonster) {
				monster[i] = new ExplosiveTackleMonster(marray[i].getX(),marray[i].getY());
			} else if (marray[i] instanceof HeavyTackleMonster) {
				monster[i] = new HeavyTackleMonster(marray[i].getX(),marray[i].getY());
			} else if (marray[i] instanceof TackleMonster) {
				monster[i] = new TackleMonster(marray[i].getX(),marray[i].getY());
			}else if (marray[i] instanceof SpeedShootingMonster) {
				monster[i] = new SpeedShootingMonster(marray[i].getX(),marray[i].getY());
			} else if (marray[i] instanceof ShootingMonster) {
				monster[i] = new ShootingMonster(marray[i].getX(),marray[i].getY());
			}
		}
		return monster;
	}
	
	public void setStage(int s) {
		this.stage = s%4;
		switch (this.stage) {
		case 0:
			SoundLogic.getInstance().setSceneMode(SceneMode.S1);
			break;
		case 1:
			SoundLogic.getInstance().setSceneMode(SceneMode.S2);
			break;
		case 2:
			SoundLogic.getInstance().setSceneMode(SceneMode.S3);
			break;
		case 3:
			SoundLogic.getInstance().setSceneMode(SceneMode.S4);
			break;
		}
		
	}
		
	public int getStage() {
		return stage;
	}

	public void setSubstage(int s) {
		this.substage = s%2;
	}
	
	public int getSubstage() {
		return substage;
	}
	
	public int getStageTotal() {
		return stage*2+substage;
	}
	
}
