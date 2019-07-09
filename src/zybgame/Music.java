package zybgame;


import java.applet.AudioClip; 
import java.io.*; 
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
 public class Music extends JFrame{ 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	File f;
	 URI uri;
	    URL url; 
	
	 Music(){  
	 try {      
	     f = new File("bgm.wav"); 
	     uri = f.toURI();
	    url = uri.toURL();  
	    AudioClip aau; 
	      aau = Applet.newAudioClip(url);
	      aau.loop();  
	  } catch (Exception e) 
	  { e.printStackTrace();
	  } 
	 }
	 
	}