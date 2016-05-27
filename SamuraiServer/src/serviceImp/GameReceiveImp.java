package serviceImp;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import control.Control;
import control.GameNotFoundException;
import control.TRAGameControl;
import control.PPGameControl;
import team.csca.server.GameReceive;
/**
 * 
 * GameObserver代理实现类
 * @author With You
 *
 */
public class GameReceiveImp implements GameReceive {
	
	/**
	 *  The people who choose PP Game;
	 */
	public final static List<PPGameControl> PPGAME_ON = new ArrayList<PPGameControl>();
	
	/**
	 *  The people who choose traditional
	 */
	public final static List<TRAGameControl> TRAGAME_ON = new ArrayList<TRAGameControl>();
	
	private final static GameReceiveImp imp = new GameReceiveImp();
	
	private GameReceiveImp() {}
	
	public static GameReceive getReceiveIns(){
		return (GameReceive)imp;
	}
	
	@Override
	/**
	 * 请严格按照格式： Action1 + space + Action2 + space + Action3 ******
	 */
	public void acceptActionTra(int action , int indexOfGame) throws RemoteException, GameNotFoundException {
		
		Iterator<TRAGameControl> iterator = TRAGAME_ON.iterator();
		TRAGameControl control  = null;
		while(iterator.hasNext()){
			TRAGameControl each = iterator.next();
			if(each.GAME_ID == indexOfGame){
				control = each;
				break;
			}
		}
		if(control != null){
			new Thread(new TraHandleRequest(action, control)).run();
		}else{
			throw new GameNotFoundException();
		}
		
	}
	/**
	 * 
	 * Create a new thread to handle the request
	 * 
	 * @author With You
	 *
	 */
	private class TraHandleRequest implements Runnable{
		private Control control;
		private int actionToTake;
		public TraHandleRequest(int action , Control control) {
			this.control = control;
			this.actionToTake = action;
		}
		
		@Override
		public void run() {
			this.control.handleAction(actionToTake);
		}
		
	}

	@Override
	public void acceptActionProp(int action , int indexOfGame) throws RemoteException, GameNotFoundException {
		
		Iterator<PPGameControl> iterator = PPGAME_ON.iterator();
		PPGameControl control = null;
		while(iterator.hasNext()){
			PPGameControl each = iterator.next();
			if(each.GAME_ID == indexOfGame){
				control = each;
				break;
			}
		}
		if(control != null){
			new Thread(new PropHandleRequest(action, control)).run();
		}else{
			throw new GameNotFoundException();
		}
	}
	/**
	 * 
	 * Action Handle Thread for Props pattern .
	 * 
	 * @author Away
	 *
	 */
	private class PropHandleRequest implements Runnable{
		
		private Control control;
		private int actionToTake;
		/**
		 * 
		 */
		public PropHandleRequest(int action , Control control) {
			this.actionToTake = action;
			this.control = control;
		}
		/* (non-Javadoc)
		 * @see java.lang.Runnable#run()
		 */
		@Override
		public void run() {
			this.control.handleAction(actionToTake);
		}
		
		
	}

	@Override
	public void acceptTRAActionFinishedSign(int indexOfGame) throws RemoteException, GameNotFoundException {
		Iterator<TRAGameControl> iterator = TRAGAME_ON.iterator();
		TRAGameControl control = null;
		while(iterator.hasNext()){
			TRAGameControl each = iterator.next();
			if(each.GAME_ID == indexOfGame){
				control = each;
				break;
			}
		}
		if(control != null){
			new Thread(new ActionSign(control)).run();;
		}else{
			throw new GameNotFoundException();
		}
	}
	
	

	@Override
	public void acceptPPActionFinishedSign(int indexOfGame) throws RemoteException, GameNotFoundException {
		Iterator<PPGameControl> iterator = PPGAME_ON.iterator();
		PPGameControl control = null;
		while(iterator.hasNext()){
			PPGameControl each = iterator.next();
			if(each.GAME_ID == indexOfGame){
				control = each;
				break;
			}
		}
		if(control != null){
			new Thread(new ActionSign(control)).run();
		}else{
			throw new GameNotFoundException();
		}
	}
	
	private class ActionSign implements Runnable{
		private Control control;
		public ActionSign(Control control) {
			this.control = control;
		}
		
		@Override
		public void run() {
			this.control.changeCharacter();
		}
	}
	
}
