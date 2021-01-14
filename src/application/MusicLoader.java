package application;

import java.io.File;
import java.util.ArrayList;

import javax.sound.sampled.*;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicLoader {

	private static boolean isMuted = false;
	private static boolean isPlayed = true;
	
	private static ArrayList<MediaPlayer> clips = new ArrayList<>();
	 public static ArrayList<Clip> clipsa =new ArrayList<>();
	 private static Media media ;
	    
	 public static MediaPlayer mediaPlayerM ;
	
	 
	
	public static boolean getPlayed() {return isPlayed; }
	public static void setPlayed(boolean p) {isPlayed=p; }

	public static void muteSound(boolean mute) {
		if(mute) {
			for (MediaPlayer clip : clips) {
				clip.pause();
			}
			isMuted = true;
		}else {
			for (MediaPlayer clip : clips) {
				clip.play();
			}
			mediaPlayerM.setOnEndOfMedia(new Runnable() {
	            @Override
	            public void run() {
	            	mediaPlayerM.seek(Duration.ZERO);
	            	mediaPlayerM.play();
	            }
	        }); 
			isMuted = false; 
		}
	}

	public static MediaPlayer startBackgroundMusic() {
		if(!isMuted) {
			File sound = new File("graphics/farkle/sounds/Barcelona.wav");
			String path = "graphics/farkle/sounds/Barcelona.wav";
	    	media=new Media(new File(path).toURI().toString());
	    	mediaPlayerM=new MediaPlayer(media);
	    	mediaPlayerM.setVolume(0.03);
	    	mediaPlayerM.play();
	    	mediaPlayerM.setOnEndOfMedia(new Runnable() {
	            @Override
	            public void run() {
	            	mediaPlayerM.seek(Duration.ZERO);
	            	mediaPlayerM.play();
	            }
	        }); 
	    	
				clips.add(mediaPlayerM);
			
			
		}
		return mediaPlayerM;
	}
	
	public static void loadSound(String path) {
		if(!isMuted) {
			File sound = new File("graphics/farkle/sounds/" + path);
			try {
				Clip clip = AudioSystem.getClip();
				clipsa.add(clip);
				clip.open(AudioSystem.getAudioInputStream(sound));
				FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				gainControl.setValue(-10f);
				clip.start();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
