package my.janscop.game.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.janscop.game.utils.Box;
import my.janscop.game.utils.Plan;
import my.janscop.game.utils.PlanUtil;

public class AiProcessor {
	
	private int planSize;
	private Random r;

	public AiProcessor(int planSize){
		this.planSize = planSize;
		this.r = new Random();
	}
	
	public Box findNewTurn(Plan plan){		
		
		List<Box> listOfBoxes = getPotentionalListOfBoxes(plan);
		
		if(listOfBoxes.size() > 0){
			int evaluation = listOfBoxes.get(0).getEvaluation();
			
			if(evaluation == 3){
				Box choosenBox = makeChoise(plan, listOfBoxes);
				return choosenBox;
			}else{			
				Box choosenBox = listOfBoxes.get(r.nextInt(listOfBoxes.size()));
				return choosenBox;
			}
		}
		
		
		/*Random r = new Random();		
		Box selectedBox = listOfBoxes.get(r.nextInt(listOfBoxes.size()));
		return selectedBox;*/
		Box choosenBox = makeChoise(plan, listOfBoxes);
		return choosenBox;
	}
	
	private List<Box> getPotentionalListOfBoxes(Plan plan){
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
		
		return listOfBoxes;
	}
	
	private List<Box> getListOfBoxes(Integer[][] evaluationMatrix, int maxEvaluation){
		List<Box> listOfBoxes = new ArrayList<Box>();
		
		for(int i = 0; i<planSize; i++){
			for(int j = 0; j<planSize; j++){
				if(evaluationMatrix[i][j].intValue() == maxEvaluation){
					listOfBoxes.add(new Box(i, j, maxEvaluation));
				}
			}
		}	
		
		return listOfBoxes;
	}
	
	private Box makeChoise(Plan plan, List<Box> listOfBoxes){
		int maxEval = 0;
		Box choosenBox = null;
		for (Box box : listOfBoxes) {
			int eval = makeMyPotentionalTurn(plan, box, 2);
			if(eval >= maxEval){
				maxEval = eval;
				choosenBox = box;
			}
		}
		
		if(choosenBox == null){				
			choosenBox = listOfBoxes.get(r.nextInt(listOfBoxes.size()));				
		}
	
		return choosenBox;
	}
	
	private int makeMyPotentionalTurn(Plan plan, Box box, int deep){		
		if(deep < 0){			
			return 0;
		}
		Plan newPlan = plan.clone();
		newPlan.makeTurn(box);
		
		//PlanChecker checkerP = new PlanChecker(plan.getPlanMatrix(), "X", "O");					
		//int myWins = checkerP.checkWins();
		int myWins = PlanUtil.checkWins(plan.getPlanMatrix(), "X");
		if(myWins == 1){
			return 5;
		}else if(myWins > 1){
			return 6;
		}
		
		List<Box> listOfBoxes = getPotentionalListOfBoxes(newPlan);
		int maxEval = 0;		
		for(Box box2 : listOfBoxes) {
			int eval = makeOponenPotentionalTurn(newPlan, box2, deep--);
			if(eval > maxEval){
				maxEval = eval;
			}
		}		
		return maxEval;
	}
	
	private int  makeOponenPotentionalTurn(Plan plan, Box box, int deep){				
		if(deep < 0){		
			return 0;
		}
		Plan newPlan = plan.clone();
		newPlan.makeTurn(box);
								
		//PlanChecker checkerO = new PlanChecker(plan.getPlanMatrix(), "O", "X");
		//int myWins = checkerO.checkWins();
		int myWins = PlanUtil.checkWins(plan.getPlanMatrix(), "O");
		if(myWins == 1){
			return -5;
		}else if(myWins > 1){
			return -6;
		}
		
		List<Box> listOfBoxes = getPotentionalListOfBoxes(newPlan);
		int maxEval = 0;
		for(Box box2 : listOfBoxes) {
			int eval = makeMyPotentionalTurn(newPlan, box2, deep--);
			if(eval > maxEval){
				maxEval = eval;
			}
		}
		return maxEval;
	}
	
}
