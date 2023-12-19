package entity;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class Field implements IRenderable {
	private final Image[] images = {RenderableHolder.Map11,RenderableHolder.Map12,
									RenderableHolder.Map21,RenderableHolder.Map22,
									RenderableHolder.Map31,RenderableHolder.Map32,
									RenderableHolder.Map41,RenderableHolder.Map42};
	private ArrayList<WritableImage> croppedimages;
	private Image img;

	public Field(int stage) {
		img = images[stage];
		addCropped(); 
	}

	@Override
	public int getZ() {
		return -9999;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (img == null) {
			return;
		}
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 10; y++) {
				WritableImage croppedImage = croppedimages.get(x*10 + y);
				gc.drawImage(croppedImage, x * 64, y * 64);
				}
		}
	
	}
	
	public void addCropped() {
		croppedimages = new ArrayList<>();
		for (int x = 0; x < 15; x++) {
			for (int y = 0; y < 10; y++) {
				WritableImage croppedImage = new WritableImage(img.getPixelReader(),
						x * 64, y * 64, 64, 64);
				croppedimages.add(croppedImage);
				}
		}
	}
	
	@Override
	public boolean isDestroyed() {
		return false;
	}

	@Override
	public boolean isVisible() {
		return true;
	}

	public ArrayList<WritableImage> getCroppedimages() {
		return croppedimages;
	}

	public void setCroppedimages(ArrayList<WritableImage> croppedimages) {
		this.croppedimages = croppedimages;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public Image[] getImages() {
		return images;
	}
	
	
}
