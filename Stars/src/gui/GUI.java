package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import main.Stars;
import data.Data;

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JCheckBox staminaUsageBox;
	private JTextField txtFoodName;
	private JTextField healthBox;
	
	public boolean running;

	public GUI() {
		setTitle("Stars 1.0");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 240, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JButton btnStartScript = new JButton("Start Script");
		btnStartScript.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Stars.data = new Data(
						staminaUsageBox.isSelected(), 
						txtFoodName.getText(),
						Integer.parseInt(healthBox.getText()));
				running = false;
				dispose();
			}
		});
		btnStartScript.setBounds(35, 80, 150, 25);
		contentPane.add(btnStartScript);
		
		staminaUsageBox = new JCheckBox();
		staminaUsageBox.setText("Stamina Pots");
		staminaUsageBox.setBounds(10, 10, 100, 20);
		staminaUsageBox.setSelected(true);
		contentPane.add(staminaUsageBox);
		
		txtFoodName = new JTextField();
		txtFoodName.setText("Lobster");
		txtFoodName.setColumns(10);
		txtFoodName.setBounds(120, 10, 100, 20);
		contentPane.add(txtFoodName);
		
		healthBox = new JTextField();
		healthBox.setText("60");
		healthBox.setColumns(2);
		healthBox.setBounds(120, 40, 100, 20);
		contentPane.add(healthBox);
		
		
		running = true;
		setVisible(true);
	}
}