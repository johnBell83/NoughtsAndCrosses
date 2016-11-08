package my.janscop.game.utils;

public class PlanUtil {
	
	public static int checkWins(String[][]plan, String player){
		int wins = 0;
		for(int i = 0; i<plan.length; i++){
			int countRow = 0;
			int countColumn = 0;
			
			int diagonalDownRight1 = 0;
			int diagonalDownRight2 = 0;
			
			int diagonalUpRight1 = 0;
			int diagonalUpRight2 = 0;
			
			for(int j = 0; j<plan.length; j++){
				countRow = check(i, j, countRow, plan, player);
				if(countRow == 5){
					wins++;
					countRow = 0;
				}
				countColumn = check(j, i, countColumn, plan, player);
				if(countColumn == 5){
					wins++;
					countColumn = 0;
				}
				
			
				diagonalDownRight1 = check(i+j, j, diagonalDownRight1, plan, player);
				if(diagonalDownRight1 == 5){
					wins++;
					diagonalDownRight1 = 0;
				}
				
				diagonalDownRight2 = check((i + j - plan.length + 1), j, diagonalDownRight2, plan, player);
				if(diagonalDownRight2 == 5){
					wins++;
					diagonalDownRight2 = 0;
				}
			
				
				diagonalUpRight1 = check(plan.length-1-i-j, j, diagonalUpRight1, plan, player);
				if(diagonalUpRight1 == 5){
					wins++;
					diagonalUpRight1 = 0;
				}
				
				diagonalUpRight2 = check(((2*(plan.length-1))-1)-i-j, j, diagonalUpRight2, plan, player);
				if(diagonalUpRight2 == 5){
					wins++;
					diagonalUpRight2 = 0;
				}
			}						
		}
		
		return wins;
	}
	
	private static int check(int row, int column, int count, String[][]plan, String player){
		if((row < 0) || (row >= plan.length)){
			return count;
		}		
		
		if(player.equals(plan[row][column])){
			count++;
		}else{
			count = 0;
		}
		return count;
			
	}
}
