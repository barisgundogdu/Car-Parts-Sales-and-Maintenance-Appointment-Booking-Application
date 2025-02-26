package denemeprojes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AdminReportPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public AdminReportPage() {
        setTitle("Purchase Report - GUNDOGDU OTO TUNING MARKET");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 888, 800);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("PURCHASE REPORT");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(10, 11, 764, 27);
        contentPane.add(lblTitle);

        String[] columnNames = {"Username", "Part ID", "Part Name", "Price", "Total Price", "Date and Time"};

        DefaultTableModel reportModel = new DefaultTableModel(columnNames, 0);
        JTable reportTable = new JTable(reportModel);
        JScrollPane reportScrollPane = new JScrollPane(reportTable);
        reportScrollPane.setBounds(10, 50, 854, 700);
        contentPane.add(reportScrollPane);

        loadPurchaseHistory(reportModel);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void loadPurchaseHistory(DefaultTableModel reportModel) {
        String purchaseHistoryFilePath = "purchase_history.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(purchaseHistoryFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    reportModel.addRow(new Object[]{parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]});
                }
            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminReportPage frame = new AdminReportPage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}