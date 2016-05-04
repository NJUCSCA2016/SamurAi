package sounds;


public class Sounds {
	
	public final static Sounds SOUNDS = new Sounds();
	
	private boolean gameSoundsOn = true;
	
	private boolean backSoundsOn = true;
	
	/**
	 * 私有构造函数。不可外部创建对象
	 */
	private Sounds(){
		//Do nothing
	}
	
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

