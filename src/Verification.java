import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Verification extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNewLabel_1;
	
	
	
	private static char randomletter () {
	    int rand = (int) (Math.random() * 52); 
	    char base; 
		if (rand < 26)base='A';
	    else base='a';
	    return (char) (base + rand % 26);

	}
	
	private static String randomCode() {
		int i=0;
		String rand="";
		while(i<=8) {
			rand=rand+randomletter();
			i++;
		}
		return rand;
	}

	/**
	 * Launch the application.
	 */
	public static void VerScreen(String S) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Verification frame = new Verification(S);
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
	public Verification(String username) {
		String email = null;
		
		try {
		Connection conn=null;
		conn=sqliteConnection.Connector(); 
		
		String query="Select email FROM INFO where username=?;";
		PreparedStatement pst=conn.prepareStatement(query);
		pst.setString(1,username);
		ResultSet rs=pst.executeQuery(); 
		
		email=rs.getString(1);
	
		rs.close();
		pst.close();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
		}	
		//obtain e-mail
		
		String code=randomCode();
		
		new SendEmail(email,"Verification",code);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("A mesage will be sent to your e-mail address.");
		lblNewLabel.setBounds(10, 24, 414, 36);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(94, 102, 207, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnConfirm = new JButton("Confirm");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					if(code.equals(textField.getText())==true) {JOptionPane.showMessageDialog(null,"Authentication complete");
					LastW.LastScreen();
					dispose();
					}
					
					//check if the sent text matches the one that is inputted 
					else JOptionPane.showMessageDialog(null,"Incorrect");
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		btnConfirm.setBounds(153, 133, 89, 23);
		contentPane.add(btnConfirm);
		
		lblNewLabel_1 = new JLabel("Please enter the text you received below:");
		lblNewLabel_1.setBounds(10, 61, 358, 14);
		contentPane.add(lblNewLabel_1);
	}
}
