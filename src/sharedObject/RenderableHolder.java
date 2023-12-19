package sharedObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.image.Image;

public class RenderableHolder {
	private static final RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;
	public static Image herowalkSprite;
	public static Image herostillSprite;
	public static Image tackleSprite;
	public static Image ArrowBasicSprite;
	public static Image ArrowHeavySprite;
	public static Image ArrowQuickSprite;
	public static Image ArrowPercingSprite;
	public static Image FireballSprite;
	public static Image HpbarSprite;
	public static Image SignSprite;
	public static Image Map11;
	public static Image Map12;
	public static Image Map21;
	public static Image Map22;
	public static Image Map31;
	public static Image Map32;
	public static Image Map41;
	public static Image Map42;
	public static Image[] ShootSprites = new Image[4];
	public static Image[] SpeedShootSprites = new Image[4];
	public static Image[] HeavyTackleSprites = new Image[4];
	public static Image[] ExplodeSprites = new Image[3];
	public static Image[] DeadSprites = new Image[3];

	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}
	
	public void reset() {
		entities = new ArrayList<IRenderable>();
	}

	private static void loadResource() {
		//load image source
		herowalkSprite = new Image(ClassLoader.getSystemResource("hero-walk-side.png").toString());
		System.out.println("Load Sprite and Map Resource 1/36");
		herostillSprite = new Image(ClassLoader.getSystemResource("hero-attack-side-weapon.png").toString());
		System.out.println("Load Sprite and Map Resource 2/36");
		tackleSprite = new Image(ClassLoader.getSystemResource("bat.png").toString());
		System.out.println("Load Sprite and Map Resource 3/36");
		ArrowBasicSprite = new Image(ClassLoader.getSystemResource("Arrow/Basic Arrow1.png").toString());
		System.out.println("Load Sprite and Map Resource 4/36");
		ArrowHeavySprite = new Image(ClassLoader.getSystemResource("Arrow/Heavy Arrow1.png").toString());
		System.out.println("Load Sprite and Map Resource 5/36");
		ArrowQuickSprite = new Image(ClassLoader.getSystemResource("Arrow/Quick Arrow1.png").toString());
		System.out.println("Load Sprite and Map Resource 6/36");
		ArrowPercingSprite = new Image(ClassLoader.getSystemResource("Arrow/Percing Arrow1.png").toString());
		System.out.println("Load Sprite and Map Resource 7/36");
		FireballSprite = new Image(ClassLoader.getSystemResource("fireball.png").toString());
		System.out.println("Load Sprite and Map Resource 8/36");
		HpbarSprite = new Image(ClassLoader.getSystemResource("health_ui.png").toString());
		System.out.println("Load Sprite and Map Resource 9/36");
		SignSprite = new Image(ClassLoader.getSystemResource("sign.png").toString());
		System.out.println("Load Sprite and Map Resource 10/36");
		ShootSprites[0] = new Image(ClassLoader.getSystemResource("ShootM/Shooter/Shoot00.png").toString());
		System.out.println("Load Sprite and Map Resource 11/36");
		ShootSprites[1] = new Image(ClassLoader.getSystemResource("ShootM/Shooter/Shoot01.png").toString());
		System.out.println("Load Sprite and Map Resource 12/36");
		ShootSprites[2] = new Image(ClassLoader.getSystemResource("ShootM/Shooter/Shoot02.png").toString());
		System.out.println("Load Sprite and Map Resource 13/36");
		ShootSprites[3] = new Image(ClassLoader.getSystemResource("ShootM/Shooter/Shoot03.png").toString());
		System.out.println("Load Sprite and Map Resource 14/36");
		SpeedShootSprites[0] = new Image(ClassLoader.getSystemResource("ShootM/SpeedShooter/SpShoot00.png").toString());
		System.out.println("Load Sprite and Map Resource 15/36");
		SpeedShootSprites[1] = new Image(ClassLoader.getSystemResource("ShootM/SpeedShooter/SpShoot01.png").toString());
		System.out.println("Load Sprite and Map Resource 16/36");
		SpeedShootSprites[2] = new Image(ClassLoader.getSystemResource("ShootM/SpeedShooter/SpShoot02.png").toString());
		System.out.println("Load Sprite and Map Resource 17/36");
		SpeedShootSprites[3] = new Image(ClassLoader.getSystemResource("ShootM/SpeedShooter/SpShoot03.png").toString());
		System.out.println("Load Sprite and Map Resource 18/36");
		ExplodeSprites[0] = new Image(ClassLoader.getSystemResource("ExplodeM/explode_0.png").toString());	
		System.out.println("Load Sprite and Map Resource 19/36");
		ExplodeSprites[1] = new Image(ClassLoader.getSystemResource("ExplodeM/explode_1.png").toString());
		System.out.println("Load Sprite and Map Resource 20/36");
		ExplodeSprites[2] = new Image(ClassLoader.getSystemResource("ExplodeM/explode_2.png").toString());
		System.out.println("Load Sprite and Map Resource 21/36");
		HeavyTackleSprites[0] = new Image(ClassLoader.getSystemResource("HeavyM/Snake00.png").toString());
		System.out.println("Load Sprite and Map Resource 22/36");
		HeavyTackleSprites[1] = new Image(ClassLoader.getSystemResource("HeavyM/Snake01.png").toString());
		System.out.println("Load Sprite and Map Resource 23/36");
		HeavyTackleSprites[2] = new Image(ClassLoader.getSystemResource("HeavyM/Snake02.png").toString());
		System.out.println("Load Sprite and Map Resource 24/36");
		HeavyTackleSprites[3] = new Image(ClassLoader.getSystemResource("HeavyM/Snake03.png").toString());
		System.out.println("Load Sprite and Map Resource 25/36");
		DeadSprites[0] = new Image(ClassLoader.getSystemResource("Death/death00.png").toString());
		System.out.println("Load Sprite and Map Resource 26/36");
		DeadSprites[1] = new Image(ClassLoader.getSystemResource("Death/death01.png").toString());
		System.out.println("Load Sprite and Map Resource 27/36");
		DeadSprites[2] = new Image(ClassLoader.getSystemResource("Death/death02.png").toString());
		System.out.println("Load Sprite and Map Resource 28/36");
		Map11 = new Image(ClassLoader.getSystemResource("Stage/stage1-1.png").toString());
		System.out.println("Load Sprite and Map Resource 29/36");
		Map12 = new Image(ClassLoader.getSystemResource("Stage/stage1-2.png").toString());
		System.out.println("Load Sprite and Map Resource 30/36");
		Map21 = new Image(ClassLoader.getSystemResource("Stage/stage2-1.png").toString());
		System.out.println("Load Sprite and Map Resource 31/36");
		Map22 = new Image(ClassLoader.getSystemResource("Stage/stage2-2.png").toString());
		System.out.println("Load Sprite and Map Resource 32/36");
		Map31 = new Image(ClassLoader.getSystemResource("Stage/stage3-1.png").toString());
		System.out.println("Load Sprite and Map Resource 33/36");
		Map32 = new Image(ClassLoader.getSystemResource("Stage/stage3-2.png").toString());
		System.out.println("Load Sprite and Map Resource 34/36");
		Map41 = new Image(ClassLoader.getSystemResource("Stage/stage4-1.png").toString());
		System.out.println("Load Sprite and Map Resource 35/36");
		Map42 = new Image(ClassLoader.getSystemResource("Stage/stage4-2.png").toString());
		System.out.println("Load Sprite and Map Resource 36/36");
		System.out.println("Load Sprite and Map Resource Completed");
	}
	

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public void update() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed() && !entities.get(i).isVisible())
				entities.remove(i);
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}
}
