package my.janscop.game.main;

public class PlanChecker {
	
	private String[][]plan; 
	private String player;
	private String playerOponent;
	
	private Integer[][]evaluationMatrix = null; 	
	
	private static final int FRAME_LENGTH = 4; 
	
	
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
	
	public static void main(String[] args){
		String[][] plan = new String[20][20];
		
		// 01234567890123456789
		//0--------------------
		//1-----X--------------
		//2-----X--------------
		//3-----X-X------------
		//4-----XX0------------
		//5------0X------------
		//6--------------------
		plan[1][5] = "X";
		plan[5][5] = "X";
		
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
		pchPlayer.printMatrix(/*pchPlayer.getEvaluationMatrix()*/);
		System.out.println();
		//System.out.println("my wins: "+pchPlayer.checkWins());
		System.out.println();
		pchOponent.evaluateMatrix();		
		pchOponent.printMatrix(/*pchOponent.getEvaluationMatrix()*/);
		//System.out.println("oponent wins: "+pchOponent.checkWins());
	}
	
	public PlanChecker(String[][] plan, String player, String playerOponent) {
		super();
		this.plan = plan;
		this.player = player;
		this.playerOponent = playerOponent;
	}	
	
	public Integer[][] getEvaluationMatrix() {
		return evaluationMatrix;
	}	
	
	public void evaluateMatrix(){
		evaluationMatrix = new Integer[plan.length][plan.length];
		checkData(evaluationMatrix, FRAME_LENGTH);
	}	
	
	public void checkData(Integer[][] matrix, int frameLength){				
		for(int i = 0; i<plan.length; i++){
			for(int j = 0; j<plan.length; j++){
				if(player.equals(plan[i][j]) || playerOponent.equals(plan[i][j])){
					matrix[i][j] = 9;					
					continue;
				}
				checkLeft(i, j, matrix, frameLength);
				checkDown(i, j, matrix, frameLength);
				checkDiagonalDownRight(i, j, matrix, frameLength);
				checkDiagonalUpRight(i, j, matrix, frameLength);
			}
		}		
	}
	
	public void printMatrix(/*Integer[][] matrix*/){
		
		for(int i = 0; i<evaluationMatrix.length; i++){
			System.out.print("|");
			for(int j = 0; j<evaluationMatrix.length; j++){
				System.out.print(evaluationMatrix[i][j]);
				System.out.print("|");
			}
			System.out.println();
		}			
	}	
	
	public void checkLeft(int i, int j, Integer[][]matrix, int frameLength){				
		for(int minus = j-frameLength; minus <= j; minus++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame <= frameLength; frame++){
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
			evaluateMatrixItem(i, j, numberOfFilledItems, matrix);	
		}		
	}
	
	public void checkDown(int i, int j, Integer[][]matrix, int frameLength){
		for(int minus = i-frameLength; minus <= i; minus++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame <= frameLength; frame++){
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
			evaluateMatrixItem(i, j, numberOfFilledItems, matrix);		
		}
	}
	
	
	public void checkDiagonalDownRight(int i, int j, Integer[][]matrix, int frameLength){
		int minusI = i-frameLength - 1;
		int minusJ = j-frameLength - 1;
		while(minusJ < j && minusI < i){
			minusI++;
			minusJ++;
		//for(int minusJ = j-4; minusJ <= j; minusJ++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame <= frameLength; frame++){
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
			evaluateMatrixItem(i, j, numberOfFilledItems, matrix);	
		}		
	}	
	
	public void checkDiagonalUpRight(int i, int j, Integer[][]matrix, int frameLength){
		int minusI = i+4 + 1;
		int minusJ = j-4 - 1;
		while(minusJ < j && minusI > i){
			minusI--;
			minusJ++;
		//for(int minusJ = j-4; minusJ <= j; minusJ++){			
			int numberOfFilledItems = 0;
			for(int frame = 0; frame <= frameLength; frame++){
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
			evaluateMatrixItem(i, j, numberOfFilledItems, matrix);			
		}
	}
	
	public void evaluateMatrixItem(int i, int j, int numberOfFilledItems, Integer[][]matrix){
		if(matrix[i][j] == null){
			matrix[i][j] = numberOfFilledItems;
		}else if((numberOfFilledItems == 3) &&  (matrix[i][j].intValue() == 3)){
			//TODO check it
			//matrix[i][j] = 3;		
		}else if(matrix[i][j] < numberOfFilledItems){
			matrix[i][j] = numberOfFilledItems;
		}
	}
	
	

}
