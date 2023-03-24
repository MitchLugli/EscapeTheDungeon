package Components;

import javax.sound.sampled.*;
import java.io.*;

public class AudioManager {
	
	private Clip clip;
	private File[] audioFiles;
	
	public AudioManager(){
		audioFiles = new File[30];
		audioFiles[0] = new File("src/resources/audio/BeepBox-Song2.wav");
		audioFiles[1] = new File("src/resources/audio/playerGotHit.wav");
		//https://www.beepbox.co/#9n31s0k0l00e03t2ma7g0fj07r1i0o512T3v1uf3f12rcq2x10x5f1d08SU0M51pr2qiqqrrrE2b686T1v3u95f0q0z10u231d19AcFhBcQ0358P8888E1bfT1v0u40f0qwx10r511d08A4F2B6Q0068Pf624E2b676T4v1uf0f0q011z6666ji8k8k3jSBKSJJAArriiiiii07JCABrzrrrrrrr00YrkqHrsrrrrjr005zrAqzrjzrrqr1jRjrqGGrrzsrsA099ijrABJJJIAzrrtirqrqjqixzsrAjrqjiqaqqysttAJqjikikrizrHtBJJAzArzrIsRCITKSS099ijrAJS____Qg99habbCAYrDzh00E0b4h40000000000g000000014i000000004h400000000p1X09MqqfywUddjI1yPOZCzZi8M96CzS0OW3bEcKWAzBY2nRFGu1p8FOGW2Y00
		audioFiles[2] = new File("src/resources/audio/dungeonOst.wav");
	}
	
	public void setClip(int i){
		try {
			clip = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFiles[i]);
			clip.open(ais);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private void play(){
		clip.start();
	}
	private void loop(){
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	private void stop(){
		clip.stop();
	}
	
	public void playMusic(int i){
		setClip(i);
		play();
		loop();
	}
	public void stopMusic(){
		stop();
	}
	public void playSoundEffect(int i){
		setClip(i);
		play();
	}
}
