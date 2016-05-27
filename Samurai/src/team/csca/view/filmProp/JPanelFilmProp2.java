package team.csca.view.filmProp;

/**
 * 第二关只允许使用两个武士
 * 于是一开始就把第一个武士设置为补课行动
 * @author Water
 *
 */
public class JPanelFilmProp2 extends JPanelFilmProp1{

	@Override
	public void initRecoverRound(){
		recoverRound[0] = maxRecoverRound;
		for (int i = 1; i < recoverRound.length; i++) {
			recoverRound[i] = 0;
		}
	}
}
