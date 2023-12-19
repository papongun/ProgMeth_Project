package scene;

import java.util.ArrayList;

import item.Rarity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.ItemLogic;
import logic.SoundLogic;

public class Inventory extends VBox {
	
	//fields
	private ToolBar header;
	private Button backButton;
	private Label sceneLabel;
	
	private VBox equipmentPane;
	private Label arrowLabel, armorLabel, ringLabel;
	private GridPane arrowSlot, armorSlot, ringSlot;
	private ToggleGroup arrowGroup, armorGroup, ringGroup;
	private Button unEquipArmor, unEquipRing;
	private ArrayList<RadioButton> arr;
	private ArrayList<RadioButton> arm;
	private ArrayList<RadioButton> ring;
	
	private Label statLabel;
	private GridPane statPane;
	private Text att;
	private Text hp;
	private Text attSpeed;
	private Text walkSpeed;
	
	//constructor
	public Inventory() {
		this.setId("root");
		this.setPrefSize(960, 640);
		this.getStylesheets().add(ClassLoader.getSystemResource("Inventory.css").toString());
		initHeader();
		initEquipmentPane();
		showHeroStat();
		updateEquipping();
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
		
		sceneLabel = new Label("Inventory");
		sceneLabel.setFont(new Font("Arial", 24));
		sceneLabel.setPadding(new Insets(0,0,0,10));
		
		HBox guide = new HBox();
		guide.setPrefWidth(750);
		guide.setAlignment(Pos.CENTER_RIGHT);
		Text t0 = new Text("Click on ");
		t0.setFont(new Font(null, 20));
		t0.setFill(Color.WHITE);
		RadioButton r = new RadioButton();
		r.setDisable(true);
		Text t1 = new Text(" to equip item");
		t1.setFont(new Font(null, 20));
		t1.setFill(Color.WHITE);
		guide.getChildren().addAll(t0,r,t1);
		
		header.getItems().setAll(backButton, sceneLabel, guide);		
		this.getChildren().add(header);
	}
	
	private void initEquipmentPane() {
		equipmentPane = new VBox();
		equipmentPane.setPadding(new Insets(10,0,0,50));
		equipmentPane.setSpacing(10);
		
		arrowLabel = new Label("Arrow");
		arrowLabel.setFont(new Font("Arial", 20));
		arrowSlot = new GridPane();
		initArrowSelector();

		armorLabel = new Label("Armor");
		armorLabel.setFont(new Font("Arial", 20));
		armorSlot = new GridPane();
		initArmorSelector();
		
		ringLabel = new Label("Ring");
		ringLabel.setFont(new Font("Arial", 20));
		ringSlot = new GridPane();
		initRingSelector();
		
		equipmentPane.getChildren().addAll(arrowLabel, arrowSlot, armorLabel, unEquipArmor, armorSlot, ringLabel, unEquipRing, ringSlot);
		this.getChildren().add(equipmentPane);
	}
	
