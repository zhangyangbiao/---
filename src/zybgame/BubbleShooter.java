package zybgame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
public class BubbleShooter extends JPanel {

	
	private static final long serialVersionUID = 1L;
	int mx;
	int my;
	int mbX = 280;
	int mbY = 740;

	Image ball1 = new ImageIcon("ball-1.png").getImage();
	Image ball2 = new ImageIcon("ball-2.png").getImage();
	Image ball3 = new ImageIcon("ball-3.png").getImage();
	Image ball4 = new ImageIcon("ball-4.png").getImage();
	
	Image[] images = { ball1, ball2, ball3, ball4 };

	ArrayList<Ball> balls = new ArrayList<Ball>(); 

	int index = (int) (Math.random() * 4);
	int index2 = (int) (Math.random() * 4);	
	int lastIndex;
	Ball shootBall = new Ball(images[index2], mbX, mbY,index2);	
	Ball nextBall= new Ball(images[index], mbX, mbY,index);
	int taughX;
	int taughY;
	int score = 0; 
	
	public BubbleShooter() {

		balls(); 
		
		addMouseMotionListener(new MouseAdapter() { 
			public void mouseMoved(MouseEvent e) {
				mx = e.getX();
				my = e.getY();
				repaint();
			}

		});

		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				double a = mx - 310;
				double b = my - 770;
				double degree = -Math.atan(a / b);

				if (degree > 1.4) 
					degree = 1.4;
				if (degree < -1.4)
					degree = -1.4;

				shootBall.panel = BubbleShooter.this;

				shootBall.degree = degree;

				if (!isOver()) {
					try {
						shootBall.start();
					} catch (IllegalThreadStateException e1) {
						JOptionPane.showMessageDialog(null, "Don't click before last ball is stoped");
					}
					if(score>=50)
						{
						JOptionPane.showMessageDialog(null, "Game Win!");
						
					    System.exit(0);}
					repaint();
				}

				else {
						
					
						JOptionPane.showMessageDialog(null, "Game Over!");	
					
					
				}

			}
		});

	}

	
	@SuppressWarnings("deprecation")
	public void paint(Graphics g) { 
		super.paint(g);

		Image image = new ImageIcon("background.jpg").getImage(); 
		g.drawImage(image, 0, 0, 600, 850, null);
		Image image2 = new ImageIcon("lifeline.jpg").getImage(); 
		g.drawImage(image2, 0,600,600, 10, null);

		Image gunG = new ImageIcon("gunG.png").getImage(); 
		

		
		for (int i = 0; i < balls.size(); i++) {
			g.drawImage(balls.get(i).image, balls.get(i).getX(), balls.get(i).getY(), 50,50, null);
		}

		g.drawImage(gunG, 155, 700, 300, 100, null); 

		
		
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.drawString("next",60,720);
		boolean taugh = isTaugh();
		g.drawImage(shootBall.getImage(), mbX, mbY, 50,50, null); 
		
		if (taugh) {
			shootBall.stop();
			taughX = mbX;
			taughY = mbY;

			Ball theBall = new Ball(images[shootBall.imageIndex], taughX, taughY, shootBall.imageIndex);

			balls.add(theBall); 

			
			calculateScore(clearBalls(theBall));

			mbX = 280;
			mbY = 740;
			
			shootBall=new Ball(images[index],mbX,mbY,index);
			index = (int) (Math.random() * 4);				
			nextBall=new Ball(images[index]);
			taugh = false;

		}
		
		g.drawImage(nextBall.getImage(), 65, 730, 50, 50, null); 
	
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		g.drawString("score:" + score, 440, 750);

	
		
		double a = mx - 305;
		double b = my - 765;
		double degreeR = -Math.atan(a / b);

		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(degreeR, 305, 765);
		Image gunS = new ImageIcon("gunS.png").getImage();
		g.drawImage(gunS, 255, 595, this);

	}


	public void balls() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 12; j++) {
				int dex = (int) (Math.random() * 4);
				if (i % 2 == 0) {

					balls.add(new Ball(images[dex], 50 * j, 50 * i, dex));
				} else {
					balls.add(new Ball(images[dex], 50 * j, 50 * i, dex));
				}
			}
		}
	}

	

	
	public boolean isTaugh() { 

		boolean flag=false;
		for (int j = 0; j < balls.size(); j++) {

			int distance = (int) (Math.sqrt(Math.pow(mbX - balls.get(j).x, 2) + Math.pow(mbY - balls.get(j).y, 2)));

			if (mbX != balls.get(j).x && mbY != balls.get(j).y) {

				
				if (distance < 50 ) { 

					if (mbX < balls.get(j).x) 
						mbX = mbX - 5;
					if (mbX > balls.get(j).x)
						mbX = mbX + 5;
					if (mbY < balls.get(j).y)
						mbY = mbY - 5;
					if (mbY > balls.get(j).y)
						mbY = mbY + 5;

					flag=true;
				}
			}		

		}
		return flag;
	}



	public int clearBalls(Ball theBall) {
		ArrayList<Ball> sameBalls = new ArrayList<Ball>();

		for (int m = 0; m < balls.size(); m++) {
			if (balls.get(m) == theBall)
				balls.get(m).setIsSame(true);
			else
				balls.get(m).setIsSame(false);
		}

		for (int times = 0; times < 3; times++) 
			for (int i = 0; i < balls.size(); i++) {
				if (balls.get(i).getIsSame()) 
					checkSame(balls.get(i));
			}

		int n = 0;
		for (int i = 0; i < balls.size(); i++) { 
			if (balls.get(i).getIsSame()) {
				sameBalls.add(balls.get(i));
				n++;
			}
		}

		if (sameBalls.size() >= 3) 
		{
			balls.removeAll(sameBalls);
			int m=0;
			m=checklink();
			return n+m;
		} else
			return 0;

	}
	public int checklink() {
		boolean isnotlink=true;
		ArrayList<Ball>notlink=new ArrayList<Ball>();
		for(int i=0;i<balls.size();i++) {
			for(int j=0;j<balls.size();j++) {
				int distance = (int) (Math
						.sqrt(Math.pow( balls.get(j).x- balls.get(i).x, 2) + Math.pow(balls.get(j).y - balls.get(i).y, 2)));
				if (distance <80&&distance>0) {
					isnotlink=false;
			}
		}
			if(isnotlink) {
				notlink.add(balls.get(i));		
				balls.remove(balls.get(i));
			}
			else
				isnotlink=true;
	}
		
		return notlink.size();
	}
	public void checkSame(Ball theBall) { 

		for (int i = 0; i < balls.size(); i++) { 
			if (!balls.get(i).getIsSame() && theBall.getImageIndex() == balls.get(i).getImageIndex()) {
				int distance = (int) (Math
						.sqrt(Math.pow(theBall.x - balls.get(i).x, 2) + Math.pow(theBall.y - balls.get(i).y, 2)));
				if (distance < 80) {
					balls.get(i).setIsSame(true);
				}
			}
		}

	}

	

	public void calculateScore(int number) { 
		score += number ;
		

	}

	
	public boolean isOver() { 

		
		for (int k = 0; k < balls.size(); k++) {
			
			if ( balls.get(k).y>540)
				return true;
			

		}
		
		
		
			return false;
	}

	
}


