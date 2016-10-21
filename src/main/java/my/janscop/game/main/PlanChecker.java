package my.janscop.game.main;

public class PlanChecker {
	
	private String[][]plan; 
	private String player;
	private String playerOponent;
	
	private Integer[][]evaluationMatrix; 
	
	
	/*public static void main(String[] args){
		String[][] plan = new String[20][20];
		
		// 01234567890123456789
		//0--------------------
		//1--------------------
		//2-----X--------------
		//3-----X-X------------
		//4-----XX0------------
		//5------0X------------
		//6--------------------
		
		plan[2][5] = "X";
		plan[3][5] = "X";
		plan[4][5] = "X";
		plan[4][6] = "X";
		plan[5][6] = "O";
		plan[3][7] = "X";
		plan[4][7] = "O";
		plan[5][7] = "X";
		
		PlanChecker pchPlayer = new PlanChecker(plan, "X", "O");
		PlanChecker pchOponent = new PlanChecker(plan, "O", "X");
		
		pchPlayer.evaluateMatrix();
		pchPlayer.printEvaluationMatrix();
		System.out.println();
		pchOponent.evaluateMatrix();
		pchOponent.printEvaluationMatrix();
	}*/
	
	public PlanChecker(String[][] plan, String player, String playerOponent) {
		super();
		this.plan = plan;
		this.player = player;
		this.playerOponent = playerOponent;
		
		evaluationMatrix = new Integer[plan.length][plan.length];
	}	
	
	public Integer[][] getEvaluationMatrix() {
		return evaluationMatrix;
	}

	public void evaluateMatrix(){
		for(int i = 0; i<plan.length; i++){
			for(int j = 0; j<plan.length; j++){
				if(player.equals(plan[i][j]) || playerOponent.equals(plan[i][j])){
					evaluationMatrix[i][j] = 9;
					continue;
				}
				checkLeft(i, j);
				checkDown(i, j);
				checkDiagonalDownRight(i, j);
				checkDiagonalUpRight(i, j);
			}
		}
	}
	
	public void printEvaluationMatrix(){
		for(int i = 0; i<evaluationMatrix.length; i++){
			System.out.print("|");
			for(int j = 0; j<evaluationMatrix.length; j++){
				System.out.print(evaluationMatrix[i][j]);
				System.out.print("|");
			}
			System.out.println();
		}	
		
	}
	
	public void checkLeft(int i, int j){				
		for(int minus = j-4; minus <= j; minus++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame < 5; frame++){
				int item = minus + frame;
				if((item < 0) || (item >= plan.length)){
					continue;
				}				
				if(player.equals(plan[i][item])){
					numberOfFilledItems++;
				}
								
				if(playerOponent.equals(plan[i][item])){
					// ----->  CH  XX0X
					if(item > j){
						break;
					}
					// -----> X0XX CH
					else{
						numberOfFilledItems = 0;
						continue;
					}
				}
 
			}
			evaluateMatrixItem(i, j, numberOfFilledItems);	
		}		
	}
	
	public void checkDown(int i, int j){
		for(int minus = i-4; minus <= i; minus++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame < 5; frame++){
				int item = minus + frame;
				if((item < 0) || (item >= plan.length)){
					continue;
				}				
				if(player.equals(plan[item][j])){
					numberOfFilledItems++;
				}
								
				if(playerOponent.equals(plan[item][j])){
					// ----->  CH  XX0X
					if(item > i){
						break;
					}
					// -----> X0XX CH
					else{
						numberOfFilledItems = 0;
						continue;
					}
				}
 
			}
			evaluateMatrixItem(i, j, numberOfFilledItems);		
		}
	}
	
	
	public void checkDiagonalDownRight(int i, int j){
		int minusI = i-4 - 1;
		int minusJ = j-4 - 1;
		while(minusJ < j && minusI < i){
			minusI++;
			minusJ++;
		//for(int minusJ = j-4; minusJ <= j; minusJ++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame < 5; frame++){
				int itemJ = minusJ + frame;
				int itemI = minusI + frame;
				if((itemJ < 0) || (itemJ >= plan.length) || (itemI < 0) || (itemI >= plan.length)){
					continue;
				}				
				if(player.equals(plan[itemI][itemJ])){
					numberOfFilledItems++;
				}
								
				if(playerOponent.equals(plan[itemI][itemJ])){
					// ----->  CH  XX0X
					if(itemJ > j){
						break;
					}
					// -----> X0XX CH
					else{
						numberOfFilledItems = 0;
						continue;
					}
				}
 
			}
			evaluateMatrixItem(i, j, numberOfFilledItems);	
		}		
	}	
	
	public void checkDiagonalUpRight(int i, int j){
		int minusI = i+4 + 1;
		int minusJ = j-4 - 1;
		while(minusJ < j && minusI > i){
			minusI--;
			minusJ++;
		//for(int minusJ = j-4; minusJ <= j; minusJ++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame < 5; frame++){
				int itemJ = minusJ + frame;
				int itemI = minusI - frame;
				if((itemJ < 0) || (itemJ >= plan.length) || (itemI < 0) || (itemI >= plan.length)){
					continue;
				}				
				if(player.equals(plan[itemI][itemJ])){
					numberOfFilledItems++;
				}
								
				if(playerOponent.equals(plan[itemI][itemJ])){
					// ----->  CH  XX0X
					if(itemJ > j){
						break;
					}
					// -----> X0XX CH
					else{
						numberOfFilledItems = 0;
						continue;
					}
				}
 
			}			
			evaluateMatrixItem(i, j, numberOfFilledItems);			
		}
	}
	
	public void evaluateMatrixItem(int i, int j, int numberOfFilledItems){
		if(evaluationMatrix[i][j] == null){
			evaluationMatrix[i][j] = numberOfFilledItems;
		}else if((numberOfFilledItems == 3) &&  (evaluationMatrix[i][j].intValue() == 3)){
			evaluationMatrix[i][j] = 4;		
		}else if(evaluationMatrix[i][j] < numberOfFilledItems){
			evaluationMatrix[i][j] = numberOfFilledItems;
		}
	}	

}
