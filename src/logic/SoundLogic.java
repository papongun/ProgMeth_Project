package logic;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundLogic {
	
	private static SoundLogic instance = new SoundLogic();
	
	private SceneMode sceneMode;
	private static MediaPlayer menuPlayer, stage1Player, stage2Player, stage3Player, stage4Player;
	private static AudioClip clickSound, hitSound, monsterHitSound, monsterShootSound, loseSound, winSound, shootSound;
	private int bgVolume, sfxVolume;
	
	public SoundLogic() {
		bgVolume = 100;
		sfxVolume = 100;
	}
	
	static {
		loadResource();
	}
	
	private static void loadResource() {
		menuPlayer = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sound/MainMenu.mp3").toString()));
		System.out.println("Load sound resources 1/12");
		stage1Player = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sound/Stage1.mp3").toString()));
		System.out.println("Load sound resources 2/12");
		stage2Player = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sound/Stage2.mp3").toString()));
		System.out.println("Load sound resources 3/12");
		stage3Player = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sound/Stage3.mp3").toString()));
		System.out.println("Load sound resources 4/12");
		stage4Player = new MediaPlayer(new Media(ClassLoader.getSystemResource("Sound/Stage4.mp3").toString()));
		System.out.println("Load sound resources 5/12");
		
		clickSound = new AudioClip(ClassLoader.getSystemResource("sfx/click.wav").toString());
		System.out.println("Load sound resources 6/12");
		hitSound = new AudioClip(ClassLoader.getSystemResource("sfx/hit.wav").toString());
		System.out.println("Load sound resources 7/12");
		monsterHitSound = new AudioClip(ClassLoader.getSystemResource("sfx/Mhit.wav").toString());
		System.out.println("Load sound resources 8/12");
		monsterShootSound = new AudioClip(ClassLoader.getSystemResource("sfx/Mshoot.wav").toString());
		System.out.println("Load sound resources 9/12");
		winSound = new AudioClip(ClassLoader.getSystemResource("sfx/win.wav").toString());
		System.out.println("Load sound resources 10/12");
		loseSound = new AudioClip(ClassLoader.getSystemResource("sfx/lose.wav").toString());
		System.out.println("Load sound resources 11/12");
		shootSound = new AudioClip(ClassLoader.getSystemResource("sfx/shoot.mp3").toString());
		System.out.println("Load sound resources 12/12");
		System.out.println("Load sound resources completed");
	}
	
	private void updateBgSound() {
		stopAll();
		switch (SoundLogic.instance.getSceneMode()) {
		case Menu:
			menuPlayer.play();
			menuPlayer.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					menuPlayer.seek(new Duration(0));
					menuPlayer.play();
				}
				
			});
			break;
		case S1:
			stage1Player.play();
			stage1Player.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stage1Player.seek(new Duration(0));
					stage1Player.play();
				}
				
			});
			break;
		case S2:
			stage2Player.play();
			stage2Player.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stage2Player.seek(new Duration(0));
					stage2Player.play();
				}
				
			});
			break;
		case S3:
			stage3Player.play();
			stage3Player.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stage3Player.seek(new Duration(0));
					stage3Player.play();
				}
				
			});
			break;
		case S4:
			stage4Player.play();
			stage4Player.setOnEndOfMedia(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					stage4Player.seek(new Duration(0));
					stage4Player.play();
				}
				
			});
		default:
			break;
		}
	}
	
	private void stopAll() {
		menuPlayer.stop();
		stage1Player.stop();
		stage2Player.stop();
		stage3Player.stop();
		stage4Player.stop();
	}
	
	private void updateBgVolume(double vol) {
		menuPlayer.setVolume(vol);
		stage1Player.setVolume(vol);
		stage2Player.setVolume(vol);
		stage3Player.setVolume(vol);
		stage4Player.setVolume(vol);
	}
	
	private void updateSfxVolume(double vol) {
		clickSound.setVolume(vol);
		hitSound.setVolume(vol);
		monsterHitSound.setVolume(vol);
		monsterShootSound.setVolume(vol);
		shootSound.setVolume(vol);
		loseSound.setVolume(vol);
		winSound.setVolume(vol);
	}
	
	public SceneMode getSceneMode() {
		return sceneMode;
	}

	public void setSceneMode(SceneMode sceneMode) {
		this.sceneMode = sceneMode;
		updateBgSound();
	}

	public static SoundLogic getInstance() {
		return instance;
	}

	public int getBgVolume() {
		return bgVolume;
	}

	public void setBgVolume(int bgVolume) {
		this.bgVolume = bgVolume;
		updateBgVolume(((double) bgVolume)/100);
	}

	public int getSfxVolume() {
		return sfxVolume;
	}

	public void setSfxVolume(int sfxVolume) {
		this.sfxVolume = sfxVolume;
		updateSfxVolume(((double) sfxVolume)/100);
	}

	public AudioClip getClickSound() {
		return clickSound;
	}

	public AudioClip getHitSound() {
		return hitSound;
	}

	public AudioClip getMonsterHitSound() {
		return monsterHitSound;
	}

	public AudioClip getMonsterShootSound() {
		return monsterShootSound;
	}

	public AudioClip getLoseSound() {
		return loseSound;
	}

	public AudioClip getShootSound() {
		return shootSound;
	}

	public AudioClip getWinSound() {
		return winSound;
	}
	
	
}
