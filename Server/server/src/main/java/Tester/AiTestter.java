package Tester;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import ColonAi.World;
import ColonAi.PackageManager.Manager;
import ColonAi.WorldObjects.WorldObjectAbstract;

public class AiTestter {

	private JFrame frame;
	
	private World world;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AiTestter window = new AiTestter();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AiTestter() {
		this.world = new World(100, 100);
		 //int a = world.underGroundDimension.getMatrix()[0].length;
		 
		 //System.out.println(a);
		 
	     this.world.init();
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.world.run();
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		TestPanel panel = new TestPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				for (int aaa = 0; aaa < 10000; aaa++)
				{
					world.run();
					WorldObjectAbstract[][] matrix = world.underGroundDimension.getMatrix();
					
					for(int i = 0 ; i < matrix.length; i++)
					{
						for(int j = 0 ; j < matrix[0].length; j++)
						{
							if(matrix[i][j] != null)
							{							
								panel.setUndergroundParticle(i, j, Color.RED);
							}
						}
					}
				}
			}
			@Override
			public void mousePressed(MouseEvent e) {
				panel.setSurfaceParticle(e.getX()-550, e.getY(), Color.RED);
			}
		});
	}

}
