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

		Map<Map<String, Integer>,Integer> lines= game.CountLines();

		checkplay(game,lines);

		playslist = new ArrayList<Map<Integer,Integer>>(solution);	

		for(int i=0; i<playslist.size();i++){
			System.out.println(playslist.get(i).toString());
		}
	}

	public static void main(String[] args) {

		Heuristic bot = new Heuristic();

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


		}

		Iterator itt = line.entrySet().iterator();
		Map.Entry finalpair = (Map.Entry)itt.next();

		Queue<Integer> playqueue = new LinkedList<Integer>();

		if(finalpair.getKey()=="x"){
			for(int i=0; i< game.balls.length;i++){
				if(game.balls[i].getX()==(Integer)finalpair.getValue()){
					playqueue.add(i);

				}
			}

		}else{
			for(int i=0; i< game.balls.length;i++){
				if(game.balls[i].getY()==(Integer)finalpair.getValue()){
					playqueue.add(i);
				}
			}
		}		
		for(int i=0; i< game.balls.length;i++){
			if(!playqueue.contains(i)){
				playqueue.add(i);
			}
		}

		Map<Integer,Integer> play = new HashMap<Integer, Integer>();	

		while(!playqueue.isEmpty()){

			int i= playqueue.remove();

			if(game.checknextball(i, 0)){
				play.clear();
				play.put(i, 0);
				game.moveBallbot(i, 0);
				solution.add(play);		

				if(!game.GameOver()){
					Map<Map<String, Integer>,Integer> heuristic2 = game.CountLines();
					Iterator it2 = heuristic2.entrySet().iterator();
					System.out.println("Iterator");
					while (it2.hasNext()) {
						Map.Entry pairs = (Map.Entry)it2.next();
						System.out.println(pairs.getKey() + " = " + pairs.getValue());
					}	
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
