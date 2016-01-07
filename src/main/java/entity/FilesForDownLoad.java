package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FilesForDownLoad {

	private String fileName;
	private BufferedImage image;
	private Graphics2D graphics2d;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public Graphics2D getGraphics2d() {
		return graphics2d;
	}
	public void setGraphics2d(Graphics2D graphics2d) {
		this.graphics2d = graphics2d;
	}
	
}
