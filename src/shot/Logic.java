package shot;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

import shot.Ball;
import shot.Board;

public class Logic {

	public Ball[] balls = new Ball[4];
	Random rand;

	Queue<Integer> movepriority;
	public int selectedball;


	public Object clone(){  
		try{  
			return super.clone();  
		}catch(Exception e){ 
			return null; 
		}
	}

	public Logic(){
		int x,y=0;


		movepriority =new LinkedList<Integer>();
		selectedball=-1;



		balls[0]=new Ball(100,100);		
		balls[1]=new Ball(300,100);			
		balls[2]=new Ball(400,100);			
		balls[3]=new Ball(300,600);	


	}




	//funï¿½ao principal
	public void Game(){ 


		Scanner reader = new Scanner(System.in);
		Scanner reader2 = new Scanner(System.in);

		for(int i=0;i<balls.length;i++){
			if(balls[i].dx>=800 || balls[i].dy>=800){
				balls[i].setActive(false);


				this.GameOver();

			}
		}		
	} 

	//funï¿½ao que move a primeira bola
	public int moveBall(int i,int dir ){

		// 1- sucesso
		// 2- esperar por jogada
		// 3- seleccionar bola
		// 0- jogada inválida

		if(!movepriority.isEmpty()){
			System.out.printf("Erro: Espere pelo fim da jogada\n");
			return 2;
		}

		if(selectedball ==-1){
			System.out.printf("Erro: Seleccione uma bola\n");
			return 3;
		}



		if(!checknextball(i,dir)){// verifica se pode mexer a bola

			System.out.printf("Erro: Jogada Invalida\n");

			return 0;
		}
		else
		{
			//o nextball modifica a posiï¿½ao da primeira bola e retorna o indice da bola em que acertou
			movepriority.add(i);
			int bola = nextball(i,dir);
			moveBall2(bola, dir);//continua o movimento, agora com a 2ï¿½ bola, sï¿½ pï¿½ra quando nao houver mais colisï¿½es de bolas

		}

		return 1;
	}


	public int moveBallbot(int i,int dir ){

		// 1- sucesso


		// 0- jogada inválida
		movepriority.add(i);


		if(!checknextball(i,dir)){// verifica se pode mexer a bola

			System.out.printf("Erro: Jogada Invalida\n");

			return 0;
		}
		else
		{
			//o nextball modifica a posiï¿½ao da primeira bola e retorna o indice da bola em que acertou
			//movepriority.add(i);
			int bola = nextball(i,dir);
			moveBall2(bola, dir);//continua o movimento, agora com a 2ï¿½ bola, sï¿½ pï¿½ra quando nao houver mais colisï¿½es de bolas

		}

		return 1;
	}

	//modifica a posiï¿½ao da bola apos a colisï¿½o, e continua recursivamente ate acabarem as colisï¿½es
	public void moveBall2(int i,int dir ){


		if(!checknextball2(i,dir)){
			movepriority.add(i);
			balls[i].setActive(false);

			switch(dir){
			case 0:
				balls[i].x=850;
				break;
			case 1:
				balls[i].y=850;
				break;
			case 2:
				balls[i].x=-50;
				break;
			case 3:
				balls[i].y=-50;
				break;

			}
		}else{
			movepriority.add(i);
			int bola = nextball(i,dir);
			moveBall2( bola, dir);
		}


	}

	// verifica se pode mexer a bola, isto ï¿½, se tem mais bolas na direcao e se nï¿½o tem nenhuma imediatamente a seguir
	public boolean checknextball(int bola, int dir){


		//direita
		switch(dir){
		case 0://direita x+
			for(int i=0;i<balls.length;i++){
				if(balls[i].getX()-balls[bola].getX()==1){
					return false;
				}
			}
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY() && balls[i].getX()>balls[bola].getX()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		case 1://cima y+
			for(int i=0;i<balls.length;i++){
				if(balls[i].getY()-balls[bola].getY()==1){
					return false;
				}
			}
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX() && balls[i].getY()>balls[bola].getY()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		case 2://esquerda x-
			for(int i=0;i<balls.length;i++){
				if(balls[bola].getX()-balls[i].getX()==1){
					return false;
				}
			}
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY() && balls[i].getX()<balls[bola].getX()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		case 3://baixo y-
			for(int i=0;i<balls.length;i++){
				if(balls[bola].getY()-balls[i].getY()==1){
					return false;
				}
			}
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX() && balls[i].getY()<balls[bola].getY()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		}


		return false;
	}

