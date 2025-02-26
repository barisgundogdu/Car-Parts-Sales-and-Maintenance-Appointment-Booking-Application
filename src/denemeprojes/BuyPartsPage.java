package denemeprojes;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.EmptyBorder;

public class BuyPartsPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private HomePage former;
    private DefaultTableModel innerTableModel;
    private DefaultTableModel outerTableModel;
    private DefaultTableModel cartModel;
    private JTable cartTable;
    private ArrayList<String[]> cartItems;
    private ArrayList<String[]> partsData;
    private final String filePath = "parts.txt";
    private final String purchaseHistoryFilePath = "purchase_history.txt";
    private String username;

    
    public BuyPartsPage(HomePage hp, LoginPage lp, String username) {
    	this.former = hp;
        this.username = lp.tfUserName.getText().trim(); 
        this.cartItems = new ArrayList<>();
        this.partsData = new ArrayList<>();

        setTitle("HOME PAGE - GUNDOGDU OTO TUNING MARKET");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 888, 800);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("WELCOME TO GUNDOGDU GARAGE");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(10, 11, 764, 27);
        contentPane.add(lblTitle);

        JLabel lblInnerParts = new JLabel("INNER PARTS");
        lblInnerParts.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblInnerParts.setForeground(Color.WHITE);
        lblInnerParts.setBounds(10, 50, 150, 20);
        contentPane.add(lblInnerParts);

        String[] columnNames = { "Part ID", "Part Name", "Price ($)", "Availability", "Stock" };

        innerTableModel = new DefaultTableModel(columnNames, 0);
        outerTableModel = new DefaultTableModel(columnNames, 0);

        loadPartsData();

        JTable innerTable = new JTable(innerTableModel);
        JScrollPane innerScrollPane = new JScrollPane(innerTable);
        innerScrollPane.setBounds(10, 80, 854, 150);
        contentPane.add(innerScrollPane);

        JButton btnAddToCartInner = new JButton("ADD TO CART ");
        btnAddToCartInner.setFont(btnAddToCartInner.getFont().deriveFont(Font.BOLD));
        btnAddToCartInner.setBounds(10, 240, 200, 30);
        btnAddToCartInner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = innerTable.getSelectedRow();
                if (selectedRow != -1) {
                    int stock = Integer.parseInt(innerTableModel.getValueAt(selectedRow, 4).toString());
                    if (stock > 0) {
                        String[] item = {
                            innerTableModel.getValueAt(selectedRow, 0).toString(),
                            innerTableModel.getValueAt(selectedRow, 1).toString(),
                            innerTableModel.getValueAt(selectedRow, 2).toString()
                        };
                        cartItems.add(item);
                        cartModel.addRow(item);
                        innerTableModel.setValueAt(String.valueOf(stock - 1), selectedRow, 4);
                        updatePartsData(innerTableModel.getValueAt(selectedRow, 0).toString(), stock - 1);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "This item is out of stock.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please select an inner product to add to cart.");
                }
            }
        });
        contentPane.add(btnAddToCartInner);

        JLabel lblOuterParts = new JLabel("OUTER PARTS");
        lblOuterParts.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblOuterParts.setForeground(Color.WHITE);
        lblOuterParts.setBounds(10, 290, 150, 20);
        contentPane.add(lblOuterParts);

        JTable outerTable = new JTable(outerTableModel);
        JScrollPane outerScrollPane = new JScrollPane(outerTable);
        outerScrollPane.setBounds(10, 320, 854, 150);
        contentPane.add(outerScrollPane);

        JButton btnAddToCartOuter = new JButton("ADD TO CART ");
        btnAddToCartOuter.setFont(btnAddToCartOuter.getFont().deriveFont(Font.BOLD));
        btnAddToCartOuter.setBounds(10, 480, 200, 30);
        btnAddToCartOuter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = outerTable.getSelectedRow();
                if (selectedRow != -1) {
                    int stock = Integer.parseInt(outerTableModel.getValueAt(selectedRow, 4).toString());
                    if (stock > 0) {
                        String[] item = {
                            outerTableModel.getValueAt(selectedRow, 0).toString(),
                            outerTableModel.getValueAt(selectedRow, 1).toString(),
                            outerTableModel.getValueAt(selectedRow, 2).toString()
                        };
                        cartItems.add(item);
                        cartModel.addRow(item);
                        outerTableModel.setValueAt(String.valueOf(stock - 1), selectedRow, 4);
                        updatePartsData(outerTableModel.getValueAt(selectedRow, 0).toString(), stock - 1);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "This item is out of stock.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please select an outer product to add to cart.");
                }
            }
        });
        contentPane.add(btnAddToCartOuter);

        JLabel lblCart = new JLabel("CART");
        lblCart.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblCart.setForeground(Color.WHITE);
        lblCart.setBounds(10, 520, 150, 20);
        contentPane.add(lblCart);

        String[] cartColumnNames = { "Part ID", "Part Name", "Price ($)" };
        cartModel = new DefaultTableModel(null, cartColumnNames);
        cartTable = new JTable(cartModel);
        JScrollPane cartScrollPane = new JScrollPane(cartTable);
        cartScrollPane.setBounds(10, 550, 854, 150);
        contentPane.add(cartScrollPane);

        JButton btnCheckout = new JButton("CHECKOUT");
        btnCheckout.setFont(btnCheckout.getFont().deriveFont(Font.BOLD));
        btnCheckout.setBounds(10, 710, 150, 30);
        btnCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cartItems.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Your cart is empty.");
                } else {
                    double total = 0;
                    for (String[] item :cartItems) {
                        total += Double.parseDouble(item[2]);
                    }
                    int option = JOptionPane.showConfirmDialog(contentPane, "Total Price: $" + total + "\nDo you want to proceed to payment?", "Confirm Purchase", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION) {
                        writePurchaseHistory(total);
                        showCreditCardDialog(total);
                    }
                }
            }
        });
        contentPane.add(btnCheckout);

        JButton btnLogout = new JButton("LOGOUT");
        btnLogout.setFont(btnLogout.getFont().deriveFont(btnLogout.getFont().getStyle() | Font.BOLD));
        btnLogout.setBounds(170, 710, 100, 30);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                lp.setLocationRelativeTo(null);
                lp.setVisible(true);
            }
        });
        contentPane.add(btnLogout);
        
        JButton btnGoBack = new JButton("GO BACK");
        btnGoBack.setFont(btnGoBack.getFont().deriveFont(Font.BOLD));
        btnGoBack.setBounds(280, 710, 150, 30);
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                if (hp != null) {
                    hp.setLocationRelativeTo(null);
                    hp.setVisible(true);
                } else {
                    
                    System.err.println("Former HomePage instance is null.");
                }
            }
        });
        contentPane.add(btnGoBack);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public BuyPartsPage(ActionListener actionListener) {
		
	}

	private void loadPartsData() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                partsData.add(parts);
                if (Integer.parseInt(parts[0]) <= 11) {
                    innerTableModel.addRow(new Object[] { parts[0], parts[1], parts[2], "In Stock", parts[3] });
                } else {
                    outerTableModel.addRow(new Object[] { parts[0], parts[1], parts[2], "In Stock", parts[3] });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePartsData(String partId, int newStock) {
        for (String[] part : partsData) {
            if (part[0].equals(partId)) {
                part[3] = String.valueOf(newStock);
                break;
            }
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] part : partsData) {
                bw.write(String.join(",", part));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writePurchaseHistory(double total) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(purchaseHistoryFilePath, true))) {
            for (String[] item : cartItems) {
                bw.write(username + "," + item[0] + "," + item[1] + "," + item[2] + ",Total: $" + total + ",Date: " + dtf.format(now));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreditCardDialog(double total) {
        JDialog dialog = new JDialog(this, "Enter Credit Card Information", true);
        dialog.getContentPane().setLayout(new GridLayout(5, 2));

        JLabel lblCardNumber = new JLabel("Card Number:");
        JTextField txtCardNumber = new JTextField();
        txtCardNumber.setDocument(new JTextFieldLimit(16)); 
        JLabel lblExpiryDate = new JLabel("Expiry Date (MM/YY):");
        JTextField txtExpiryDate = new JTextField();
        txtExpiryDate.setDocument(new JTextFieldLimit(5)); 
        JLabel lblCVV = new JLabel("CVV:");
        JTextField txtCVV = new JTextField();
        txtCVV.setDocument(new JTextFieldLimit(3)); 

        JButton btnPurchase = new JButton("Purchase");
        JButton btnCancel = new JButton("Cancel");

        btnPurchase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cardNumber = txtCardNumber.getText();
                String expiryDate = txtExpiryDate.getText();
                String cvv = txtCVV.getText();

                if (cardNumber.length() != 16 || !cardNumber.matches("\\d{16}")) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid 16-digit card number.");
                } else if (!expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}")) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid expiry date in MM/YY format.");
                } else if (cvv.length() != 3 || !cvv.matches("\\d{3}")) {
                    JOptionPane.showMessageDialog(dialog, "Please enter a valid 3-digit CVV.");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Purchase successful! Total: $" + total);
                    cartItems.clear();
                    cartModel.setRowCount(0);
                    dialog.dispose();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.getContentPane().add(lblCardNumber);
        dialog.getContentPane().add(txtCardNumber);
        dialog.getContentPane().add(lblExpiryDate);
        dialog.getContentPane().add(txtExpiryDate);
        dialog.getContentPane().add(lblCVV);
        dialog.getContentPane().add(txtCVV);
        dialog.getContentPane().add(btnPurchase);
        dialog.getContentPane().add(btnCancel);

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    
    class JTextFieldLimit extends javax.swing.text.PlainDocument {
        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, javax.swing.text.AttributeSet attr) throws javax.swing.text.BadLocationException {
            if (str == null)
                return;

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

}