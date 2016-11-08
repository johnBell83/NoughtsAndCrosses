package my.janscop.game.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import my.janscop.game.utils.Box;
import my.janscop.game.utils.Plan;

public class Game {	
	
	public static void  main(String args[]) throws IOException{		
		
		Plan plan = new Plan(true, 20);
		AiProcessor processor = new AiProcessor(20);
		plan.makeTurn(10, 10);
		plan.printPlan();
		
		String inputString = null; 				
		try{			
			while(!"finish".equals(inputString)){
				System.out.println("enter line;row");
				InputStreamReader in=new InputStreamReader(System.in);
			    BufferedReader br = new BufferedReader(in);
			    inputString = br.readLine();			   						    
				if("finish".equals(inputString)){
					continue;
				}
				
				Box inputBox = parseInput(inputString);
				plan.makeTurn(inputBox);
				plan.printPlan();
				
				inputBox = processor.findNewTurn(plan);
				plan.makeTurn(inputBox);
				plan.printPlan();
			}
			
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("finish");

    }
	
	public static Box parseInput(String input){		
		String[] inputValues = input.split(";");
		Box ret = new Box(Integer.parseInt(inputValues[0]), Integer.parseInt(inputValues[1]), -1);		
		return ret;
	}
}
