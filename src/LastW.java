import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LastW extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void LastScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LastW frame = new LastW();
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
	public LastW() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogOff = new JButton("Log off");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Home.main(null);
				dispose();
			}
		});
		btnLogOff.setBounds(161, 132, 89, 23);
		contentPane.add(btnLogOff);
	}

}
