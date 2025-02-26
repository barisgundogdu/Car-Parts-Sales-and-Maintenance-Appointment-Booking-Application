package denemeprojes;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdminPage extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public AdminPage() {
        setTitle("Admin Page - Manage Users");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 620, 400);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][] {}, new String[] { "ID", "First Name", "Last Name", "Username", "Password" }) {
            public boolean isCellEditable(int row, int column) {
                return column != 0; 
            }
        };
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        JButton btnLoadUsers = new JButton("Load Users");
        btnLoadUsers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadUsers();
            }
        });
        contentPane.add(btnLoadUsers, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);

        JButton btnSaveChanges = new JButton("Save Changes");
        btnSaveChanges.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveChanges();
            }
        });
        panel.add(btnSaveChanges);

        JButton btnDeleteUser = new JButton("Delete User");
        btnDeleteUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteUser();
            }
        });
        panel.add(btnDeleteUser);

        JButton btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        panel.add(btnLogout);

        JButton btnViewReport = new JButton("View Report");
        btnViewReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewReport();
            }
        });
        panel.add(btnViewReport);

        JButton btnShowAppointments = new JButton("Show Appointments");
        btnShowAppointments.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAppointments();
            }
        });
        panel.add(btnShowAppointments);

        loadUsers();
    }

    private void loadUsers() {
        tableModel.setRowCount(0); 
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveChanges() {
        try (PrintWriter pw = new PrintWriter(new FileWriter("users.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    sb.append(tableModel.getValueAt(i, j));
                    if (j < tableModel.getColumnCount() - 1) {
                        sb.append(",");
                    }
                }
                pw.println(sb.toString());
            }
            JOptionPane.showMessageDialog(this, "Changes saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error saving changes.");
        }
    }

    private void deleteUser() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.");
        }
    }

    private void logout() {
        dispose();
        LoginPage loginPage = new LoginPage(null, null, null);
        loginPage.setVisible(true);
    }

    private void viewReport() {
        AdminReportPage reportPage = new AdminReportPage();
        reportPage.setVisible(true);
    }

    private void showAppointments() {
        JFrame appointmentFrame = new JFrame("Appointments");
        appointmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        appointmentFrame.setSize(800, 400);

        DefaultTableModel appointmentTableModel = new DefaultTableModel(new Object[][] {}, new String[] { "User", "Day", "Time", "Brand", "Vehicle Year", "Vehicle Mileage", "Maintenance Type" });
        JTable appointmentTable = new JTable(appointmentTableModel);
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        appointmentFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        try (BufferedReader br = new BufferedReader(new FileReader("appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0]; 
                String day = data[1];
                String time = data[2]; 
                String brand = data[3];
                String vehicleYear = data[4];
                String vehicleMileage = data[5];
                String maintenanceType = data[6];

                String[] rowData = { username, day, time, brand, vehicleYear, vehicleMileage, maintenanceType };
                appointmentTableModel.addRow(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        appointmentFrame.setVisible(true);
    }
}