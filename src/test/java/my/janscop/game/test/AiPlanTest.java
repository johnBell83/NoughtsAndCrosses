package my.janscop.game.test;

import my.janscop.game.main.AiProcessor;
import my.janscop.game.utils.Box;
import my.janscop.game.utils.Plan;

public class AiPlanTest {

	public static void main(String[]args){
		
		AiProcessor processor = new AiProcessor(10);
		Plan plan = new Plan(true, 10);
		
		// 0|1|2|3|4|5|6|7	
		//0				 		
		//1     						
		//2 	 X				
		//3      X		
		//4		 O X X					
		//5	   X O					
		//6		 O O O					
		//7							
		plan.makeTurn(new Box(2, 3, -1, null));//X
		plan.makeTurn(new Box(4, 3, -1, null));//O
		plan.makeTurn(new Box(3, 3, -1, null));//X
		plan.makeTurn(new Box(5, 3, -1, null));//O
		plan.makeTurn(new Box(4, 4, -1, null));//X
		plan.makeTurn(new Box(6, 3, -1, null));//O
		plan.makeTurn(new Box(5, 2, -1, null));//X
		plan.makeTurn(new Box(6, 4, -1, null));//O
		plan.makeTurn(new Box(4, 5, -1, null));//X
		plan.makeTurn(new Box(6, 5, -1, null));//O
		
		plan.printPlan();
		System.out.println();
		System.out.println("=============================");
		System.out.println();
		Box box = processor.findNewTurn(plan);
		
		System.out.println(box.toString());
		
		
	}
	
}
