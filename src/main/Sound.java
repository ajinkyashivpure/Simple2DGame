package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public URL [] urls=new URL[2];
	Clip clip;
	
	//constructor
	public Sound() {
		
		//this is the basic way to read any file address stored in the ide itself.
		urls[0]=getClass().getResource("/sounds/BlueBoyAdventure.wav");
	}
	public void loadsound(int i) {
		try {
			
			AudioInputStream audioInputStream=AudioSystem.getAudioInputStream(urls[i]);
			clip=AudioSystem.getClip();
			clip.open(audioInputStream);
					
;
			}
		catch(Exception e) {
			
		}
		}
	public void playMusic() {
		clip.start();
	}
	public void loopMusic() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	public void stopMusic() {
		clip.stop();
	}
	
			
		
		
	
	

}
