package my.janscop.game.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.janscop.game.common.BlockAttackEnum;
import my.janscop.game.utils.Box;
import my.janscop.game.utils.EvaluatedTurn;
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
		EvaluatedTurn turns = getPotentionalListOfBoxes(plan, true);
		
		if(turns.getEvaluation() == 3){
			//in case of XXX or OOO try to find best choise
			Box choosenBox = makeChoise(plan, turns.getListOfBoxes());
			return choosenBox;
		}else{
			//in case of X, XX or XXXX (O, OO or OOOO) random choise from suitable boxes
			Box choosenBox = turns.getListOfBoxes().get(r.nextInt(turns.getListOfBoxes().size()));
			return choosenBox;
		}
	}
	
	private EvaluatedTurn getPotentionalListOfBoxes(Plan plan, boolean equalsOrHigher){
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
				
		EvaluatedTurn evaluatedTurn = null;				
		if((equalsOrHigher && (maxEvaluationP >= maxEvaluationO)) || (!equalsOrHigher && (maxEvaluationP > maxEvaluationO))){
			evaluatedTurn = new EvaluatedTurn(getListOfBoxes(checkerP.getEvaluationMatrix(), maxEvaluationP), maxEvaluationP, BlockAttackEnum.ATTACK);					
		}else{
			evaluatedTurn = new EvaluatedTurn(getListOfBoxes(checkerO.getEvaluationMatrix(), maxEvaluationO), maxEvaluationO, BlockAttackEnum.BLOCK);
		}
		
		return evaluatedTurn;
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
	
	public Box makeChoise(Plan plan, List<Box> listOfBoxes){
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
		
		int myWins = evaluateWin(plan, "X");
		if(myWins != 0){
			return myWins;
		}
		
		Plan newPlan = plan.clone();
		newPlan.makeTurn(box);
		
		EvaluatedTurn turns = getPotentionalListOfBoxes(newPlan, false);
		
		//if after my turn I have to blok case like OOOO
		if((deep == 0) && (turns.getEvaluation() > 3) && BlockAttackEnum.BLOCK.equals(turns.getBlockAttack())) {	
			return -1;				
		}
		
		int maxEval = 0;		
		for(Box box2 : turns.getListOfBoxes()) {
			int eval = makeOponenPotentionalTurn(newPlan, box2, deep-1);
			if(eval > maxEval){
				maxEval = eval;
			}
			
			if(eval < 0){
				return eval;
			}
			
		}		
		return maxEval;
	}
	
	private int makeOponenPotentionalTurn(Plan plan, Box box, int deep){				
		if(deep < 0){		
			return 0;
		}
		
		int myWins = evaluateWin(plan, "O");
		if(myWins != 0){
			return myWins;
		}
		
		Plan newPlan = plan.clone();
		newPlan.makeTurn(box);
										
		EvaluatedTurn turns = getPotentionalListOfBoxes(newPlan, true);
		
		int maxEval = 0;
		for(Box box2 : turns.getListOfBoxes()) {
			int eval = makeMyPotentionalTurn(newPlan, box2, deep-1);
			if(eval > maxEval){
				maxEval = eval;
			}
			
			//TODO check it
			if(eval < 0){
				return -1;
			}
		}
		return maxEval;
	}
	
	private int evaluateWin(Plan plan, String player){
		int numberOfWins = PlanUtil.checkWins(plan.getPlanMatrix(), player);
		int eval = 0;
		if(numberOfWins == 1){
			eval = 5;
		}else if(numberOfWins > 1){
			eval = 6;
		}
		if("O".equals(player)){
			return ((-1)*eval);
		}
		return eval;
	}
	
}
