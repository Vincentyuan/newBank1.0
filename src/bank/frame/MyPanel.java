package bank.frame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MyPanel extends JPanel{
	private BufferedImage backImage;
	public MyPanel(String imageName) {
		// TODO Auto-generated constructor stub
//		backImage = new ImageIcon(imageName);
	       try {                
	    	   backImage = ImageIO.read(new File(imageName));
	        } catch (IOException ex) {
	             // handle exception...
	        }
		setLayout(null);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backImage, 0, 0, this);
	}

}
