package shot;

import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class Ball {

	public int x;
	public int y;
	

	//direcão de x
	public int dx;
	public int dy;


	private boolean active=true;

	//objeto ball (img)
	private String ball = "ball.png";
	//objeto Imagem
	private Image image;

	public Ball(int x, int y){
		ImageIcon ii = new ImageIcon(this.getClass().getResource(ball));
		image = ii.getImage();
		active = true;

		this.x=x;
		this.y=y;
		this.dx=x;
		this.dy=y;
	}

	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void move(int dir) {
		x += dx;
		y += dy;
	}

	public Image getImage() {
		return image;
	}

	public void setX(int x){
		this.x=x;
	}

	public void setY(int y){
		this.y=y;
	}

	public boolean getActive(){
		return active;
	}

	public void setActive(boolean a){
		active = a;
	}

	





}
