package Frames;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.SwingConstants;

public class FirstFrame extends JFrame {

	public boolean x = true;
	public GeneralPanel panel;
	private JButton startButton;
	private FrameDiScelta secondFrame;
	
	public FirstFrame() {
		
		super("Project GRU");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FirstFrame.class.getResource("/imgs/lastin.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 517, 555);
		getContentPane().setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		panel = new GeneralPanel();
		panel.setBounds(0, 0, 501, 516);
		getContentPane().add(panel);
		
		ImageIcon iconlabelImageFirstFrame = new ImageIcon(getClass().getResource("/imgs//program.jpg"));
		JLabel labelImageFirstFrame = new JLabel("");
		labelImageFirstFrame.setBounds(23, 148, 454, 179);
		labelImageFirstFrame.setIcon(iconlabelImageFirstFrame);
		panel.add(labelImageFirstFrame);
		
		startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				secondFrame = new FrameDiScelta();
				setVisible(false);
				
			}
		});
		startButton.setFont(new Font("Kimberley Bl", Font.PLAIN, 13));
		startButton.setBackground(Color.white);
		startButton.setForeground(Color.RED);
		startButton.setBounds(182, 371, 120, 37);
		startButton.setBorder(new RoundBorderBotton(10));
		panel.add(startButton);
		
		setVisible(true);

	}
	
}
