package my.janscop.game.utils;

public class PlanUtil {

	public static int checkWin(String[][] plan, int countOfBoxes, String userSign, Box lastTurn){		
		int win = 0;
		if(checkLine(plan, countOfBoxes, userSign, lastTurn)){
			win++;
		}
		if(checkRow(plan, countOfBoxes, userSign, lastTurn)){
			win++;
		}
		if(checkDiagonal2DownRight(plan, countOfBoxes, userSign, lastTurn)){
			win++;
		}
		if(checkDiagonal2UpRight(plan, countOfBoxes, userSign, lastTurn)){
			win++;
		}
		return win;
	}
	
	
	private static boolean checkLine(String[][] plan, int countOfBoxes, String userSign, Box lastTurn){
		boolean isFinished = false;
		int j = lastTurn.j - 1;
		int countOfObject = 1;
		while(!isFinished){
			if(j < 0){
				isFinished = true;
				continue;
			}
			if(countOfObject == countOfBoxes){
				isFinished = true;
				continue;
			}
			if(userSign.equals(plan[lastTurn.i][j])){
				countOfObject++;
			}else {
				isFinished = true;
			}
			j--;
		}
		
		if(countOfObject < countOfBoxes){
			isFinished = false;
			j = lastTurn.j + 1;
			while(!isFinished){
				if(j >= plan.length){
					isFinished = true;
					continue;
				}
				if(countOfObject == countOfBoxes){
					isFinished = true;
					continue;
				}
				if(userSign.equals(plan[lastTurn.i][j])){
					countOfObject++;
				}else {
					isFinished = true;
				}
				j++;
			}
		}
		
		if(countOfObject >= countOfBoxes){
			return true;
		}		
		return false;
	}
	
	private static boolean checkRow(String[][] plan, int countOfBoxes, String userSign, Box lastTurn){
		boolean isFinished = false;
		int i = lastTurn.i - 1;
		int countOfObject = 1;
		while(!isFinished){
			if(i < 0){
				isFinished = true;
				continue;
			}
			if(countOfObject == countOfBoxes){
				isFinished = true;
				continue;
			}
			if(userSign.equals(plan[i][lastTurn.j])){
				countOfObject++;
			}else {
				isFinished = true;
			}
			i--;
		}
		
		if(countOfObject < countOfBoxes){
			isFinished = false;
			i = lastTurn.i + 1;
			while(!isFinished){
				if(i >= plan.length){
					isFinished = true;
					continue;
				}
				if(countOfObject == countOfBoxes){
					isFinished = true;
					continue;
				}
				if(userSign.equals(plan[i][lastTurn.j])){
					countOfObject++;
				}else {
					isFinished = true;
				}
				i++;
			}
		}
		
		if(countOfObject >= countOfBoxes){
			return true;
		}		
		return false;
	}
	
	private static boolean checkDiagonal2DownRight(String[][] plan, int countOfBoxes, String userSign, Box lastTurn){
		boolean isFinished = false;
		int i = lastTurn.i - 1;
		int j = lastTurn.j - 1;
		int countOfObject = 1;
		while(!isFinished){
			if(i < 0 || j < 0){
				isFinished = true;
				continue;
			}
			if(countOfObject == countOfBoxes){
				isFinished = true;
				continue;
			}
			if(userSign.equals(plan[i][j])){
				countOfObject++;
			}else {
				isFinished = true;
			}
			i--;
			j--;
		}
		
		if(countOfObject < countOfBoxes){
			isFinished = false;
			i = lastTurn.i + 1;
			j = lastTurn.j + 1;
			while(!isFinished){
				if(i >= plan.length || j >= plan.length){
					isFinished = true;
					continue;
				}
				if(countOfObject == countOfBoxes){
					isFinished = true;
					continue;
				}
				if(userSign.equals(plan[i][j])){
					countOfObject++;
				}else {
					isFinished = true;
				}
				i++;
				j++;
			}
		}
		
		if(countOfObject >= countOfBoxes){
			return true;
		}		
		return false;
	}
	
	private static boolean checkDiagonal2UpRight(String[][] plan, int countOfBoxes, String userSign, Box lastTurn){
		boolean isFinished = false;
		int i = lastTurn.i + 1;
		int j = lastTurn.j - 1;
		int countOfObject = 1;
		while(!isFinished){
			if(i >= plan.length || j < 0){
				isFinished = true;
				continue;
			}
			if(countOfObject == countOfBoxes){
				isFinished = true;
				continue;
			}
			if(userSign.equals(plan[i][j])){
				countOfObject++;
			}else {
				isFinished = true;
			}
			i++;
			j--;
		}
		
		if(countOfObject < countOfBoxes){
			isFinished = false;
			i = lastTurn.i - 1;
			j = lastTurn.j + 1;
			while(!isFinished){
				if(i < 0 || j >= plan.length){
					isFinished = true;
					continue;
				}
				if(countOfObject == countOfBoxes){
					isFinished = true;
					continue;
				}
				if(userSign.equals(plan[i][j])){
					countOfObject++;
				}else {
					isFinished = true;
				}
				i--;
				j++;
			}
		}
		
		if(countOfObject >= countOfBoxes){
			return true;
		}		
		return false;
	}
}
