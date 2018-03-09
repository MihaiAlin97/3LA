import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField UsernameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField EmailField;
	
	
	public static boolean EmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                            "[a-zA-Z0-9_+&*-]+)*@" +
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                            "A-Z]{2,7}$";
                             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
 
	
	public static boolean PassValid(String password)
    {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";
                             
        Pattern pat = Pattern.compile(passwordRegex);
        if (password == null) 
            return false;
        return pat.matcher(password).matches();
    }
 
	
	
	
	
	/**
	 * Launch the application.
	 */
	public static void RegScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
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
	public Registration() {

		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		UsernameField = new JTextField();
		UsernameField.setBounds(195, 47, 86, 20);
		contentPane.add(UsernameField);
		UsernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(195, 78, 86, 20);
		contentPane.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(195, 109, 86, 20);
		contentPane.add(passwordField_1);
		
		EmailField = new JTextField();
		EmailField.setBounds(195, 140, 86, 20);
		contentPane.add(EmailField);
		EmailField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Choose your username:");
		lblNewLabel.setBounds(46, 50, 142, 14); 
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter your password:");
		lblNewLabel_1.setBounds(46, 81, 142, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm Password:");
		lblNewLabel_2.setBounds(46, 112, 131, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("E-mail:");
		lblNewLabel_3.setBounds(46, 143, 118, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				Connection conn=null;
				conn=sqliteConnection.Connector();
				
				try { 
					
		
					boolean check =true; 
				
				    if(UsernameField.getText().equals("")||
				    		passwordField.getText().equals("")||
				    		passwordField_1.getText().equals("")||
				    		EmailField.getText().equals("")) 
				    { 
				    	JOptionPane.showMessageDialog(null,"Can't leave blank");
				    	check=false;
				    }//see if all fiels are not blank
				    else //*if none is blank,check the rest*//
				    	{
				    	String query="select * from INFO where username=?;";
					    PreparedStatement pst=conn.prepareStatement(query);
					    pst.setString(1,UsernameField.getText());
						
					    ResultSet rs=pst.executeQuery();
					    
					    
					    int count=0;
					    while(rs.next()) {
					    	count++;
					    }
					    if (count==1) {
					    	JOptionPane.showMessageDialog(null,"Username already in use");
					    	check=false;
					    }
						//check if the username is already in use
				    	
				    	
				    	
				    	
				    	
				    
				    	if(passwordField.getText().equals(passwordField_1.getText())==false) {
				    	check=false;
				    	JOptionPane.showMessageDialog(null,"Passwords do not match");
				        }//see if both pass fields match
				    

				    	else if(PassValid(passwordField.getText())==false) //if they do match,check if they are valid
				    	{
				    	check=false;
				    	JOptionPane.showMessageDialog(null,"Password must contain at"
				    			+ " least one uppercase,one lowercase and one digit");
				        }
				    
				   
				    	if(EmailValid(EmailField.getText())==false) {//check if email is valid
				    	check=false;
				    	JOptionPane.showMessageDialog(null,"Invalid e-mail format");
				        }
				    
				    
				    
				    
				    pst.close();
					rs.close();
				    }
				    
				    
				    if(check==true) {//if all things are ok,put all info in the database
				    	try {
				    
												
				    	String insert="INSERT INTO INFO(username,password,email) VALUES(?,?,?);";
				    	PreparedStatement pst=conn.prepareStatement(insert);
				    	pst.setString(1,UsernameField.getText());
				    	pst.setString(2,passwordField.getText());
				    	pst.setString(3,EmailField.getText());
				    	pst.executeUpdate();
				    	
				    	pst.close();
				    	
				    	PatternSelection.PattScreen(UsernameField.getText());
				    	dispose();
				         
				    	
				    	} 
				    	catch(Exception e1) {
				    		JOptionPane.showMessageDialog(null,e1);
				    	}
				    	
				    	
				    	
				    }
				
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(null,e1);
				}
				
			
			}
		});
		btnNext.setBounds(192, 189, 89, 23);
		contentPane.add(btnNext);
	}

}
