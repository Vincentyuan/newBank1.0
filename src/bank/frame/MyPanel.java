package bank.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private ImageIcon backImage;
	public MyPanel(String imageName) {
		// TODO Auto-generated constructor stub
		backImage = new ImageIcon(imageName);
		setLayout(null);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backImage.getImage(), 0, 0, this);
	}

}
