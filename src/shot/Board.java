/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shot;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 *
 * @author Manuel
 */
public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Ball ball;
	public Logic logic;
	//public Heuristic logic;

	public Board() {

		addKeyListener(new TAdapter());
		addMouseListener(new MouseClick());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		logic = new Logic();
		//logic = new Heuristic();
		timer = new Timer(5, this);
		timer.start();

	}




	public void paint(Graphics g) {
		super.paint(g);

		Graphics2D g2d = (Graphics2D)g;
		for(int i=0;i<logic.balls.length;i++){
			

			g2d.drawImage(logic.balls[i].getImage(), logic.balls[i].dx, logic.balls[i].dy, this);
			
		}
		repaint();


		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public class MouseClick implements MouseListener 
	{
		
		
		@Override
		public void mouseClicked(MouseEvent me) {
			// TODO Auto-generated method stub
			System.out.println("Entrou");
			int x = me.getPoint().x;
			int y = me.getPoint().y;
			for (int i=0; i<logic.balls.length;i++){	

				if(Math.abs(logic.balls[i].getX()-x)<=50 &&Math.abs(logic.balls[i].getY() - y)<=50){
					logic.selectedball=i;
				}			
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		} 
	}


	public void actionPerformed(ActionEvent e) {
		// definir aqui o movimento
		//logic.checkplay(logic.game);
		logic.move();
	}




	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {
			logic.keyReleased(e);
		}


	}

}
