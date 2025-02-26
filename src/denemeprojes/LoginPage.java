package denemeprojes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;

public class LoginPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JTextField tfUserName;
	private JPasswordField pfPassword;
	private String username;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage(null, null, null);
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginPage(HomePage hp, BuyPartsPage bp, MaintenancePage mp) {
		setTitle("LOGIN PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1027, 682);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel("GUNDOGDU GARAGE");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblTitle.setBounds(313, 36, 379, 92);
		contentPane.add(lblTitle);

		JLabel lblUserName = new JLabel("USERNAME:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(216, 183, 184, 55);
		contentPane.add(lblUserName);

		tfUserName = new JTextField();
		tfUserName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfUserName.setBounds(434, 183, 269, 55);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);

		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(216, 274, 184, 55);
		contentPane.add(lblPassword);

		JButton btnLogin = new JButton("LOGIN");
		BtnLoginActionListener myLgn = new BtnLoginActionListener(this);
		btnLogin.addActionListener(myLgn);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLogin.setBounds(182, 419, 242, 55);
		contentPane.add(btnLogin);

		JButton btnRegister = new JButton("REGISTER");
		BtnRegisterListener myListener = new BtnRegisterListener(this);
		btnRegister.addActionListener(myListener);
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnRegister.setBounds(540, 419, 242, 55);
		contentPane.add(btnRegister);

		JButton btnForgotPassword = new JButton("FORGOT PASSWORD?");
		BtnForgotPasswordListener myListener2 = new BtnForgotPasswordListener(this);
		btnForgotPassword.addActionListener(myListener2);
		btnForgotPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnForgotPassword.setBounds(348, 509, 269, 43);
		contentPane.add(btnForgotPassword);

		pfPassword = new JPasswordField();
		pfPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pfPassword.setBounds(434, 274, 269, 55);
		contentPane.add(pfPassword);
	}

	private class BtnLoginActionListener implements ActionListener {

		private LoginPage lp;
		private HomePage hp;
		private BuyPartsPage bp;
		private MaintenancePage mp;

		public BtnLoginActionListener(LoginPage l) {
			lp = l;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			String adminUserName = "admin";
			String adminPassword = "admin";
			if (tfUserName.getText().trim().isEmpty() || pfPassword.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(lp, "Username or password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ((tfUserName.getText().trim().equals(adminUserName)) && (pfPassword.getText().trim().equals(adminPassword))) {
				AdminPage adminPage = new AdminPage();
				adminPage.setVisible(true);
				lp.setVisible(false);
			} else {
				FileReader fr = null;
				BufferedReader br = null;
				boolean found = false;
				try {
					fr = new FileReader("users.txt");
					br = new BufferedReader(fr);
					String line;
					String[] strArray;
					while ((line = br.readLine()) != null) {
						strArray = line.split(",");
						if ((strArray[3].equals(tfUserName.getText().trim())) && (strArray[4].equals(pfPassword.getText().trim()))) {
							found = true;
							
							String username = tfUserName.getText().trim();
							break;
						}
					}
					if (!found) {
						JOptionPane.showMessageDialog(lp, "There is no such a user...", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e) {
					JOptionPane.showMessageDialog(lp, "File reading error occurred...", "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					if (fr != null) {
						try {
							fr.close();
							if (found) {
								
								HomePage homePage = new HomePage(hp, lp, bp, username);
								homePage.setVisible(true);
								lp.setVisible(false);
							}
						} catch (IOException exp) {
							JOptionPane.showMessageDialog(lp, "Read operation successful however close unsuccessful...", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		}
	}

	private class BtnRegisterListener implements ActionListener {

		private LoginPage lp;

		public BtnRegisterListener(LoginPage l) {
			lp = l;
		}

		public void actionPerformed(ActionEvent evt) {
			setVisible(false);
			RegisterPage rp = new RegisterPage(lp);
		}
	}

	private class BtnForgotPasswordListener implements ActionListener {

		private LoginPage lp;

		public BtnForgotPasswordListener(LoginPage l) {
			lp = l;
		}

		public void actionPerformed(ActionEvent evt) {
			setVisible(false);
			ForgotPasswordPage fp = new ForgotPasswordPage(lp);
		}
	}

	public String getUsername() {
		return this.username;
	}
}