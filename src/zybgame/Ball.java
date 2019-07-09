package zybgame;

import java.awt.Image;

class Ball extends Thread {
	double degree;
	final int v = 10;
	BubbleShooter panel;
	Image image;
	int x;
	int y;
	int imageIndex;
	boolean isSame = false;
	boolean isLinked = false;

	public Ball() {

	}

	public Ball(Image image) {
		this.image = image;
	}

	public Ball(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	public Ball(Image image, int x, int y, int imageIndex) {
		this.image = image;
		this.x = x;
		this.y = y;
		this.imageIndex = imageIndex;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public void setImageIndex(int imageIndex) {
		this.imageIndex = imageIndex;
	}

	public int getImageIndex() {
		return imageIndex;
	}

	public void setIsLinked(boolean isLinked) {
		this.isLinked = isLinked;
	}

	public boolean getIsLinked() {
		return isLinked;
	}

	public void setIsSame(boolean isSame) {
		this.isSame = isSame;
	}

	public boolean getIsSame() {
		return isSame;
	}

	public String toString() {
		return "imageIndex:" + imageIndex + " x:" + x + " y:" + y;
	}

	public void run() {
		super.run();
		int vx = (int) (v * Math.cos(degree - Math.PI / 2));
		int vy = (int) (v * Math.sin(degree - Math.PI / 2));
		while (true) {
			panel.mbX += vx;
			panel.mbY += vy;
			if (panel.mbX <= 30 || panel.mbX >=510) { 
				vx = -vx;
				degree=-degree;
			}

			

			try {
				sleep(10);
			} catch (InterruptedException e) {
				// e.printStackTrace();
			}
			panel.repaint();
		}

	}

}

