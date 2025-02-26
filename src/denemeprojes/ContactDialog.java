package denemeprojes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactDialog extends JDialog {

    public ContactDialog(JFrame parent) {
        super(parent, "İletişim Bilgileri", true);
        setSize(631, 343);
        setLocationRelativeTo(parent);

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);

        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("NAME:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setBounds(114, 49, 72, 32);
        panel.add(lblNewLabel);
        
        JLabel lblNewLabel_1 = new JLabel("PHONE NUMBER:");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_1.setBounds(10, 103, 176, 32);
        panel.add(lblNewLabel_1);
        
        JLabel lblNewLabel_2 = new JLabel("E-MAİL:");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2.setForeground(Color.WHITE);
        lblNewLabel_2.setBounds(100, 159, 86, 32);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("ADDRESS:");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(80, 218, 134, 32);
        panel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("BARIŞ GÜNDOĞDU");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setBounds(215, 51, 221, 32);
        panel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("0532 875 95 65");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_5.setForeground(Color.WHITE);
        lblNewLabel_5.setBounds(215, 105, 161, 32);
        panel.add(lblNewLabel_5);
        
        JLabel lblNewLabel_6 = new JLabel("gundogdugarage@gmail.com");
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6.setForeground(Color.WHITE);
        lblNewLabel_6.setBounds(215, 161, 249, 32);
        panel.add(lblNewLabel_6);
        
        JLabel lblNewLabel_7 = new JLabel("İstanbul Büyükçekmece Tepekent NO: 18");
        lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_7.setForeground(Color.WHITE);
        lblNewLabel_7.setBounds(215, 220, 342, 32);
        panel.add(lblNewLabel_7);
        
        JLabel lblNewLabel_8 = new JLabel("CONTACT INFORMATION");
        lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblNewLabel_8.setForeground(Color.BLACK);
        lblNewLabel_8.setBounds(152, 10, 312, 29);
        panel.add(lblNewLabel_8);
    }
}