package my.janscop.game.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import my.janscop.game.utils.Box;
import my.janscop.game.utils.Plan;
import my.janscop.game.utils.PlanUtil;

public class Game {	
	
	public static void  main(String args[]) throws IOException{
		
		String inputString = null; 				
		try{
			InputStreamReader in=new InputStreamReader(System.in);
		    BufferedReader br = new BufferedReader(in);
			
			System.out.println("If you want to exit the game, type quit");
			System.out.println("Do you want to go first? Y/N");
			inputString = br.readLine();
			boolean first = false;
			if("Y".equals(inputString.toUpperCase())){
				first = true;
			}else if("N".equals(inputString.toUpperCase())){
				first = false;
			}else{
				System.out.println("Your input wasn't Y or N. Application is finished");
				return;
			}
			
			Plan plan = new Plan(!first, 20);
			AiProcessor processor = new AiProcessor(20);
			if(!first){
				plan.makeTurn(10, 10);
				plan.printPlan();				
			}
			
			while(!"quit".equals(inputString)){
				System.out.println("enter line;row");
				
			    inputString = br.readLine();			   						    
				if("quit".equals(inputString)){
					continue;
				}
				
				Box inputBox = parseInput(inputString);
				if(inputBox == null){
					System.out.println("Your input wasn't correct. Type it again");
					continue;
				}
				
				boolean success = plan.makeTurn(inputBox);
				if(!success){
					System.out.println("Your turn is not possible to execute. Try it again");
					continue;
				}
				plan.printPlan();
				if(PlanUtil.checkWins(plan.getPlanMatrix(), "O") > 0){
					System.out.println("Your win!");
					return;
				}
				
				inputBox = processor.findNewTurn(plan);
				plan.makeTurn(inputBox);
				plan.printPlan();
				if(PlanUtil.checkWins(plan.getPlanMatrix(), "X") > 0){
					System.out.println("I win!");
					return;
				}
				
			}
			
		    
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("Application is finished");

    }
	
	public static Box parseInput(String input){
		try{
			String[] inputValues = input.split(";");
			Box ret = new Box(Integer.parseInt(inputValues[0]), Integer.parseInt(inputValues[1]), -1);		
			return ret;
		}catch(Exception e){
			return null;
		}
	}
}
