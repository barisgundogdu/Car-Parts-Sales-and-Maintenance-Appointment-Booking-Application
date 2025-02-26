package denemeprojes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class RegisterPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfName;
	private JTextField tfSurName;
	private JTextField tfUserName;
	private JTextField tfGuvenlikSrs;
	private JPasswordField pfPassword;
    protected LoginPage former;
	
	public RegisterPage(LoginPage lp) {
		former = lp;
		setTitle("REGISTER PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1015, 627);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setForeground(Color.WHITE);
		lblName.setBounds(358, 221, 68, 25);
		contentPane.add(lblName);
		
		JLabel lblSurName = new JLabel("SURNAME:");
		lblSurName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSurName.setForeground(Color.WHITE);
		lblSurName.setBounds(319, 269, 108, 25);
		contentPane.add(lblSurName);
		
		tfName = new JTextField();
		tfName.setBounds(466, 220, 243, 34);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfSurName = new JTextField();
		tfSurName.setBounds(466, 264, 243, 34);
		contentPane.add(tfSurName);
		tfSurName.setColumns(10);
		
		JLabel lblUserName = new JLabel("USERNAME:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(308, 314, 137, 25);
		contentPane.add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setBounds(466, 308, 243, 35);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		
		tfGuvenlikSrs = new JTextField();
		tfGuvenlikSrs.setBounds(466, 401, 244, 30);
		contentPane.add(tfGuvenlikSrs);
		tfGuvenlikSrs.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(305, 359, 404, 25);
		contentPane.add(lblPassword);
		
		pfPassword = new JPasswordField();
		pfPassword.setBounds(465, 354, 244, 34);
		contentPane.add(pfPassword);
		
		JLabel lblGuvenlikSrs = new JLabel("FAVOURITE FRIEND:");
		lblGuvenlikSrs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGuvenlikSrs.setForeground(Color.WHITE);
		lblGuvenlikSrs.setBounds(230, 400, 479, 25);
		contentPane.add(lblGuvenlikSrs);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 BufferedReader br = null;
	             FileReader fr = null;
	             int tempId = 0;
        		 boolean found = false;
	             try {
	            	 fr = new FileReader("users.txt");
	        		 br = new BufferedReader(fr);
	        		 String line;
	        		 String[] strArray;
	        		 
	        		 while((line = br.readLine()) != null) {
	        				 strArray = line.split(",");
	        				 tempId = Integer.parseInt(strArray[0]);
	        				 if((strArray[3].equals(tfUserName.getText().trim()))) {
	        					 found = true;
	        					 JOptionPane.showMessageDialog(null, "This user name already exists...", "Error", JOptionPane.ERROR_MESSAGE);
	        					 break;
	        				 }
	        		 }
	        		 
	        	} catch (IOException e) {
	        			
	        		 JOptionPane.showMessageDialog(null, "File reading error occurred...", "Error", JOptionPane.ERROR_MESSAGE);
	        	}
	            finally {
	            	 if(fr != null) {
	            		 try {
	            			 fr.close();
	            		 }
	            		 catch(IOException exp){
	            			 System.out.println("Read operation successful however close unsuccessful...");
	            		 }
	            	 }
	             }
	        	 
	        	 if(!found) {
	        		 FileWriter fWriter = null;
		             try {
		            	 tempId++;
		            	 String temp = tempId + "," + tfName.getText().trim() + "," + tfSurName.getText().trim() + "," + tfUserName.getText().trim() + "," + new String(pfPassword.getPassword()).trim() + "," + tfGuvenlikSrs.getText().trim() + "\r\n";	            	
		            	 fWriter = new FileWriter("users.txt", true);
		            	 fWriter.write(temp);
		                 JOptionPane.showMessageDialog(null, "Register operation successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
		                 dispose(); 
		                 former.setLocationRelativeTo(null);
		         	     former.setVisible(true); 
		             }
		             catch (IOException exp) {
		                 JOptionPane.showMessageDialog(null, "Register operation unsuccessful...", "Error", JOptionPane.ERROR_MESSAGE);
		             }
		             finally {
		            	 if(fWriter != null) {
		            		 try {
		            			 fWriter.close();
		            		 }
		            		 catch(IOException exp){
		            			 System.out.println("Close unsuccessful...");
		            		 }
		            	 }
		             }
	        	 }
	         }
	      });
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegister.setBounds(429, 471, 137, 36);
		contentPane.add(btnRegister);
		
		JLabel lblNewLabel = new JLabel("GUNDOGDU GARAGE REGISTER PAGE");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(309, 74, 479, 98);
		contentPane.add(lblNewLabel);
		setVisible(true);
		setLocationRelativeTo(null);
	}
}