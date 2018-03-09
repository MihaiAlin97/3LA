import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatternConfirm extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_4;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void ConfScreen(String S) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatternConfirm frame = new PatternConfirm(S);
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
	public PatternConfirm(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_4.setText(textField_4.getText()+"red");
			}
		});
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(Color.RED);
		textField.setBounds(33, 39, 86, 88);
		contentPane.add(textField);
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		textField_4.setBounds(33, 142, 372, 26);
		contentPane.add(textField_4);
		
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
		textField_3.setBounds(319, 39, 86, 88);
		contentPane.add(textField_3);
		
		JButton button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
             textField_4.setText(null);
			}
		});
		button.setBounds(126, 198, 89, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn=null;
				conn=sqliteConnection.Connector(); 
				try {
					String pattern=textField_4.getText().replace("red","1");
					pattern=pattern.replace("yellow","2");
					pattern=pattern.replace("blue","3");
					pattern=pattern.replace("green","4");
					
					String query="Select pattern FROM INFO where username=?;";
					PreparedStatement pst=conn.prepareStatement(query);
					pst.setString(1,username);
					ResultSet rs=pst.executeQuery(); 
				
					
					if(rs.getObject(1).equals(pattern)==true) {
						JOptionPane.showMessageDialog(null,"Pattern is correct");
						
						pst.close(); 
						rs.close();
						
					
						Verification.VerScreen(username);
						dispose();
						
					}
					else JOptionPane.showMessageDialog(null,"Pattern is incorrect");
					 pst.close();
					 rs.close();
					
				}
				
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		button_1.setBounds(225, 198, 89, 23);
		contentPane.add(button_1);
	}

}
