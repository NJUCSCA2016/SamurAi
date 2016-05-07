package ai;

public class PlayerSword extends Player{
	
	/**
	 *  假设有三个在上场 ， 你只能杀一个。那么可能杀了以后你就死了。就逃跑吧
	 */
	public final static int RUN_AWAY = 0;
	/**
	 * 可以杀一个人并且应该为安全的
	 */
	public final static int CAN_KILL_ONE = 1;
	/**
	 * 双杀。不需要考虑会不会死。死俩值了
	 */
	public final static int DOUBLE_KILL = 2;
	/**
	 * 嘿嘿
	 */
	public final static int TRIPLE_KILL = 3;
	/**
	 * 该片区域全部被占领了，并且没有人在。
	 */
	public final static int OCCUPY_ALL = 4;
	/**
	 * 没人并且部分占领了。
	 */
	public final static int NOBODY_PART = 5;
	
	
	
	@Override
	public void play() {
		// TODO Auto-generated method stub
		
	}
	

	
	
	
//	public boolean checkAndKill(){
//		
//		
//		
//	}
//	
	
	/**
	 * 
	 *	只有一个人在边上，必须得杀啊。
	 *没准还可以double kill
	 * @return
	 */
	public void  mustKillTheOnlyOneLajiGuy(){
		//Kill
	}
	/**
	 * 
	 * 能双杀还不杀这不傻逼吗。
	 * 
	 */
	public void mustDoubleKill(){
		
	}
	/**
	 * 
	 * 这个就不太可能了。
	 * 尴尬脸.jpg
	 * 
	 */
	public void allKill(){
		
		
	}


}
