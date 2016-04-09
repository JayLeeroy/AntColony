package Tester;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class TestPanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage undergound;
    
    private BufferedImage surface;

    public TestPanel() {
        undergound = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        surface = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.drawImage(undergound, 0, 0, this);
        g2d.drawImage(surface, 550, 0, this);
        g2d.dispose();
    }
    
    public void setUndergroundParticle(int x, int y, Color color)
    {
    	Graphics2D g2d = undergound.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(x, y, 1, 1);
        g2d.dispose();
    	
    	this.repaint();
    }
    
    public void setSurfaceParticle(int x, int y, Color color)
    {
    	Graphics2D g2d = surface.createGraphics();
        g2d.setColor(color);
        g2d.fillRect(x, y, 1, 1);
        g2d.dispose();
    	
    	this.repaint();
    }
}