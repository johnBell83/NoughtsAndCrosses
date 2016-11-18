package my.janscop.game.utils;

import java.util.ArrayList;
import java.util.List;

public class Plan implements Cloneable{

	private String[][] planMatrix;
	private String nextTurn;
	
	private Plan(){		
	}
	
	public Plan(boolean aiStart, int size){
		this.planMatrix = new String[size][size];
		this.nextTurn = aiStart ? "X" : "O";			
	}
	
	public boolean makeTurn(Box box){
		return makeTurn(box.getI(), box.getJ());
	}
	
	public boolean makeTurn(int i, int j){
		if(i >= planMatrix.length || j >= planMatrix.length){
			return false;
		}
		if(planMatrix[i][j] != null){
			return false;
		}
		if(!isPossible2MakeTurn()){
			return false;
		}
		planMatrix[i][j] = nextTurn;
		nextTurn = nextTurn.equals("X") ? "O" : "X";
		return true;
	}
	
	public List<Box> getFreeBoxes(){
		List<Box> retList = new ArrayList<Box>();
		for(int i = 0; i<planMatrix.length; i++){
			for(int j = 0; j<planMatrix.length; j++){
				if(planMatrix[i][j] == null){
					retList.add(new Box(i, j));
				}
			}
		}		
		return retList;
	}
	
	public boolean isPossible2MakeTurn(){
		if(getFreeBoxes().size() > 0){
			return true;
		}
		return false;
	}	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(" ");
		for(int i = 0; i<planMatrix.length; i++){
			sb.append(" ");
			sb.append(i%10);			
		}
		sb.append("\n");
		for(int i = 0; i<planMatrix.length; i++){
			sb.append(i%10);
			sb.append("|");
			for(int j = 0; j<planMatrix[i].length; j++){
				sb.append(planMatrix[i][j] == null ? " " : planMatrix[i][j]);
				sb.append("|");
			}
			sb.append("\n");						
		}
		return sb.toString();
	}
	
	public void printPlan(){
		System.out.println(toString());
	}

	public String[][] getPlanMatrix() {		
		return planMatrix;
	}		
	
	public String getNextTurn() {
		return nextTurn;
	}

	public Plan clone(){
		Plan newPlan = new Plan();
		newPlan.planMatrix = new String[this.planMatrix.length][this.planMatrix.length];
		for(int i=0; i<this.planMatrix.length; i++){
			for(int j=0; j<this.planMatrix.length; j++){
				newPlan.planMatrix[i][j]=this.planMatrix[i][j];
			}	
		}	  
		
		newPlan.nextTurn = this.nextTurn;
		return newPlan;				
	}
}

