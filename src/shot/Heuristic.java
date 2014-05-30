package shot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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


		solution = new Stack<Map<Integer,Integer>>();

		playslist = new ArrayList<Map<Integer,Integer>>(solution);		

		Map<Map<String, Integer>,Integer> lines= game.CountLines();
		System.out.println("AQUI");

		Iterator it = lines.entrySet().iterator();

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			System.out.println(pairs.getKey() + " = " + pairs.getValue());
			it.remove(); // avoids a ConcurrentModificationException
		}		

		checkplay(game,lines);

	}



	public static void main(String[] args) {

		Heuristic bot = new Heuristic();
		//bot.checkplay(bot.game);

	}

	public boolean checkplay(Logic game,Map<Map<String, Integer>,Integer> heuristic){

		Iterator it = heuristic.entrySet().iterator();
		Map<String, Integer> line=new HashMap<String, Integer>() ;
		int counter=0;

		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry)it.next();
			//System.out.println(pairs.getKey() + " = " + pairs.getValue());
			if((Integer)pairs.getValue() ==0 || (Integer)pairs.getValue()>counter){
				line = (Map<String, Integer>)pairs.getKey();
				counter = (Integer)pairs.getValue();
			}
			
			it.remove(); // avoids a ConcurrentModificationException
		}
		System.out.println("Linhas maximas");
		System.out.println(line.toString());
		System.out.println(counter);




		Map<Integer,Integer> play = new HashMap<Integer, Integer>();	


		for(int i=0; i<4;i++){


			if(game.checknextball(i, 0)){
				play.clear();
				play.put(i, 0);
				game.moveBallbot(i, 0);
				solution.add(play);		

				if(!game.GameOver()){
					Map<Map<String, Integer>,Integer> heuristic2 = game.CountLines();
					checkplay(game,heuristic2);
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

					Map<Map<String, Integer>,Integer> heuristic2 = game.CountLines();
					checkplay(game,heuristic2);
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

					Map<Map<String, Integer>,Integer> heuristic2 = game.CountLines();
					checkplay(game,heuristic2);
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

					Map<Map<String, Integer>,Integer> heuristic2 = game.CountLines();
					checkplay(game,heuristic2);
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
