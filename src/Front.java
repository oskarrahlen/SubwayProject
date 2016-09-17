import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
	private JLabel nextSubwayLabel = new JLabel("", SwingConstants.CENTER);
	
	public Front(){
		setTitle("App");
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		setSize((int)width, (int)height);
		setVisible(true);
		getContentPane().setBackground(Color.BLACK);
		add(panel);
		
		setDefaultCloseOperation(3);
		panel.setLayout(new BorderLayout());
		panel.add(nextSubwayLabel, BorderLayout.NORTH);
		panel.setBackground(Color.BLACK);
		nextSubwayLabel.setText("ERROR");
		nextSubwayLabel.setForeground(Color.YELLOW);
		nextSubwayLabel.setFont(new Font("Courier New", Font.BOLD, 60));
	}
	
	public void setSubwayLabels(long[][] input){
		
		
		nextSubwayLabel.setText("Nästa tåg: " + input[0][1] + " minuter");
	}
	
}
