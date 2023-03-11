import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class AttendanceManagementSystem extends JFrame implements ActionListener {

    private JPanel panel;
    private JLabel titleLabel, nameInputLabel, dateInputLabel, attendanceInputLabel;
    private JTextField nameField, dateField, attendanceField;
    private JButton addButton, viewButton;
    private JTextArea displayArea;

    private HashMap<String, HashMap<String, String>> attendanceData;

    public AttendanceManagementSystem() {
        super("Attendance Management System");

        attendanceData = new HashMap<>();

        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        titleLabel = new JLabel("Attendance Management System");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(titleLabel);

        nameInputLabel = new JLabel("Name:");
        panel.add(nameInputLabel);
        nameField = new JTextField(20);
        panel.add(nameField);

        dateInputLabel = new JLabel("Date (dd/mm/yyyy):");
        panel.add(dateInputLabel);
        dateField = new JTextField(10);
        panel.add(dateField);

        attendanceInputLabel = new JLabel("Attendance (Present/Absent):");
        panel.add(attendanceInputLabel);
        attendanceField = new JTextField(10);
        panel.add(attendanceField);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        panel.add(addButton);

        viewButton = new JButton("View Attendance");
        viewButton.addActionListener(this);
        panel.add(viewButton);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        panel.add(scrollPane);

        add(panel);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String name = nameField.getText();
            String date = dateField.getText();
            String attendance = attendanceField.getText();

            if (!attendanceData.containsKey(name)) {
                attendanceData.put(name, new HashMap<>());
            }

            attendanceData.get(name).put(date, attendance);
            displayArea.setText(name + " " + date + " " + attendance + " added successfully.");
            nameField.setText("");
            dateField.setText("");
            attendanceField.setText("");
        } else if (e.getSource() == viewButton) {
            StringBuilder sb = new StringBuilder();

            for (Map.Entry<String, HashMap<String, String>> entry : attendanceData.entrySet()) {
                String name = entry.getKey();
                HashMap<String, String> attendance = entry.getValue();

                sb.append(name).append(":\n");

                for (Map.Entry<String, String> attEntry : attendance.entrySet()) {
                    String date = attEntry.getKey();
                    String status = attEntry.getValue();

                    sb.append("  ").append(date).append(" - ").append(status).append("\n");
                }
            }

            displayArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        AttendanceManagementSystem AttendanceManagementSystem = new AttendanceManagementSystem();
    }
}
