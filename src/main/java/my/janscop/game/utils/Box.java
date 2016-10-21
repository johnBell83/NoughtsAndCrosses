package my.janscop.game.utils;

public class Box {

	int i;
	int j;	
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
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
		
	
}
