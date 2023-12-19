package scene;

import drawing.GameScreen;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.layout.StackPane;
import logic.GameLogic;
import sharedObject.RenderableHolder;

public class StageScene extends StackPane{
	
    private int frameNum;
    //private long startTime;
	
	public StageScene() {
		this.setPrefSize(960, 640);
		initScene();
	}
	
	private void initScene() {

		GameLogic logic = new GameLogic();
		GameScreen gameScreen = new GameScreen(960, 640);
		getChildren().add(gameScreen);
		gameScreen.requestFocus();

		AnimationTimer animation = new AnimationTimer() {
		    private int framesPerSec = 120; // Desired frames per second. You can modify this value!
		    private long nSecPerFrame = Math.round(1.0/framesPerSec * 1e9);
		    private long lastUpdate = 0;
			public void handle(long now) {				
				if (now - lastUpdate > nSecPerFrame) {
                    frameNum++;
                    if (!logic.isStageGameEnd()) {
                    	logic.logicUpdate();
        				gameScreen.paintComponent();
        				if (!logic.isStageGameEnd()) {	
        					RenderableHolder.getInstance().update();
        				}
                    }				
                    lastUpdate = now;
                }
            }
		};
		animation.start();
	}
}