	// verifica se pode mexer a segunda bola, isto ï¿½, se tem mais bolas na direcao
	public boolean checknextball2(int bola, int dir){



		switch(dir){
		case 0://direita x++

			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY() && balls[i].getX()>balls[bola].getX()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		case 1://cima y++

			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX() && balls[i].getY()>balls[bola].getY()&&balls[i].getActive()){
					return true;
				}
			}
			break;
		case 2://esquerda x--

			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY() && balls[i].getX()<balls[bola].getX()&&balls[i].getActive()){
					return true;
				}
			}
			break;

		case 3://baixo y--

			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX() && balls[i].getY()<balls[bola].getY()&&balls[i].getActive()){
					return true;
				}
			}
			break;
		}

		return false;
	}

	//ï¿½ chamada depois do moveball ou moveball2, muda a posiï¿½ao da bola que ï¿½ movida e retorna o indice da bola seguinte a mover
	public int nextball(int bola, int dir){

		int i2 = 1000;
		int dif=0;
		int x=0,xi=0,yi=0,y=0;
		int bolafinal=-1;

		switch(dir){


		case 0: //direita
			y=balls[bola].getY();
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY()){

					dif = balls[i].getX()-balls[bola].getX();
					if(dif<i2 && dif>0){
						i2=dif;
						x= balls[i].getX()-50;
						bolafinal=i;
					}
				}

			}
			break;
		case 1: //cima
			x=balls[bola].getX();
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX()){
					dif = balls[i].getY()-balls[bola].getY();
					if(dif<i2 && dif>0){
						i2=dif;
						y= balls[i].getY()-50;
						bolafinal=i;
					}
				}
			}
			break;
		case 2: //esquerda
			y=balls[bola].getY();
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getY()==balls[i].getY()){
					dif = balls[bola].getX()-balls[i].getX();
					if(dif<i2 && dif>0){
						i2=dif;
						x= balls[i].getX()+50;
						bolafinal=i;
					}
				}
			}
			break;
		case 3: //baixo
			x=balls[bola].getX();
			for(int i=0;i<balls.length;i++){
				if(bola != i && balls[bola].getX()==balls[i].getX()){
					dif = balls[bola].getY()-balls[i].getY();
					if(dif<i2 && dif>0){
						i2=dif;
						y= balls[i].getY()+50;
						bolafinal=i;
					}
				}
			}
			break;
		}


		xi=balls[bola].getX();
		yi= balls[bola].getY();
		Ball check= new Ball(x,y);
		balls[bola]=check;
		balls[bola].dx=xi;
		balls[bola].dy=yi;

		return bolafinal;

	}


	public void move() {

		int i;
		boolean end=true;

		if(!movepriority.isEmpty()){

			i=movepriority.peek();

			if(balls[i].dx>balls[i].x){
				balls[i].dx--;
				end=false;
			}
			if(balls[i].dx<balls[i].x){
				balls[i].dx++;
				end=false;

			}
			if(balls[i].dy>balls[i].y){
				balls[i].dy--;
				end=false;

			}
			if(balls[i].dy<balls[i].y){
				balls[i].dy++;
				end=false;
			}

			if(end){
				movepriority.remove();
			}
		}


	}

	public Map<Map<String, Integer>,Integer> CountLines(){
		Map<String, Integer> counter= new HashMap<String, Integer>();
		Map<Map<String, Integer>,Integer> countertotal = new HashMap<Map<String, Integer>,Integer>();		

		for(int i =0; i<balls.length; i++){
			if(balls[i].getActive()){
				counter= new HashMap<String, Integer>();
				counter.put("x", balls[i].getX());

				if(!countertotal.containsKey(counter)){	

					countertotal.put(counter, 1);

				}else{

					countertotal.put(counter, (countertotal.get(counter))+1);

				}

				counter= new HashMap<String, Integer>();
				counter.put("y", balls[i].getY());

				if(!countertotal.containsKey(counter)){
					countertotal.put(counter, 1);
				}else{
					countertotal.put(counter, (countertotal.get(counter)+1));
				}


			}

		}

		return countertotal;
	}



	public boolean GameOver(){
		int active = 0;

		for(int i=0;i<balls.length;i++){

			if(balls[i].getActive()){
				active++;
			}
		}
		if(active ==1){
			System.out.printf("O jogo acabou\n");
			return true;
		}
		return false;
	}


	public void mouseClicked(MouseEvent me) {

		System.out.println("Entrou");
		int x = me.getPoint().x;
		int y = me.getPoint().y;
		for (int i=0; i<4;i++){	

			if(Math.abs(balls[i].getX()-x)<=50 &&Math.abs(balls[i].getY() - y)<=50){
				selectedball=i;
			}			
		}
	}

	public void keyReleased(KeyEvent e) {


		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			moveBall(selectedball, 2);

			//A bola vai parar de se mover

		}

		if (key == KeyEvent.VK_RIGHT) {
			//A bola vai parar de se mover
			moveBall(selectedball, 0);

		}

		if (key == KeyEvent.VK_UP) {
			//A bola vai parar de se mover
			moveBall(selectedball, 3);

		}

		if (key == KeyEvent.VK_DOWN){
			moveBall(selectedball, 1);

		}


	}



}
