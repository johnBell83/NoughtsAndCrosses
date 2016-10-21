package my.janscop.game.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.janscop.game.utils.Box;
import my.janscop.game.utils.Plan;

public class AiProcessor {
	
	private int planSize;

	public AiProcessor(int planSize){
		this.planSize = planSize;
	}
	
	public Box findNewTurn(Plan plan){
		PlanChecker checkerP = new PlanChecker(plan.getPlanMatrix(), "X", "O");
		PlanChecker checkerO = new PlanChecker(plan.getPlanMatrix(), "O", "X");
		checkerP.evaluateMatrix();
		checkerO.evaluateMatrix();
		
		int maxEvaluationP = 0;
		int maxEvaluationO = 0;
		for(int i = 0; i<planSize; i++){
			for(int j = 0; j<planSize; j++){
				if(checkerP.getEvaluationMatrix()[i][j].intValue() == 9){
					continue;
				}
				if(checkerP.getEvaluationMatrix()[i][j].intValue() > maxEvaluationP){
					maxEvaluationP = checkerP.getEvaluationMatrix()[i][j].intValue();
				}
				
				if(checkerO.getEvaluationMatrix()[i][j].intValue() > maxEvaluationO){
					maxEvaluationO = checkerO.getEvaluationMatrix()[i][j].intValue();
				}
			}
		}
		
		List<Box> listOfBoxes = null;
		
		if(maxEvaluationP >= maxEvaluationO){
			listOfBoxes = getListOfBoxes(checkerP.getEvaluationMatrix(), maxEvaluationP);
		}else{
			listOfBoxes = getListOfBoxes(checkerO.getEvaluationMatrix(), maxEvaluationO);
		}
		
		Random r = new Random();		
		Box selectedBox = listOfBoxes.get(r.nextInt(listOfBoxes.size()));
		return selectedBox;		
	}
	
	private List<Box> getListOfBoxes(Integer[][] evaluationMatrix, int maxEvaluation){
		List<Box> listOfBoxes = new ArrayList<Box>();
		
		for(int i = 0; i<planSize; i++){
			for(int j = 0; j<planSize; j++){
				if(evaluationMatrix[i][j].intValue() == maxEvaluation){
					listOfBoxes.add(new Box(i, j));
				}
			}
		}	
		
		return listOfBoxes;
	}
	
}
