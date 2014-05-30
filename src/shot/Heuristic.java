package shot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Heuristic {

	Logic game;
	Stack<Map<Integer,Integer>> solution; 
	List<Map<Integer,Integer>> playslist;
	boolean end=false;

	public Heuristic(){		

		game = new Logic();
		
		game.CountLines();		
		

		solution = new Stack<Map<Integer,Integer>>();		

		checkplay(game);
		
		playslist = new ArrayList<Map<Integer,Integer>>(solution);	
		
		for(int i=0; i<playslist.size();i++){
			System.out.println(playslist.get(i).toString());
		}
		
		System.out.printf("%d\n",game.movepriority.size());	
		

	}



	public static void main(String[] args) {

		Heuristic bot = new Heuristic();
		//bot.checkplay(bot.game);

	}

	public boolean checkplay(Logic game){

		Map<Integer,Integer> play = new HashMap<Integer, Integer>();

		for(int i=0; i<4;i++){

			if(game.checknextball(i, 0)){
				play.clear();
				play.put(i, 0);
				game.moveBallbot(i, 0);
				solution.add(play);		

				if(!game.GameOver()){
								
					checkplay(game);
					if(end){
						return true;
					}
					solution.pop();				
					
					
				}else{
					
					end= true;
					return true;
				}
			}
			if(game.checknextball(i, 1)){
				play.clear();
				play.put(i, 1);
				game.moveBallbot(i, 1);
				solution.add(play);
				

				if(!game.GameOver()){
					//game.moveBallbot(i, 1);
											
					checkplay(game);
					if(end){
						return true;
					}
					solution.pop();
					
					
				}else{
					
					end= true;
					return true;
				}
			}
			if(game.checknextball(i, 2)){
				play.clear();
				play.put(i, 2);
				game.moveBallbot(i, 2);
				solution.add(play);
				
				

				if(!game.GameOver()){
					//game.moveBallbot(i, 2);
										
					checkplay(game);
					if(end){
						return true;
					}
					solution.pop();
					
				}else{
					
					end= true;
					return true;
				}
			}
			if(game.checknextball(i, 3)){
				play.clear();
				play.put(i, 3);
				game.moveBallbot(i, 3);
				solution.add(play);
				

				if(!game.GameOver()){
					game.moveBallbot(i, 3);
									
					checkplay(game);
					if(end){
						return true;
					}
					solution.pop();
					
				}else{
					
					end= true;
					return true;
				}
			}

		}

		return false;

	}


}
