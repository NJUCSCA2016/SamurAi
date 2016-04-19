package sounds;


public class Sounds {
	
	private boolean gameSoundsOn = true;
	
	private boolean backSoundsOn = true;
	
	
	
	
	/**
	 * 播放音乐
	 */
	
	public void Play(){
		//然而我并不会写
	}
	
	
	
	/***
	 * 设置游戏音效。
	 */
	public void setGameSounds(boolean gameSounds){
		this.gameSoundsOn = gameSounds;
	}
	
	public boolean getGameSounds(){
		return this.gameSoundsOn;
	}
	public void setBackSounds(boolean backSounds){
		this.backSoundsOn = backSounds;
	}
	public boolean getBackSounds(){
		return this.backSoundsOn;
	}
}

