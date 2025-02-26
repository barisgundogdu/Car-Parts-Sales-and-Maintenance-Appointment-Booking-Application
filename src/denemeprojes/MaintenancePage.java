package denemeprojes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class MaintenancePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> hourComboBox;
    private JComboBox<String> brandsComboBox;
    private JTextField yearTextField;
    private JTextField mileageTextField;
    private JComboBox<String> maintenanceTypeComboBox;
    private HashMap<String, String> reservedAppointments;
    private HomePage homePage;
    private LoginPage loginPage;
    private String username;

    public MaintenancePage(HomePage homePage, LoginPage loginPage, String username) {
        this.homePage = homePage;
        this.loginPage = loginPage;
        this.username = username;
        setTitle("Maintenance Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1024, 603);
        contentPane = new JPanel();
        contentPane.setBackground(Color.DARK_GRAY);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel("Maintenance Appointment");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(10, 11, 340, 27);
        contentPane.add(lblTitle);

        JLabel lblDay = new JLabel("Select Day:");
        lblDay.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblDay.setForeground(Color.WHITE);
        lblDay.setBounds(356, 78, 100, 48);
        contentPane.add(lblDay);

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        dayComboBox.setBounds(495, 78, 208, 48);
        contentPane.add(dayComboBox);

        JLabel lblHour = new JLabel("Select Hour:");
        lblHour.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblHour.setForeground(Color.WHITE);
        lblHour.setBounds(350, 134, 106, 45);
        contentPane.add(lblHour);

        String[] hours = {"9:00", "10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        hourComboBox = new JComboBox<>(hours);
        hourComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        hourComboBox.setBounds(495, 136, 208, 45);
        contentPane.add(hourComboBox);

        JLabel lblBrand = new JLabel("Car Brand:");
        lblBrand.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblBrand.setForeground(Color.WHITE);
        lblBrand.setBounds(364, 189, 92, 41);
        contentPane.add(lblBrand);

        String[] brands = {"BMW", "VOLKSWAGEN", "AUDI", "MERCEDES-BENZ", "PORSCHE", "LAND ROVER", "HONDA", "NISSAN", "TOYOTA", "SUZUKI", "RENAULT", "FIAT", "DACIA", "ALFA ROMEO", "JEEP"};
        brandsComboBox = new JComboBox<>(brands);
        brandsComboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
        brandsComboBox.setSize(208, 39);
        brandsComboBox.setLocation(495, 191);
        contentPane.add(brandsComboBox);

        JLabel lblYear = new JLabel("Car Year:");
        lblYear.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblYear.setForeground(Color.WHITE);
        lblYear.setBounds(372, 241, 84, 41);
        contentPane.add(lblYear);

        yearTextField = new JTextField();
        yearTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        yearTextField.setBounds(495, 243, 208, 41);
        contentPane.add(yearTextField);

        JLabel lblMileage = new JLabel("Mileage:");
        lblMileage.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMileage.setForeground(Color.WHITE);
        lblMileage.setBounds(381, 292, 75, 41);
        contentPane.add(lblMileage);

        mileageTextField = new JTextField();
        mileageTextField.setFont(new Font("Tahoma", Font.BOLD, 14));
        mileageTextField.setBounds(495, 294, 208, 41);
        contentPane.add(mileageTextField);

        JLabel lblMaintenanceType = new JLabel("Maintenance Type:");
        lblMaintenanceType.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblMaintenanceType.setForeground(Color.WHITE);
        lblMaintenanceType.setBounds(296, 346, 160, 41);
        contentPane.add(lblMaintenanceType);

        String[] maintenanceTypes = {"Regular Maintenance", "Heavy Maintenance"};
        maintenanceTypeComboBox = new JComboBox<>(maintenanceTypes);
        maintenanceTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
        maintenanceTypeComboBox.setBounds(495, 345, 208, 41);
        contentPane.add(maintenanceTypeComboBox);

        JButton btnCheckAvailability = new JButton("Check Availability");
        btnCheckAvailability.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnCheckAvailability.setBounds(427, 432, 196, 30);
        contentPane.add(btnCheckAvailability);
        btnCheckAvailability.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDay = dayComboBox.getSelectedItem().toString();
                String selectedHour = hourComboBox.getSelectedItem().toString();
                if (isAvailable(selectedDay, selectedHour)) {
                    int result = JOptionPane.showConfirmDialog(contentPane, "Selected time is available. Do you want to make an appointment?", "Availability", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        reserveAppointment(username, selectedDay, selectedHour);
                        JOptionPane.showMessageDialog(contentPane, "Appointment has been made successfully.");
                    }
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Selected time is not available. Please choose another time.");
                }
            }
        });

        reservedAppointments = new HashMap<>();
        loadAppointments();
        
        JButton btnGoBack = new JButton("Go Back");
        btnGoBack.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnGoBack.setBounds(766, 496, 208, 32);
        contentPane.add(btnGoBack);
        btnGoBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homePage.setVisible(true);
                dispose();
            }
        });
        
        JButton btnLogout = new JButton("Logout");
        btnLogout.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnLogout.setBounds(30, 496, 160, 32);
        contentPane.add(btnLogout);
        btnLogout.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
                 if (loginPage != null) {
                     loginPage.setVisible(true);
                 }
                 dispose();
             }
         });

         setVisible(true);
         setLocationRelativeTo(null);
     }

     private boolean isAvailable(String day, String hour) {
         return !reservedAppointments.containsKey(day + " " + hour);
     }

     private void reserveAppointment(String username, String day, String hour) {
         reservedAppointments.put(day + " " + hour, hour);

         String brand = brandsComboBox.getSelectedItem().toString();
         String year = yearTextField.getText();
         String mileage = mileageTextField.getText();
         String maintenanceType = maintenanceTypeComboBox.getSelectedItem().toString();

         try (BufferedWriter writer = new BufferedWriter(new FileWriter("appointments.txt", true))) {
             
             writer.write(username + "," + day + "," + hour + "," + brand + "," + year + "," + mileage + "," + maintenanceType);
             writer.newLine();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     private void loadAppointments() {
         try (BufferedReader reader = new BufferedReader(new FileReader("appointments.txt"))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 String[] parts = line.split(",");
                 if (parts.length >= 3) {
                     String dayHour = parts[1] + " " + parts[2];
                     reservedAppointments.put(dayHour, parts[2]);
                 }
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
    