	private static void boxLayoutSetup(HBox box, Rarity rarity) {
		Border boxBorder = null;
		switch (rarity) {
		case Common:
			boxBorder = new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
			break;
			
		case Rare:
			boxBorder = new Border(new BorderStroke(Color.MEDIUMTURQUOISE, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
			break;
		
		case Epic:
			boxBorder = new Border(new BorderStroke(Color.PURPLE, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
			break;
		
		case Legendary:
			boxBorder = new Border(new BorderStroke(Color.MEDIUMSEAGREEN, BorderStrokeStyle.SOLID, null, new BorderWidths(5)));
			break;
		}

	
		box.setAlignment(Pos.CENTER);
		box.setPrefSize(100, 100);
		box.setSpacing(10);
		box.setPadding(new Insets(10,10,10,10));
		box.setBorder(boxBorder);
	}
	
	private static void itemImgLayoutSetup(ImageView img) {
		img.setFitWidth(50);
		img.setPreserveRatio(true);
	}
	
	private static void itemTextLayoutSetup(Text text, String fontName, int fontSize) {
		text.setFont(new Font(fontName, fontSize));
	}
	
	private static void textBoxLayoutSetup(VBox box) {
		box.setSpacing(10);
		box.setAlignment(Pos.CENTER);
	}
	private void initArrowSelector() {
		arrowGroup = new ToggleGroup();
		arr = new ArrayList<RadioButton>();
		for (int i=0; i<4; i++) {
			
			//create new box of item
			HBox arr0Box = new HBox();
			boxLayoutSetup(arr0Box, ItemLogic.getInstance().getAllArrows().get(i).getRarity());
			
			//create radiobutton (item selector)
			arr.add(new RadioButton());
			arr.get(i).setToggleGroup(arrowGroup);
			arr.get(i).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					updateEquipping();
				}
				
			});
			
			ImageView arr0Img = new ImageView(ItemLogic.getInstance().getAllArrows().get(i).getIURL());
			itemImgLayoutSetup(arr0Img);
			
			VBox arr0TextBox = new VBox();
			textBoxLayoutSetup(arr0TextBox);
			Text arr0Name = new Text(ItemLogic.getInstance().getAllArrows().get(i).getName());
			Text arr0Stat = new Text(
					"Attack Damage +" + ItemLogic.getInstance().getAllArrows().get(i).getAttackdamage() + "\n" + 
					"Attack Delay : " + ItemLogic.getInstance().getAllArrows().get(i).getAttackspeed() + " s");
			if (ItemLogic.getInstance().getAllArrows().get(i).isPercing()) {
				arr0Stat.setText(arr0Stat.getText() + "\n" + "Skill : Piercing");
			}
			itemTextLayoutSetup(arr0Name, "Arial", 14);
			itemTextLayoutSetup(arr0Stat, "Arial", 12);
			arr0TextBox.getChildren().addAll(arr0Name, arr0Stat);
			
			arr0Box.getChildren().addAll(arr.get(i), arr0Img, arr0TextBox);
			
			arrowSlot.add(arr0Box, i, 0);
			//check if item is locked show radio disable
			if (!ItemLogic.getInstance().getAllArrows().get(i).isUnlocked()) {
				arr.get(i).setDisable(true);
				arr0Box.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
				arr0Stat.setText("LOCKED");
				arr0Stat.setFill(Color.RED);
			}
			else {
				arr.get(i).setDisable(false);
			}
			
			//check which item is equipping
			if (ItemLogic.getInstance().getAllArrows().indexOf(ItemLogic.getInstance().getEquippingArrow()) == i) {
				arr.get(i).setSelected(true);
			}
		}
	}

	private void initArmorSelector() {
		armorGroup = new ToggleGroup();
		arm = new ArrayList<RadioButton>();
		
		//unEquip button
		unEquipArmor = new Button("Unequip Armor");
		unEquipArmor.getStyleClass().add("unequip");
		unEquipArmor.setTextFill(Color.WHITE);
		unEquipArmor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i=0; i<arm.size(); i++) {
					arm.get(i).setSelected(false);
				}
				ItemLogic.getInstance().setEquippingArmor(null);
				updateEquipping();
			}
			
		});
		
		for (int i=0; i<4; i++) {
			
			//create new box of item
			HBox arm0Box = new HBox();
			boxLayoutSetup(arm0Box, ItemLogic.getInstance().getAllArmors().get(i).getRarity());
			
			//create radiobutton (item selector)
			arm.add(new RadioButton());
			arm.get(i).setToggleGroup(armorGroup);
			arm.get(i).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					updateEquipping();
				}
				
			});
			
			ImageView arm0Img = new ImageView(ItemLogic.getInstance().getAllArmors().get(i).getIURL());
			itemImgLayoutSetup(arm0Img);
			
			VBox arm0TextBox = new VBox();
			textBoxLayoutSetup(arm0TextBox);
			Text arm0Name = new Text(ItemLogic.getInstance().getAllArmors().get(i).getName());
			Text arm0Stat = new Text(
					"Bonus Health +" + ItemLogic.getInstance().getAllArmors().get(i).getBonushp() + "\n" + 
					"Bonus Speed : " + (int)(ItemLogic.getInstance().getAllArmors().get(i).getBonuswalkspeed() * 100) + "%");
			itemTextLayoutSetup(arm0Name, "Arial", 14);
			itemTextLayoutSetup(arm0Stat, "Arial", 12);
			arm0TextBox.getChildren().addAll(arm0Name, arm0Stat);
			
			arm0Box.getChildren().addAll(arm.get(i), arm0Img, arm0TextBox);
			
			armorSlot.add(arm0Box, i, 0);
			//check if item is locked show radio disable
			if (!ItemLogic.getInstance().getAllArmors().get(i).isUnlocked()) {
				arm.get(i).setDisable(true);
				arm0Box.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
				arm0Stat.setText("LOCKED");
				arm0Stat.setFill(Color.RED);
			}
			else {
				arm.get(i).setDisable(false);
			}
			
			//check which item is equipping
			if (ItemLogic.getInstance().getAllArmors().indexOf(ItemLogic.getInstance().getEquippingArmor()) == i) {
				arm.get(i).setSelected(true);
			}
		}
	}
	
	private void initRingSelector() {
		ringGroup = new ToggleGroup();
		ring = new ArrayList<RadioButton>();
		
		//unEquip button
		unEquipRing = new Button("Unequip Ring");
		unEquipRing.setTextFill(Color.WHITE);
		unEquipRing.getStyleClass().add("unequip");
		unEquipRing.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for (int i=0; i<ring.size(); i++) {
						ring.get(i).setSelected(false);
				}
				ItemLogic.getInstance().setEquippingRing(null);
						updateEquipping();
			}
					
		});
		
		for (int i=0; i<4; i++) {
			
			//create new box of item
			HBox ring0Box = new HBox();
			boxLayoutSetup(ring0Box, ItemLogic.getInstance().getAllRings().get(i).getRarity());
			
			//create radiobutton (item selector)
			ring.add(new RadioButton());
			ring.get(i).setToggleGroup(ringGroup);
			ring.get(i).setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					// TODO Auto-generated method stub
					updateEquipping();
				}
				
			});
			
			ImageView ring0Img = new ImageView(ItemLogic.getInstance().getAllRings().get(i).getIURL());
			itemImgLayoutSetup(ring0Img);
			
			VBox ring0TextBox = new VBox();
			textBoxLayoutSetup(ring0TextBox);
			Text ring0Name = new Text(ItemLogic.getInstance().getAllRings().get(i).getName());
			Text ring0Stat = new Text();
			if (ItemLogic.getInstance().getAllRings().get(i).getAttackdamage() > 0) {
				ring0Stat.setText("AttackDamage : +" + ItemLogic.getInstance().getAllRings().get(i).getAttackdamage());
			}
			if (ItemLogic.getInstance().getAllRings().get(i).getBonushp() > 0) {
				ring0Stat.setText(ring0Stat.getText() + "\n" + "Bonus Health +" + ItemLogic.getInstance().getAllRings().get(i).getBonushp());
			}
			if (ItemLogic.getInstance().getAllRings().get(i).getBonuswalkspeed() > 0) {
				ring0Stat.setText(ring0Stat.getText() + "\n" + "Bonus Speed +" + (int)(ItemLogic.getInstance().getAllRings().get(i).getBonuswalkspeed() * 100) + "%");
			}
			
			itemTextLayoutSetup(ring0Name, "Arial", 14);
			itemTextLayoutSetup(ring0Stat, "Arial", 12);
			ring0TextBox.getChildren().addAll(ring0Name, ring0Stat);
			
			ring0Box.getChildren().addAll(ring.get(i), ring0Img, ring0TextBox);
			
			ringSlot.add(ring0Box, i, 0);
			//check if item is locked show radio disable
			if (!ItemLogic.getInstance().getAllRings().get(i).isUnlocked()) {
				ring.get(i).setDisable(true);
				ring0Box.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));
				ring0Stat.setText("LOCKED");
				ring0Stat.setFill(Color.RED);
			}
			else {
				ring.get(i).setDisable(false);
			}
			
			//check which item is equipping
			if (ItemLogic.getInstance().getAllRings().indexOf(ItemLogic.getInstance().getEquippingRing()) == i) {
				ring.get(i).setSelected(true);
			}
		}
	}
	
	private void showHeroStat() {
		statLabel = new Label("Hero Stat");
		statLabel.setFont(new Font("Arial", 20));
		
		statPane = new GridPane();
		statPane.setHgap(10);
		equipmentPane.getChildren().addAll(statLabel, statPane);
	}
	
	private void updateHeroStatPane() {
		statPane.getChildren().clear();
		att = new Text(new String(Character.toChars(0x02694)) + " Attack Damage : " + ItemLogic.getInstance().getAttackDamage() + " |");
		att.setFont(new Font("Arial", 16));
		att.setFill(Color.WHITE);
		
		hp = new Text(new String(Character.toChars(0x02764)) + " Health : " + ItemLogic.getInstance().getMaxHealth() + " |");
		hp.setFont(new Font("Arial", 16));
		hp.setFill(Color.LIGHTGRAY);
		
		attSpeed = new Text(new String(Character.toChars(0x1F552)) + " Attack Delay: " + ItemLogic.getInstance().getAttackSpeed() + " s |");
		attSpeed.setFont(new Font("Arial", 16));
		attSpeed.setFill(Color.WHITE);
		
		walkSpeed = new Text(new String(Character.toChars(0x1F4A8)) + " Walk Speed: " + ItemLogic.getInstance().getWalkSpeed());
		walkSpeed.setFont(new Font("Arial", 16));
		walkSpeed.setFill(Color.LIGHTGRAY);
		statPane.add(att, 0, 0);
		statPane.add(hp, 1, 0);
		statPane.add(attSpeed, 2, 0);
		statPane.add(walkSpeed, 3, 0);
	}
	
	
	private void updateEquipping() {
		// TODO Auto-generated method stub
		for (int i=0; i<4; i++) {
			if (arr.get(i).isSelected()) {
				( (HBox) arr.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
				ItemLogic.getInstance().setEquippingArrow(ItemLogic.getInstance().getAllArrows().get(i));
			}
			else if (ItemLogic.getInstance().getAllArrows().get(i).isUnlocked()) {
				( (HBox) arr.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			}
		}
		
		for (int i=0; i<4; i++) {
			if (arm.get(i).isSelected()) {
				( (HBox) arm.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
				ItemLogic.getInstance().setEquippingArmor(ItemLogic.getInstance().getAllArmors().get(i));
			}
			else if (ItemLogic.getInstance().getAllArmors().get(i).isUnlocked()) {
				( (HBox) arm.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			}
		}
		
		for (int i=0; i<4; i++) {
			if (ring.get(i).isSelected()) {
				( (HBox) ring.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
				ItemLogic.getInstance().setEquippingRing(ItemLogic.getInstance().getAllRings().get(i));
			}
			else if (ItemLogic.getInstance().getAllRings().get(i).isUnlocked()) {
				( (HBox) ring.get(i).getParent()).setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
			}
		}
		
		
		ItemLogic.getInstance().updateHeroStat();
		updateHeroStatPane();
	}
	
	
	
 }
