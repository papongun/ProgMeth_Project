package drawing;

import entity.Character;
import input.InputUtility;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import logic.GameLogic;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;
import javafx.scene.input.KeyEvent;

public class GameScreen extends Canvas {
	
	public GameScreen(double width, double height) {
		super(width, height);
		this.setVisible(true);
		addListerner();
		setFocusTraversable(true);
	}

	public void addListerner() {
		this.setOnKeyPressed((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), true);
		});

		this.setOnKeyReleased((KeyEvent event) -> {
			InputUtility.setKeyPressed(event.getCode(), false);
		});
	}

	public void paintComponent() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0,0,gc.getCanvas().getWidth(),gc.getCanvas().getHeight());				
		for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
			if (entity.isVisible() && !entity.isDestroyed()) {
				entity.draw(gc);
			}else if (entity.isVisible() && entity instanceof Character) {
				Character en = (Character)entity;
				en.drawDead(gc);
			}
		}
		gc.drawImage(RenderableHolder.HpbarSprite,10, 580);
		if (GameLogic.Monsters.size() <= 0) {			
			gc.drawImage(RenderableHolder.SignSprite, 870, 288);
		}
	}

}
