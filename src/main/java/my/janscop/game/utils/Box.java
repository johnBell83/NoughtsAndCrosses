package my.janscop.game.utils;

public class Box {

	private int i;
	private int j;	
	/*int evaluation;
	BlockAttackEnum blockAttack;
	public Box(int i, int j, int evaluation, BlockAttackEnum blockAttack) {
		super();
		this.j = j;
		this.i = i;
		this.evaluation = evaluation;
		this.blockAttack = blockAttack;
	}*/
	
	public Box(int i, int j) {
		super();
		this.j = j;
		this.i = i;		
	}
	
	@Override
	public String toString() {
		return "Box [i=" + i + ", j=" + j + "]";
	}
	public int getI() {
		return i;
	}	
	public int getJ() {
		return j;
	}
	
	/*public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	public BlockAttackEnum getBlockAttack() {
		return blockAttack;
	}*/	
	
}
