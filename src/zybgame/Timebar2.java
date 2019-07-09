package zybgame;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

			
		
class Timebar2 extends Thread{
		
		
		private int[] num = new int[100];
		JProgressBar timebar=new JProgressBar() ;
		
		public Timebar2() {
			for(int i=0;i<100;i++){
				num[i]=i+1;
			}
			timebar.setIndeterminate(true);//设置进度条的样式为不确定的进度条样式（进度条来回滚动），false为确定的进度条样式（即进度条从头到尾显示）
			timebar.setStringPainted(true);//设置进度条显示提示信息
			timebar.setString("time");//设置提示信息
			timebar.setBounds(150,50,300, 20);
			timebar.setBackground(Color.green);
			timebar.setForeground(Color.orange);
			
			
		}
		
		
		
		
			
			
		
		
		public   void run() {
			timebar.setStringPainted(true);
			timebar.setIndeterminate(false);//采用确定的进度条样式
		
			for(int i = num.length-1; i>=0; i--) {
				
				try {
					timebar.setValue(num[i]);
					
					Thread.sleep(600);
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				if(i==0) {
					timebar.setString("time over"); 
					break;
				}
			}
			
		
		}
		
		@SuppressWarnings("deprecation")
		public static void main(String[] args) {
			Music music=new Music();
			BubbleShooter p = new BubbleShooter();
			p.setBounds(0, 100, 600, 850);
			Timebar2 m=new Timebar2();
			m.start();
			Timel timel=new Timel(System.currentTimeMillis());
			timel.start();
			JLabel lblBackground=new JLabel("");
			lblBackground.setIcon(new ImageIcon("background.jpg"));
	        lblBackground.setBounds(0,0,600,100);
			
			JFrame frame = new JFrame();	
			
			frame.setLayout(null);
			frame.add(m.timebar);
			frame.add(timel.timel);
			frame.add(p);
			frame.add(lblBackground);
			frame.setSize(618, 1000);		
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			
			
	}
	
	
		
	}
