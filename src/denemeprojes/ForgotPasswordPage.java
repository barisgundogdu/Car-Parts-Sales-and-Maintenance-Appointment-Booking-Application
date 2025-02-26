package denemeprojes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JTextField;
import javax.swing.JButton;

public class ForgotPasswordPage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfUserName;
	private JTextField tfGuvenlikSrs;
	protected LoginPage former;


	
	public ForgotPasswordPage(LoginPage lp) {
		former = lp;
		setTitle("FORGOT PASSWORD PAGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 651);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUserName = new JLabel("USERNAME:");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(282, 192, 112, 45);
		contentPane.add(lblUserName);
		
		tfUserName = new JTextField();
		tfUserName.setFont(tfUserName.getFont().deriveFont(tfUserName.getFont().getStyle() | Font.BOLD));
		tfUserName.setBounds(404, 195, 206, 45);
		contentPane.add(tfUserName);
		tfUserName.setColumns(10);
		
		JLabel lblGuvenlikSrs = new JLabel("SECURİTY QUESTİON:");
		lblGuvenlikSrs.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGuvenlikSrs.setForeground(Color.WHITE);
		lblGuvenlikSrs.setBounds(188, 295, 206, 45);
		contentPane.add(lblGuvenlikSrs);
		
		tfGuvenlikSrs = new JTextField();
		tfGuvenlikSrs.setFont(new Font("Tahoma", Font.BOLD, 14));
		tfGuvenlikSrs.setBounds(404, 296, 206, 45);
		contentPane.add(tfGuvenlikSrs);
		tfGuvenlikSrs.setColumns(10);
		
		JButton btnSubmit = new JButton("SUBMİT");
		btnSubmit.addActionListener(new ActionListener() {
	         @Override
	         public void actionPerformed(ActionEvent evt) {
	        	 BufferedReader br = null;
	             FileReader fr = null;
       		 boolean found = false;
	             try {
	            	 fr = new FileReader("users.txt");
	        		 br = new BufferedReader(fr);
	        		 String line;
	        		 String[] strArray;
	        		 while ((line = br.readLine()) != null) {
	        				 strArray = line.split(",");
	        				 if((strArray[3].equals(tfUserName.getText().trim())) && (strArray[5].equals(tfGuvenlikSrs.getText().trim()))){
	        					 found = true;
	        					 JOptionPane.showMessageDialog((Component)evt.getSource(), "Password: " + strArray[4]);
	        					 break;
	        				 }
	        		 }
	        		 if(found == false)
	        			 JOptionPane.showMessageDialog((Component)evt.getSource(), "There is not such a user...");
	        		 
	        	} catch (IOException e) {
	        			
	        		 System.out.println("File readin error occurred...");
	        	}
	            finally {
	            	 if(fr != null) {
	            		 try {
	            			 fr.close();
	            			 if(found == true) {
	            				 dispose(); 
	    		                 former.setLocationRelativeTo(null);
	    		         	     former.setVisible(true); 
	            			 }
	            				 
	            		 }
	            		 catch(IOException exp){
	            			 System.out.println("Read operation successful however close unsuccessful...");
	            		 }
	            	 }
	             }
	         }
	      });
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(263, 449, 258, 54);
		contentPane.add(btnSubmit);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
