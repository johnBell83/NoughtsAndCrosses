package my.janscop.game.utils;

public class Box {

	int i;
	int j;	
	int evaluation;
	public Box(int i, int j, int evaluation) {
		super();
		this.j = j;
		this.i = i;
		this.evaluation = evaluation;		
	}
	@Override
	public String toString() {
		return "Box [i=" + i + ", j=" + j + "]";
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}	
		
	
}
