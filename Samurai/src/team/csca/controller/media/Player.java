package team.csca.controller.media;

import java.io.File;

import saint.media.MidiPlayer;
import saint.media.SimplePlayer;

/**
 * 音乐播放器
 * 凡是涉及到背景音乐、游戏音效等方面的播放均要用到
 * @author Water
 *
 */
public class Player {
	
	/**
	 * @author With You
	 * Using in JButtonGameSound and making sure it's singleton
	 */
	public final static Player MUSiC_PLAYER = new Player();
	
	private boolean back_ON = true;
	
	private boolean game_ON = true;
	
	private Player(){}
	
//	public void turnOnBack(){
//		this.back_ON = true;
//	}
//	
//	public void turnOffBack(){
//		this.back_ON = false;
//	}
	
//	public void turnOnGame(){
//		this.game_ON = true;
//	}
//	
//	public void turnOffGame() {
//		this.game_ON = false;
//	}
	
	
	// TODO: 目前并不清楚我们需要用到哪些类型的音频，所以先按照学长的来写。
	/**
	 * 功能是用来打开mp3类型的文件
	 * 在项目中是专门用来播放mp3类型的背景音乐
	 */
	private static SimplePlayer musicPlayer = null;
	
	public boolean isBack_ON() {
		return back_ON;
	}

	public void changeBack_ON() {
		this.back_ON = !this.back_ON;
	}

	public boolean isGame_ON() {
		return game_ON;
	}

	public void changeGame_ON() {
		this.game_ON = !this.game_ON;
	}

	/**
	 * 功能是用来打开mp3类型的文件
	 * 在项目中是专门用来播放mp3类型的游戏音效
	 */
	private static SimplePlayer soundPlayer = null;
	/**
	 * 功能是用来打开midi类型的文件
	 * 在项目中是专门用来播放midi类型的背景音乐
	 */
	private static MidiPlayer midiPlayer = null;

	/**
	 * 播放midi文件
	 * @param .mid文件的文件名
	 */
	public static void playMidi(String name) {
		if (midiPlayer != null)
			midiPlayer.stop();
		else 
			midiPlayer = new MidiPlayer();
		if (musicPlayer != null)
			stopMusic();
		
		try {
			midiPlayer.open(new File("media/midi/" + name + ".mid"));
			midiPlayer.setLoop(true);
			midiPlayer.setLoopCount(1000);
		} catch (Exception e) {
			System.err.println("Error!");
			return;
		}
		try {
			midiPlayer.play();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 播放MP3音乐
	 * @param .mp3文件的文件名
	 */
	public static void playMusic(String name) {
		// TODO: 还没有添加判断条件，判断条件应该为音乐开关
		if (true) {
			if (midiPlayer != null)
				midiPlayer.stop();
			else 
				midiPlayer = new MidiPlayer();
			
			musicPlayer = new SimplePlayer();
			try{
				musicPlayer.open(new File("media/" + name + ".mp3"));
				musicPlayer.setLoop(true);
				musicPlayer.setLoopCount(1000);
			}catch (Exception e) {
				System.err.println("Error！");
				return;
			}
			try {
				musicPlayer.play();	
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 播放MP3音效
	 * @param .mp3文件的文件名
	 */
	public static void playSound(String name) {
		// TODO: 还没有添加判断条件，判断条件应该为音效开关
		if (true) {
			soundPlayer = new SimplePlayer();
			try {
				soundPlayer.open(new File("media/sound/" + name + ".mp3"));
				soundPlayer.setLoop(false);
			} catch (Exception e) {
				System.err.println("Error!");
				return;
			}
			try {
				soundPlayer.play();	
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 停止mp3音乐
	 */
	public static void stopMusic() {
		musicPlayer.setVolume(0);
	}
	
	/**
	 * 停止midi音乐
	 */
	public static void stopMidi() {
		midiPlayer.stop();
	}


}
