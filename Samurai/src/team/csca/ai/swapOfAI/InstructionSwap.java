package team.csca.ai.swapOfAI;

import team.csca.ai.AIdata.GameInfo;
import team.csca.view.extend.JPanelGame;
import team.csca.view.pm.JPanelPM;


/**
 *
 * 
 * This class is the swap of AI for all the AI programs we write . 
 * 
 * @author With You
 *	
 */
public class InstructionSwap {
	
 	private GameInfo info;
 	private JPanelGame panel;
	
 	public InstructionSwap(GameInfo info , JPanelGame panel) {
		this.panel = panel;
		this.info = info;
	}
 	
	public void initAIField(int[] basicInfo , int[] homeX , int[] homeY){
		basicInfo[1] -= 3;
		dealWithCoorChange(homeX, homeY);
		info.initInfo(basicInfo, homeX, homeY,this);
	}
	
	private void dealWithCoorChange(int[] Xchange , int[] Ychange){
		//X and  Y reverse 
		for(int i = 0 ; i < Ychange.length ; i ++ ){
			if(Ychange[i] != -1){
				Ychange[i] = 14 - Ychange[i];
			}
		}
		
		for(int i = 0 ; i < 3 ; i ++){
			int tempOne = Xchange[i];
			int tempTwo = Ychange[i];
			Xchange[i] = Xchange[i + 3];
			Ychange[i] = Ychange[i + 3];
			Xchange[i + 3] = tempOne;
			Ychange[i + 3] = tempTwo;
		}
	}
	
	public void sendInfo(int[] basicInfo , int[] curXes , int[] curYes , int [] hidden , int[] occupy){
		//TODO change occupy to two dimensions
		int[][] field = new int[info.height][info.width];
		for(int i = 0 ; i < 15 ; i ++){
			for(int j = 0 ; j < 15 ; j ++){
				int block = occupy[15 * i + j];
				if(block == 9){
					field[14 - j][i] = block;
				}else if(block == -1){
					field[14 - j][i] = 8;
				}else if(block < 3){
					field[14 - j][i] = block + 3;
				}else{
					field[14 - j][i] = block - 3;
				}
			}
		}
		for(int i = 0 ; i < 3 ; i ++){
			int tempHide = hidden[i] ;
			hidden[i] = hidden[i + 3];
			hidden[i + 3] = tempHide;
		}
		dealWithCoorChange(curXes, curYes);
		info.readTurnInfo(basicInfo, curXes, curYes, hidden, field);
	}
	
	public void moveSwap(int direction){
		switch (direction) {
		case 5:
			this.panel.moveDown();
			break;
		case 6:
			this.panel.moveRight();
			break;
		case 7:
			this.panel.moveUp();
			break;
		case 8:
			this.panel.moveLeft();
			break;
		default:
			break;
		}
	}

	public void occupySwap(int direction){
		switch (direction) {
		case 1:
			this.panel.attackDown();
			break;
		case 2:
			this.panel.attackRight();
			break;	
		case 3:
			this.panel.attackUp();
			break;
		case 4:
			this.panel.attackLeft();
			break;
		default:
			break;
		}
	}
	
	public void hideSwap(){
		this.panel.hideMe();
	}
	
	public void showSwap(){
		this.panel.showMe();
	}
	
}
