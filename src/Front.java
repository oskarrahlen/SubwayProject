import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

/**
 * 
 */

/**
 * @author Oskar
 *
 */
public class Front extends JFrame {

	private JPanel panel = new JPanel();
	private JLabel label = new JLabel();
	
	public Front(){
		setTitle("The coolest subway sub in the sub tub");
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize((int)width, (int)height);
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		add(panel);
		
		setDefaultCloseOperation(3);
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		panel.setBackground(Color.BLACK);
		label.setText("Hello my name is nice");
		label.setForeground(Color.YELLOW);
		
		
	}
	
}
