package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.SceneMode;
import logic.SoundLogic;
import scene.MainMenu;

public class Main extends Application {
	
	private static Stage guiStage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);	
	}
	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		setGuiStage(arg0);
		Scene scene = new Scene(new MainMenu());
		arg0.setTitle("Archery");
		arg0.getIcons().add(new Image(ClassLoader.getSystemResource("logo.jpg").toString()));
		arg0.setResizable(false);
		arg0.setScene(scene);
		arg0.show();
		SoundLogic.getInstance().setSceneMode(SceneMode.Menu);
	}

	public static Stage getGuiStage() {
		return guiStage;
	}

	public static void setGuiStage(Stage guiStage) {
		Main.guiStage = guiStage;
	}
	
}
