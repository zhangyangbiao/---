package zybgame;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

			
		
class Timel extends Thread{
		
		
		JLabel timel;
		long time = 0; // 时间
		public long second;
		public Timel(long time) {
		
			this.time=time;
			
			timel=new JLabel();
			
			timel.setBounds(280,10,100,30);
			
				
			timel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));	
			timel.setText("0 : 60");
			
			
		}
		
		public  synchronized void run() {
			
		
			
				for(int i=0;i<61;i++) {
				try {
					long nowTime = Math.round((System.currentTimeMillis() - time) / 1000); // 当前所用秒数
				    long minute = nowTime / 60;
				    long second =60-(nowTime - minute * 60);
					
					timel.setText("0:"+second);
					
					Thread.sleep(1000);
					if(second==1) {
						
						
						JOptionPane.showMessageDialog(null, "Game Over!");
						System.exit(0);
					
					
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				}
		}
		
			
	
		
	}
