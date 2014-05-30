/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package shot;

/**
 *
 * @author Manuel
 */
import javax.swing.JFrame;


public class Design extends JFrame {

	Board board;

	public Design() {

		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 800);
		setLocationRelativeTo(null);
		setTitle("R - Type");
		setResizable(false);
		setVisible(true);


	}

	public static void main(String[] args) {
		new Design();
	}
}
