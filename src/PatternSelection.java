import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PatternSelection extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNext;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void PattScreen(String S) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatternSelection frame = new PatternSelection(S);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PatternSelection(String username) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				textField_4.setText(textField_4.getText()+"red");
				
				
			}
		});
		textField.setBackground(Color.RED);
		textField.setEditable(false);
		textField.setBounds(33, 39, 86, 88);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(textField_4.getText()+"yellow");
			}
		});
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBackground(Color.YELLOW);
		textField_1.setBounds(129, 39, 86, 88);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(textField_4.getText()+"blue");
			}
		});
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(Color.BLUE);
		textField_2.setBounds(225, 39, 86, 88);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(textField_4.getText()+"green");
			}
		});
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBackground(Color.GREEN);
		textField_3.setBounds(321, 39, 86, 88);
		contentPane.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(33, 148, 372, 26);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				conn=sqliteConnection.Connector(); 
				
				try {//see where is the username passed as argument,and set the pattern for it
					String pattern=textField_4.getText().replace("red","1");
					pattern=pattern.replace("yellow","2");
					pattern=pattern.replace("blue","3");
					pattern=pattern.replace("green","4");
					String update="UPDATE INFO set pattern=? WHERE username=?;";
					PreparedStatement pst=conn.prepareStatement(update);
					pst.setString(1,pattern);
					pst.setString(2,username);
					 
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null,"Registration Succesful");
		            pst.close(); 
		            
		            
		            
		             Home.main(null);
		           dispose();
					
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
				
			}
		});
		btnNext.setBounds(222, 198, 89, 23);
		contentPane.add(btnNext);
		
		btnNewButton = new JButton("Clear");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_4.setText(null);
			}
		});
		btnNewButton.setBounds(126, 198, 89, 23);
		contentPane.add(btnNewButton);
	}
}
