package main;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundEffect {
	 Clip clip;
	    URL [] urls=new URL[10];


	    public SoundEffect()  {
	        urls[0]=getClass().getResource("/Sounds/coin.wav");
	        urls[1]=getClass().getResource("/Sounds/dooropen.wav");
	        urls[2]=getClass().getResource("/Sounds/unlock.wav");
	        urls[3]=getClass().getResource("/Sounds/fanfare.wav");
	    }
	    public void loadSoundEffect(int i){
	       try {
	           AudioInputStream audioInputStream= AudioSystem.getAudioInputStream(urls[i]);
	           clip=AudioSystem.getClip();
	           clip.open(audioInputStream);

	       }catch (Exception e){

	       }

	    }
	    public void playSoundEffect(){
	        clip.start();
	    }

}
