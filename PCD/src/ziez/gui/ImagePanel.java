package ziez.gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel {
	private Image image = null;
	private BufferedImage buf = null;
	private Color warna;
	
	public ImagePanel() {

		setLayout (new BorderLayout ());
		setOpaque(false);
        warna = new Color(getBackground ().getRed (), getBackground ().getGreen (), getBackground ().getBlue (), 3);
	}

	public ImagePanel(BufferedImage img, BufferedImageOp op) {

		setBuffImage (img);
		setOperation (op);

		setPreferredSize (new Dimension (buf.getWidth (), buf.getHeight ()));
	}

	public ImagePanel(BufferedImage buf) {

		this (buf, null);
	}


	public void setBuffImage(BufferedImage in) {

		buf = in;
		this.setBounds (0, 0, buf.getWidth (), buf.getHeight ());
		this.setPreferredSize (new Dimension (buf.getWidth (), buf.getHeight ()));

		this.revalidate ();
		this.invalidate ();
		this.repaint ();

	}
	
	public BufferedImage getBuffImage() {

		return buf;
	}

	public void setOperation(BufferedImageOp op) {
	}
	
	 public void setBackground(Color bg) {
	        super.setBackground(bg);

	        warna = new Color(getBackground ().getRed (), getBackground ().getGreen (), getBackground ().getBlue (), 3);
	        repaint();
	    }
	 
	 public void setBackground(BufferedImage image, String url){
		 this.image = new ImageIcon(getClass().getResource(url)).getImage();
	      
		 repaint();
	 }

	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent (g);
		if (buf != null) {
			g.drawImage (buf, 0, 0, getWidth (), getHeight (), null);
		}
		
		Graphics2D gd = (Graphics2D) g.create();

        gd.setColor(warna);
        gd.fillRect(0, 0, getWidth(), getHeight());

        gd.dispose ();
        
        Graphics2D gdi = (Graphics2D) g.create();

        gdi.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        gdi.dispose();
	}
	
}
