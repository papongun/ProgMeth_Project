package scene;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.SoundLogic;

public class Option extends VBox {
	
	//fields
	private ToolBar header;
	private Button backButton;
	private Label sceneLabel;
	
	private VBox optionPane;
	private Label bgSoundAdjustLabel , sfxSoundAdjustLabel;
	private Slider bgSoundAdjustSlider, sfxSoundAdjustSlider;
	
	//constructor
	public Option() {
		this.setPrefSize(960, 640);
		this.getStylesheets().add(ClassLoader.getSystemResource("Option.css").toString());
		this.setId("root");
		initHeader();
		initOptionPane();
	}
	
	//sub-constructor
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
		
		sceneLabel = new Label("Option");
		sceneLabel.setFont(new Font("Arial", 24));
		sceneLabel.setPadding(new Insets(0,0,0,10));
		
		header.getItems().setAll(backButton, sceneLabel);		
		this.getChildren().add(header);
	}
	
	private void initOptionPane() {
		optionPane = new VBox();
		optionPane.setAlignment(Pos.CENTER_LEFT);
		optionPane.setSpacing(50);
		optionPane.setPadding(new Insets(130,250,0,250));
		
		bgSoundAdjustLabel = new Label("Background music volume : " + SoundLogic.getInstance().getBgVolume());
		bgSoundAdjustLabel.setFont(new Font(null, 28));
		bgSoundAdjustSlider = new Slider();
		bgSoundAdjustSlider.setValue(SoundLogic.getInstance().getBgVolume());
		bgSoundAdjustSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
            	bgSoundAdjustLabel.textProperty().setValue("Background music volume : " + String.valueOf(newValue.intValue()));
            	SoundLogic.getInstance().setBgVolume((int) Math.round((Double) newValue));
              }
        });
		
		sfxSoundAdjustLabel = new Label("Sound effect volume : " + SoundLogic.getInstance().getSfxVolume());
		sfxSoundAdjustLabel.setFont(new Font(null, 28));
		sfxSoundAdjustSlider = new Slider();
		sfxSoundAdjustSlider.setValue(SoundLogic.getInstance().getSfxVolume());
		sfxSoundAdjustSlider.valueProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(
               ObservableValue<? extends Number> observableValue, 
               Number oldValue, 
               Number newValue) { 
            	sfxSoundAdjustLabel.textProperty().setValue("Sound effect volume : " + String.valueOf(newValue.intValue()));
            	SoundLogic.getInstance().setSfxVolume((int) Math.round((Double) newValue));
              }
        });


		
		
		optionPane.getChildren().addAll(bgSoundAdjustLabel, bgSoundAdjustSlider, sfxSoundAdjustLabel, sfxSoundAdjustSlider);
		this.getChildren().add(optionPane);
	}
}

