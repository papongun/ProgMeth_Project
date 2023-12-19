package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.SoundLogic;

public class MainMenu extends VBox{

	//fields
	private Label gameName;
	private Button startButton;
	private Button inventoryButton;
	private Button tutorialButton;
	private Button optionButton;
	private Button exitButton;

	//constructor
	public MainMenu() {
		this.setPrefSize(960, 640);
		this.setAlignment(Pos.CENTER);
		this.setSpacing(15);
		this.getStylesheets().add(ClassLoader.getSystemResource("MainMenu.css").toString());
		this.setId("root");
		initGameNameLabel();
		initStartButton();
		initInventoryButton();
		initTutorialButton();
		initOptionButton();
		initExitButton();
	}
	
	//sub-constructor
	private void initGameNameLabel() {
		gameName = new Label("Archery");
		gameName.setFont(new Font("Arial", 72));
		gameName.setPadding(new Insets(0, 0, 30, 0));
		this.getChildren().add(gameName);
	}
	
	private void initStartButton() {
		startButton = new Button("Start");
		startButton.setPrefSize(250, 50);
		startButton.setFont(new Font("Arial", 14));
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Scene scene = new Scene(new StageSelect());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		
		});
		this.getChildren().add(startButton);
	}
	
	private void initInventoryButton() {
		inventoryButton = new Button("Inventory");
		inventoryButton.setPrefSize(250, 50);
		inventoryButton.setFont(new Font("Arial", 14));
		inventoryButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Scene scene = new Scene(new Inventory());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		
		});
		this.getChildren().add(inventoryButton);
	}
	
	private void initTutorialButton() {
		tutorialButton = new Button("Tutorial");
		tutorialButton.setPrefSize(250, 50);
		tutorialButton.setFont(new Font("Arial", 14));
		tutorialButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Scene scene = new Scene(new Tutorial());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		
		});
		this.getChildren().add(tutorialButton);
	}
	
	private void initOptionButton() {
		optionButton = new Button("Options");
		optionButton.setPrefSize(250, 50);
		optionButton.setFont(new Font("Arial", 14));
		optionButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Scene scene = new Scene(new Option());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
		
		});
		this.getChildren().add(optionButton);
	}
	
	private void initExitButton() {
		exitButton = new Button("Exit");
		exitButton.setPrefSize(250, 50);
		exitButton.setFont(new Font("Arial", 14));
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//RenderableHolder.ClickSound.play();
				SoundLogic.getInstance().getClickSound().play();
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.close();
			}
		
		});
		this.getChildren().add(exitButton);
	}
}
