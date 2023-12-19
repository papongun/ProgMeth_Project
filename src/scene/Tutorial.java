package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.SoundLogic;

public class Tutorial extends VBox {
	
	//fields
	private ToolBar header;
	private Button backButton;
	private Label sceneLabel;
		
	private VBox tutorialPane;
	
	public Tutorial() {
		this.setId("root");
		this.setPrefSize(960, 640);
		this.getStylesheets().add(ClassLoader.getSystemResource("Tutorial.css").toString());
		initHeader();
		initTutorialPane();
	}
	
	private void initHeader() {
		header = new ToolBar();
		header.setPrefSize(960, 50);
		
		backButton = new Button("");
		backButton.setPrefSize(40, 40);
		backButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Scene scene = new Scene(new MainMenu());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		
		});
		
		sceneLabel = new Label("Tutorial");
		sceneLabel.setFont(new Font("Arial", 24));
		sceneLabel.setPadding(new Insets(0,0,0,10));
		header.getItems().setAll(backButton, sceneLabel);		
		this.getChildren().add(header);
	}
	
	private void initTutorialPane() {
		tutorialPane = new VBox();
		tutorialPane.setPrefSize(960, 590);
		tutorialPane.setId("tpane");
		this.getChildren().add(tutorialPane);
	}
}
