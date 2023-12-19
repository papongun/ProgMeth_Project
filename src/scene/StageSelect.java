package scene;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.SoundLogic;
import logic.StageLogic;

public class StageSelect extends VBox {
	
	//fields
	private ToolBar header;
	private Button backButton;
	private Label sceneLabel;
	
	private HBox stagesPane;
	private Button stage1Button;
	private Button stage2Button;
	private Button stage3Button;
	private Button stage4Button;

	//constructor
	public StageSelect() {
		this.setPrefSize(960, 640);
		this.setId("root");
		this.getStylesheets().add(ClassLoader.getSystemResource("StageSelect.css").toString());
		initHeader();
		initStagesPane();
	}
	
	//sub-constructor
	private void initHeader() {
		header = new ToolBar();
		header.setPrefSize(960, 50);
		
		backButton = new Button("");
		backButton.setId("back");
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
		
		sceneLabel = new Label("Stages");
		sceneLabel.setFont(new Font("Arial", 24));
		sceneLabel.setPadding(new Insets(0,0,0,10));
		
		header.getItems().setAll(backButton, sceneLabel);		
		this.getChildren().add(header);
	}
	
	private void initStagesPane() {
		stagesPane = new HBox();
		stagesPane.setPrefSize(960, 590);
		stagesPane.setAlignment(Pos.CENTER);
		stagesPane.setSpacing(100);
		
		stage1Button = new Button("1");
		stage1Button.setPrefSize(100, 100);
		stage1Button.getStyleClass().add("stage-button");
		stage1Button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				SoundLogic.getInstance().getClickSound().play();
				StageLogic.getInstance().setStage(0);
				StageLogic.getInstance().setSubstage(0);
				Scene scene = new Scene(new StageScene());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
			
		});
		
		stage2Button = new Button("2");
		stage2Button.setPrefSize(100, 100);
		stage2Button.getStyleClass().add("stage-button");
		if (!StageLogic.getInstance().getStageUnlocked().get(1)) {
			stage2Button.setDisable(true);
		}
		stage2Button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				SoundLogic.getInstance().getClickSound().play();		
				StageLogic.getInstance().setStage(1);
				StageLogic.getInstance().setSubstage(0);
				Scene scene = new Scene(new StageScene());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
			
		});
		
		stage3Button = new Button("3");
		stage3Button.setPrefSize(100, 100);
		stage3Button.getStyleClass().add("stage-button");
		if (!StageLogic.getInstance().getStageUnlocked().get(2)) {
			stage3Button.setDisable(true);
		}
		stage3Button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				SoundLogic.getInstance().getClickSound().play();
				StageLogic.getInstance().setStage(2);
				StageLogic.getInstance().setSubstage(0);
				Scene scene = new Scene(new StageScene());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
			
		});
		
		stage4Button = new Button("4");
		stage4Button.setPrefSize(100, 100);
		stage4Button.getStyleClass().add("stage-button");
		if (!StageLogic.getInstance().getStageUnlocked().get(3)) {
			stage4Button.setDisable(true);
		}
		stage4Button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				SoundLogic.getInstance().getClickSound().play();
				StageLogic.getInstance().setStage(3);
				StageLogic.getInstance().setSubstage(0);
				Scene scene = new Scene(new StageScene());
				Stage stage = (Stage)((Node) arg0.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			}
			
		});
		
		stagesPane.getChildren().addAll(stage1Button, stage2Button ,stage3Button, stage4Button);
		
		this.getChildren().add(stagesPane);	
	}
}
