package denemeprojes;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private HomePage homePage;
    private LoginPage loginPage;
    private BuyPartsPage buyPartsPage;
    private String username;

    public HomePage(HomePage hp, LoginPage lp, BuyPartsPage bp, String username) {
        this.homePage = hp;
        this.loginPage = lp;
        this.buyPartsPage = bp;
        this.username = username;
        setTitle("Home Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1052, 653);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblWelcome = new JLabel("WELCOME TO GUNDOGDU GARAGE");
        lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblWelcome.setForeground(Color.WHITE);
        lblWelcome.setBounds(20, 20, 726, 61);
        contentPane.add(lblWelcome);

        JButton btnBuyParts = new JButton("BUY PARTS");
        btnBuyParts.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnBuyParts.setBounds(216, 169, 259, 287);
        contentPane.add(btnBuyParts);
        btnBuyParts.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BuyPartsPage buyPartsPage = new BuyPartsPage(HomePage.this, loginPage, username);
                buyPartsPage.setVisible(true);
                setVisible(false);
            }
        });

        JButton btnMaintenance = new JButton("MAINTENANCE");
        btnMaintenance.setFont(new Font("Tahoma", Font.BOLD, 28));
        btnMaintenance.setBounds(549, 169, 259, 287);
        contentPane.add(btnMaintenance);
        btnMaintenance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MaintenancePage maintenancePage = new MaintenancePage(HomePage.this, lp, username);
                maintenancePage.setVisible(true);
                dispose();
            }
        });
        
        JButton btnLogout = new JButton("Log Out");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnLogout.setBounds(790, 540, 200, 33);
        contentPane.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (loginPage != null) {
                    loginPage.setVisible(true);
                }
                dispose(); 
            }
        });
        
        JButton btnContact = new JButton("Contact Us");
        btnContact.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnContact.setBounds(49, 540, 200, 33);
        contentPane.add(btnContact);
        btnContact.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                ContactDialog contactDialog = new ContactDialog(HomePage.this);
                contactDialog.setVisible(true);
            }
        });
        
        setVisible(true);
        setLocationRelativeTo(null);
    }
}