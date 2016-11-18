package my.janscop.game.utils;

import java.util.List;

import my.janscop.game.common.BlockAttackEnum;

public class EvaluatedTurn {

	private List<Box> listOfBoxes;
	private int evaluation;
	private BlockAttackEnum blockAttack;
	
	public EvaluatedTurn(List<Box> listOfBoxes, int evaluation, BlockAttackEnum blockAttack) {
		super();
		this.listOfBoxes = listOfBoxes;
		this.evaluation = evaluation;
		this.blockAttack = blockAttack;
	}
	public List<Box> getListOfBoxes() {
		return listOfBoxes;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public BlockAttackEnum getBlockAttack() {
		return blockAttack;
	}
}
