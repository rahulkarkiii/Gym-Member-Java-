// Reference:
// Oracle Java Swing Tutorial – https://docs.oracle.com/javase/tutorial/uiswing/
// Used for creating JFrame, JPanel, and event handling

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class GymGUI extends JFrame {
    private ArrayList<GymMember> members;
    
    // Text Fields
    private JTextField idField;
    private JTextField nameField;
    private JTextField locationField;
    private JTextField phoneField;
    private JTextField emailField;
    private JTextField referralSourceField;
    private JTextField paidAmountField;
    private JTextField removalReasonField;
    private JTextField trainerNameField;
    private JTextField priceField;
    
    private JTextField premiumChargeField;
    private JTextField discountAmountField;
    
    // Combo Boxes for DOB and Membership Start Date
    private JComboBox<String> dobYearComboBox;
    private JComboBox<String> dobMonthComboBox;
    private JComboBox<String> dobDayComboBox;
    private JComboBox<String> msYearComboBox;
    private JComboBox<String> msMonthComboBox;
    private JComboBox<String> msDayComboBox;
    
    // Radio Buttons for Gender
    private JRadioButton maleRadio;
    private JRadioButton femaleRadio;
    private ButtonGroup genderGroup;
    
    // Combo Box for Plans
    private JComboBox<String> planComboBox;
    
    // Display area
    private JTextArea displayArea;
    
    // Constructor
    public GymGUI() {
        members = new ArrayList<>();
        setupGUI();
    }
    
    private void setupGUI() {
        setTitle("Gym Membership Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Create main panel with GridBagLayout for better control
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Initialize components
        initializeComponents();
        
        // Create member details panel with titled border
        JPanel memberDetailsPanel = new JPanel(new GridBagLayout());
        memberDetailsPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Member Details"));
        
        // Add components to member details panel
        // First Column
        gbc.gridx = 0;
        gbc.gridy = 0;
        memberDetailsPanel.add(new JLabel("ID:"), gbc);
        
        gbc.gridx = 1;
        memberDetailsPanel.add(idField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        memberDetailsPanel.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        memberDetailsPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        memberDetailsPanel.add(new JLabel("Location:"), gbc);
        
        gbc.gridx = 1;
        memberDetailsPanel.add(locationField, gbc);
        
        // Second Column
        gbc.gridx = 2;
        gbc.gridy = 0;
        memberDetailsPanel.add(new JLabel("Phone:"), gbc);
        
        gbc.gridx = 3;
        memberDetailsPanel.add(phoneField, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 1;
        memberDetailsPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 3;
        memberDetailsPanel.add(emailField, gbc);
        
        // Gender Radio Buttons
        gbc.gridx = 2;
        gbc.gridy = 2;
        memberDetailsPanel.add(new JLabel("Gender:"), gbc);
        
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        gbc.gridx = 3;
        memberDetailsPanel.add(genderPanel, gbc);
        
        // DOB Combo Boxes
        gbc.gridx = 0;
        gbc.gridy = 3;
        memberDetailsPanel.add(new JLabel("Membership Start Date"), gbc);
        
        JPanel msPanel = new JPanel();
        msPanel.add(msYearComboBox);
        msPanel.add(msMonthComboBox);
        msPanel.add(msDayComboBox);
        gbc.gridx = 1;
        memberDetailsPanel.add(msPanel, gbc);
        
        // Membership Start Date Combo Boxes
        gbc.gridx = 2;
        gbc.gridy = 3;
        memberDetailsPanel.add(new JLabel("Date of Birth:"), gbc);
        
        JPanel dobPanel = new JPanel();
        dobPanel.add(dobYearComboBox);
        dobPanel.add(dobMonthComboBox);
        dobPanel.add(dobDayComboBox);
        gbc.gridx = 3;
        memberDetailsPanel.add(dobPanel, gbc);
        
        // Add member details panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        mainPanel.add(memberDetailsPanel, gbc);
        
        // Create regular member panel with titled border
        JPanel premiumMemberPanel = new JPanel(new GridBagLayout());
        premiumMemberPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Premium Member"));
        
        // Add components to regular member panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        premiumMemberPanel.add(new JLabel("Trainer's Name:"), gbc);
        
        gbc.gridx = 1;
        premiumMemberPanel.add(trainerNameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        premiumMemberPanel.add(new JLabel("Paid Amount:"), gbc);
        
        gbc.gridx = 1;
        premiumMemberPanel.add(paidAmountField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        premiumMemberPanel.add(new JLabel("Premium Charge:"), gbc);
        
        gbc.gridx = 1;
        premiumMemberPanel.add(premiumChargeField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        premiumMemberPanel.add(new JLabel("Discount Amount:"), gbc);
        
        gbc.gridx = 1;
        premiumMemberPanel.add(discountAmountField, gbc);
        
        // Add regular member panel to main panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(premiumMemberPanel, gbc);
        
        // Create premium member panel with titled border
        JPanel regularMemberPanel = new JPanel(new GridBagLayout());
        regularMemberPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Regular Member"));
        
        // Add components to premium member panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        regularMemberPanel.add(new JLabel("Referral Source:"), gbc);
        
        gbc.gridx = 1;
        regularMemberPanel.add(referralSourceField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        regularMemberPanel.add(new JLabel("Removal Reason:"), gbc);
        
        gbc.gridx = 1;
        regularMemberPanel.add(removalReasonField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        regularMemberPanel.add(new JLabel("Plan:"), gbc);
        
        gbc.gridx = 1;
        regularMemberPanel.add(planComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        regularMemberPanel.add(new JLabel("Price:"), gbc);
        
        gbc.gridx = 1;
        regularMemberPanel.add(priceField, gbc);
        
        // Add premium member panel to main panel
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(regularMemberPanel, gbc);
        
        // Add buttons panel below member sections
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, gbc);
        
        // Add display area below buttons
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        
        // Create a panel for the display area with border
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Display"));
        displayPanel.add(scrollPane, BorderLayout.CENTER);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        mainPanel.add(displayPanel, gbc);
        
        // Non-editable fields panel
        JPanel pricePanel = new JPanel(new GridLayout(1, 1, 10, 0));
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        mainPanel.add(pricePanel, gbc);
        
        // Add main panel to frame
        add(mainPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
    }
    
    private void initializeComponents() {
        // Initialize text fields
        idField = new JTextField(10);
        nameField = new JTextField(20);
        locationField = new JTextField(20);
        phoneField = new JTextField(15);
        emailField = new JTextField(20);
        referralSourceField = new JTextField(20);
        removalReasonField = new JTextField(20);
        trainerNameField = new JTextField(20);
        priceField = new JTextField(10);
        
        // Initialize non-editable fields
        premiumChargeField = new JTextField(10);
        premiumChargeField.setBackground(Color.WHITE);
        premiumChargeField.setText("50000");
        premiumChargeField.setEditable(false);


        discountAmountField = new JTextField(10);
        discountAmountField.setBackground(Color.WHITE);
        discountAmountField.setText("0");
        discountAmountField.setEditable(false);

        paidAmountField = new JTextField(10);
        paidAmountField.setBackground(Color.WHITE);
        paidAmountField.setText("0");
    
        
        // Initialize gender radio buttons
        maleRadio = new JRadioButton("Male");
        femaleRadio = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        
        // Initialize plan combo box
        String[] plans = {"Basic", "Standard", "Deluxe"};
        planComboBox = new JComboBox<>(plans);
        
        // Initialize DOB combo boxes
        String[] years = new String[100];
        for (int i = 0; i < 100; i++) {
            years[i] = String.valueOf(2024 - i);
        }
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        String[] days = new String[31];
        for (int i = 0; i < 31; i++) {
            days[i] = String.format("%02d", i + 1);
        }
        
        dobYearComboBox = new JComboBox<>(years);
        dobMonthComboBox = new JComboBox<>(months);
        dobDayComboBox = new JComboBox<>(days);
        
        msYearComboBox = new JComboBox<>(years);
        msMonthComboBox = new JComboBox<>(months);
        msDayComboBox = new JComboBox<>(days);
    }
    
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createEtchedBorder(), "Action"));
        
        JButton addRegularMember = new JButton("Add Regular Member");
        JButton addPremiumMember = new JButton("Add Premium Member");
        JButton activateMembership = new JButton("Activate Membership");
        JButton deactivateMembership = new JButton("Deactivate Membership");
        JButton markAttendance = new JButton("Mark Attendance");
        JButton upgradePlan = new JButton("Upgrade Plan");
        JButton CalculateDiscount = new JButton("Calculate Discount");
        JButton payAmount = new JButton("Pay Amount");
        JButton revertRegularMember = new JButton("Revert Regular Member");
        JButton revertPremiumMember = new JButton("Revert Premium Member");
        JButton displayMembers = new JButton("Display Members");
        JButton SaveToFile = new JButton("Save to File");
        JButton ReadFromFile = new JButton("Read from File");
        JButton clear = new JButton("Clear");


        
        buttonPanel.add(addRegularMember);
        buttonPanel.add(addPremiumMember);
        buttonPanel.add(activateMembership);
        buttonPanel.add(deactivateMembership);
        buttonPanel.add(markAttendance);
        buttonPanel.add(upgradePlan);
        buttonPanel.add(CalculateDiscount); 
        buttonPanel.add(payAmount);
        buttonPanel.add(revertRegularMember);
        buttonPanel.add(revertPremiumMember);
        buttonPanel.add(SaveToFile);
        buttonPanel.add(ReadFromFile);
        buttonPanel.add(displayMembers);
        buttonPanel.add(clear);
        
        // Add action listeners
        addRegularMember.addActionListener(e -> addRegularMember());
        addPremiumMember.addActionListener(e -> addPremiumMember());
        activateMembership.addActionListener(e -> activateMembership());
        deactivateMembership.addActionListener(e -> deactivateMembership());
        markAttendance.addActionListener(e -> markAttendance());
        upgradePlan.addActionListener(e -> upgradePlan());  
        CalculateDiscount.addActionListener(e -> calculateDiscount());
        payAmount.addActionListener(e -> payAmount());
        revertRegularMember.addActionListener(e -> revertRegularMember());
        revertPremiumMember.addActionListener(e -> revertPremiumMember());
        displayMembers.addActionListener(e -> displayMembers());
        SaveToFile.addActionListener(e -> saveToFile());
       ReadFromFile.addActionListener(e -> readFromFile());
        clear.addActionListener(e -> clearFields());
        
        return buttonPanel;
    }
    
    private boolean validateFields(boolean isPremium) {
        // Check if the name field is empty
        String name = nameField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Name cannot be empty!");
            return false;
        }
    
        // Check if name contains only letters and spaces
        if (!name.matches("^[a-zA-Z\\s]+$")) {
            JOptionPane.showMessageDialog(this, "Name should contain only letters and spaces, not numbers!");
            return false;
        }
        
        // Check if phone contains only numbers and has valid length
        String phone = phoneField.getText().trim();
        if (!phone.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(this, "Phone number should contain exactly 10 digits!");
            return false;
        }
        
        // Check if any required field is empty
        if (idField.getText().trim().isEmpty() ||
            name.isEmpty() ||
            locationField.getText().trim().isEmpty() ||
            phone.isEmpty() ||
            emailField.getText().trim().isEmpty() ||
            !(maleRadio.isSelected() || femaleRadio.isSelected())) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
            return false;
        }
        
        // Additional checks for Regular and Premium members
        if (!isPremium) {
            // Check if referral source and plan are selected for regular member
            if (referralSourceField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter referral source for regular member!");
                return false;
            }
            if (planComboBox.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a plan for regular member!");
                return false;
            }
        } else {
            // Check if trainer's name is provided for premium member
            if (trainerNameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter trainer's name for premium member!");
                return false;
            }
        }
        
        return true;
    }
    
    
    private void addPremiumMember() {
        try {
            // First check if all required fields are filled
            if (idField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                locationField.getText().trim().isEmpty() ||
                phoneField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                (!maleRadio.isSelected() && !femaleRadio.isSelected()) ||
                trainerNameField.getText().trim().isEmpty() ||
                paidAmountField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
                return;
            }
              // Check if location contains numbers
        String location = locationField.getText().trim();
        if (location.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Location cannot contain numbers!");
            return;
        }

        // Check if email contains numbers
        String email = emailField.getText().trim();
        if (email.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Email cannot contain numbers!");
            return;
        }
            // Only proceed with other validations if the fields have content
            if (!validateFields(true)) {
                return;
            }
            
            // Validate and convert numeric fields (ID and Paid Amount)
            int id;
            double paidAmount;
            try {
                id = Integer.parseInt(idField.getText().trim());
                if (id <= 0) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive number!");
                    return;
                }
                paidAmount = Double.parseDouble(paidAmountField.getText().trim());
                if (paidAmount < 0) {
                    JOptionPane.showMessageDialog(this, "Paid amount must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid positive numbers for ID and Paid Amount!");
                return;
            }
            
            // Check for duplicate ID
            if (isDuplicateId(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
            
            // Get text field values
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            
            // Ensure valid date selection (check if combo boxes have valid selections)
            String dob = String.format("%s-%s-%s", 
                dobYearComboBox.getSelectedItem(),
                dobMonthComboBox.getSelectedItem(),
                dobDayComboBox.getSelectedItem());
            
            String startDate = String.format("%s-%s-%s", 
                msYearComboBox.getSelectedItem(),
                msMonthComboBox.getSelectedItem(),
                msDayComboBox.getSelectedItem());
    
            String trainerName = trainerNameField.getText().trim();
    
            // Create the PremiumMember object
            PremiumMember member = new PremiumMember(id, name, location, phone, email,
            gender, dob, startDate, trainerName);

        // Add the member to the list
        members.add(member);
        JOptionPane.showMessageDialog(this, "Premium Member added successfully!");
        clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding premium member: " + ex.getMessage());
        }
    }
    
    private boolean isDuplicateId(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void addRegularMember() {
        try {
            // First check if all required fields are empty
            if (idField.getText().trim().isEmpty() ||
                nameField.getText().trim().isEmpty() ||
                locationField.getText().trim().isEmpty() ||
                phoneField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() ||
                (!maleRadio.isSelected() && !femaleRadio.isSelected()) ||
                referralSourceField.getText().trim().isEmpty() ||
                planComboBox.getSelectedIndex() == -1) { // Make sure plan is selected
                JOptionPane.showMessageDialog(this, "Please fill in all required fields!");
                return;
            }
             // Check if location contains numbers
        String location = locationField.getText().trim();
        if (location.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Location cannot contain numbers!");
            return;
        }

        // Check if email contains numbers
        String email = emailField.getText().trim();
        if (email.matches(".*\\d.*")) {
            JOptionPane.showMessageDialog(this, "Email cannot contain numbers!");
            return;
        }
    
            // Only proceed with other validations if the fields have content
            if (!validateFields(false)) {
                return;
            }
    
            // Validate and convert numeric fields
            int id;
            try {
                id = Integer.parseInt(idField.getText().trim());
                if (id <= 0) {
                    JOptionPane.showMessageDialog(this, "ID must be a positive number!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for ID!");
                return;
            }
    
            // Check for duplicate ID
            if (isDuplicateId(id)) {
                JOptionPane.showMessageDialog(this, "Member ID already exists!");
                return;
            }
    
            // Get text field values
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String gender = maleRadio.isSelected() ? "Male" : "Female";
            
            // Ensure the date combo boxes have selected values
            String dob = String.format("%s-%s-%s", 
                dobYearComboBox.getSelectedItem(),
                dobMonthComboBox.getSelectedItem(),
                dobDayComboBox.getSelectedItem());
            
            String startDate = String.format("%s-%s-%s", 
                msYearComboBox.getSelectedItem(),
                msMonthComboBox.getSelectedItem(),
                msDayComboBox.getSelectedItem());
            
            String referralSource = referralSourceField.getText().trim();
            String plan = planComboBox.getSelectedItem().toString();

            RegularMember member = new RegularMember(id, name, location, phone, email,
                                                     gender, dob, startDate, referralSource, plan);
            
            // Add the member to the list (or database)
            members.add(member); // Assuming `members` is an ArrayList or similar collection
            
            JOptionPane.showMessageDialog(this, "Regular Member added successfully!");
            clearFields(); // Assuming clearFields properly clears all fields
    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding regular member: " + ex.getMessage());
        }
    }
    
    private void activateMembership() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
            if (input == null || input.trim().isEmpty()) {
                return; // User cancelled or didn't enter anything
            }
    
            int id = Integer.parseInt(input.trim());
            GymMember member = findMemberById(id);
    
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found!");
                return;
            }
    
            if (member.isActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Member is already active.");
            } else {
                member.activateMembership();
                JOptionPane.showMessageDialog(this, "Membership activated successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error activating membership: " + ex.getMessage());
        }
    }
    
    
    private void deactivateMembership() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
            if (input == null || input.trim().isEmpty()) {
                return; // User cancelled or left it blank
            }
    
            int id = Integer.parseInt(input.trim());
            GymMember member = findMemberById(id);
    
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found!");
                return;
            }
    
            if (!member.isActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Member is already inactive.");
            } else {
                member.deactivateMembership();
                JOptionPane.showMessageDialog(this, "Membership deactivated successfully!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deactivating membership: " + ex.getMessage());
        }
    }
    
    
    private void markAttendance() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
            if (input == null || input.trim().isEmpty()) {
                return; // User cancelled or left blank
            }
    
            int id = Integer.parseInt(input.trim());
            GymMember member = findMemberById(id);
    
            if (member == null) {
                JOptionPane.showMessageDialog(this, "Member not found!");
                return;
            }
    
            if (!member.isActiveStatus()) {
                JOptionPane.showMessageDialog(this, "Cannot mark attendance for inactive member!");
                return;
            }
    
            member.markAttendance(); // Method should handle attendance + loyalty logic
            JOptionPane.showMessageDialog(this, "Attendance marked successfully!");
            JOptionPane.showMessageDialog(this, "Current Attendance: " + member.getAttendance());
    
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error marking attendance: " + ex.getMessage());
        }
    }
  private void upgradePlan() {
    try {
        // Get the Member ID from the user
        int id = getMemberIdFromUser();
        if (id == -1) return;  // Exit if invalid ID is entered

        // Find the member by ID
        GymMember member = findMemberById(id);
        if (member == null) {
            JOptionPane.showMessageDialog(this, "Member not found!");
            return;
        }

        // Check if the member is a RegularMember
        if (member instanceof RegularMember) {
            RegularMember regularMember = (RegularMember) member;

            // Let user choose the plan to upgrade to
            String[] planOptions = {"basic", "standard", "deluxe", "premium"};
            String selectedPlan = (String) JOptionPane.showInputDialog(
                    this,
                    "Select new plan to upgrade:",
                    "Plan Upgrade",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    planOptions,
                    regularMember.getPlan() // pre-select current plan
            );

            // If user cancels or closes the dialog
            if (selectedPlan == null) return;

            // Call the upgradePlan() method with user-selected plan
            String upgradeMessage = regularMember.upgradePlan(selectedPlan);
            JOptionPane.showMessageDialog(this, upgradeMessage);

            // If upgraded successfully, refresh the list
            if (upgradeMessage.contains("Successfully upgraded")) {
                members.remove(regularMember);
                members.add(regularMember);  // already modified internally
            }
        } else {
            JOptionPane.showMessageDialog(this, "Only Regular Members can be upgraded.");
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error upgrading plan: " + ex.getMessage());
    }
}
    private int getMemberIdFromUser() {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (input == null || input.trim().isEmpty()) {
            return -1; // User cancelled or left blank
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric ID!");
            return -1;
        }
    }               

    private void revertRegularMember() {
        try {
            String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
            if (input == null || input.trim().isEmpty()) {
                return; // User canceled or left blank
            }
    
            int id = Integer.parseInt(input.trim());
            GymMember member = findMemberById(id);
    
            if (member != null && member instanceof RegularMember) {
                member.resetMember(); // Should reset values like attendance, dues, etc.
               
    
                JOptionPane.showMessageDialog(this, "Regular member reverted successfully! ID: " + id);
    
                clearFields(); // Reset form fields
            } else {
                JOptionPane.showMessageDialog(this, "Regular member not found or not a RegularMember!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid member ID!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reverting regular member: " + ex.getMessage());
        }
    }
    
  public void revertPremiumMember() {
    try {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (input == null || input.trim().isEmpty()) {
            return; // User canceled or left blank
        }

        int id = Integer.parseInt(input.trim());
        GymMember member = findMemberById(id);

        if (member != null && member instanceof PremiumMember) {
            PremiumMember premiumMember = (PremiumMember) member;

            // Revert the member's data
            premiumMember.resetMember();  // Reset both common and premium-specific fields
            
            JOptionPane.showMessageDialog(this, "Premium member reverted successfully! ID: " + id);
            clearFields(); // Clear GUI form inputs
        } else {
            JOptionPane.showMessageDialog(this, "Premium member not found or not a PremiumMember!");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid member ID!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error reverting premium member: " + ex.getMessage());
    }
}


   private void calculateDiscount() {
    try {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (input == null || input.trim().isEmpty()) {
            return; // User canceled or left blank
        }

        int id = Integer.parseInt(input.trim());
        GymMember member = findMemberById(id);

        if (member != null && member instanceof PremiumMember) {
            PremiumMember premiumMember = (PremiumMember) member;
            String message = premiumMember.calculateDiscount(); // FIXED: now captures String message
            JOptionPane.showMessageDialog(this, message);
        } else {
            JOptionPane.showMessageDialog(this, "Premium member not found!");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid member ID!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error calculating discount: " + ex.getMessage());
    }
}

   private void payAmount() {
    try {
        String input = JOptionPane.showInputDialog(this, "Enter Member ID:");
        if (input == null || input.trim().isEmpty()) {
            return; // User canceled or left blank
        }

        int id = Integer.parseInt(input.trim());
        GymMember member = findMemberById(id);

        if (member != null && member instanceof PremiumMember) {
            PremiumMember premiumMember = (PremiumMember) member;

            String amountStr = JOptionPane.showInputDialog(this, "Enter amount to pay:");
            if (amountStr == null || amountStr.trim().isEmpty()) {
                return; // User canceled
            }

            double amount = Double.parseDouble(amountStr.trim());
            String message = premiumMember.payAmount(amount); // FIXED: now calls with amount
            JOptionPane.showMessageDialog(this, message);
        } else {
            JOptionPane.showMessageDialog(this, "Premium member not found!");
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number!");
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error processing payment: " + ex.getMessage());
    }
   }

   // Reference:
// Baeldung Java File I/O – https://www.baeldung.com/java-read-lines-large-file
// Helped in understanding BufferedReader and PrintWriter usage

  private void saveToFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Save Members to File");

    int userSelection = fileChooser.showSaveDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    File file = fileChooser.getSelectedFile();

    try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
        // Write header line
        writer.println("Type,ID,Name,Location,Phone,Email,Gender,DOB,MembershipStartDate,Status," +
                "ReferralSource,Plan,Price,Attendance,LoyaltyPoints,TrainerName,PaidAmount,PremiumCharge,DiscountAmount,PaymentStatus");

        for (GymMember member : members) {
            String type = "";
            String referralSource = "";
            String plan = "";
            String price = "";
            String attendance = "";
            String loyaltyPoints = "";
            String trainerName = "";
            String paidAmount = "";
            String premiumCharge = "";
            String discountAmount = "";
            String paymentStatus = "";

            String status = member.isActiveStatus() ? "Active" : "Inactive";

            if (member instanceof RegularMember) {
                RegularMember rm = (RegularMember) member;
                type = "RegularMember";
                referralSource = rm.getReferralSource();
                plan = rm.getPlan();
                price = String.valueOf(rm.getPrice());
                attendance = String.valueOf(rm.getAttendance());
                loyaltyPoints = String.valueOf(rm.getLoyaltyPoints());
            } else if (member instanceof PremiumMember) {
                PremiumMember pm = (PremiumMember) member;
                type = "PremiumMember";
                trainerName = pm.getPersonalTrainer();
                paidAmount = String.valueOf(pm.getPaidAmount());
                premiumCharge = String.valueOf(pm.getPremiumCharge());
                discountAmount = String.valueOf(pm.getDiscountAmount());
                paymentStatus = pm.isFullPayment() ? "Complete" : "Incomplete";
            }

            writer.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s\n",
                    type,
                    member.getId(),
                    member.getName(),
                    member.getLocation(),
                    member.getPhone(),
                    member.getEmail(),
                    member.getGender(),
                    member.getDOB(),
                    member.getMembershipStartDate(),
                    status,
                    referralSource,
                    plan,
                    price,
                    attendance,
                    loyaltyPoints,
                    trainerName,
                    paidAmount,
                    premiumCharge,
                    discountAmount,
                    paymentStatus);
        }

        JOptionPane.showMessageDialog(this, "Members saved successfully to file!");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving to file: " + e.getMessage());
    }
}
/**
 * Opens and reads gym member dats from CSV file.
 * Display formatted content in a JTextArea with headers and spacing.
 * Uses a file chooser dialog to select file.
 * Validates data and handls formatting dynamically based on column widths.
 */

private void readFromFile() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Open Members File");

    int userSelection = fileChooser.showOpenDialog(this);
    if (userSelection != JFileChooser.APPROVE_OPTION) {
        return;
    }

    File file = fileChooser.getSelectedFile();

    JFrame displayFrame = new JFrame("Gym Members from File");
    displayFrame.setSize(1200, 450);
    displayFrame.setLocationRelativeTo(null);

    JTextArea textArea = new JTextArea();
    textArea.setEditable(false);
    textArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Monospaced font for alignment
    JScrollPane scrollPane = new JScrollPane(textArea);

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String header = reader.readLine(); // Read header line
        if (header == null) {
            JOptionPane.showMessageDialog(this, "File is empty");
            return;
        }

        // Split headers
        String[] headers = header.split(",");

        // Define fixed width for each column (adjust sizes as needed)
        // Make sure widths.length == headers.length
        int[] widths = new int[headers.length];
        // Example: assign default width 15 if you want or customize per header text length + buffer
        for (int i = 0; i < headers.length; i++) {
            int len = headers[i].length();
            widths[i] = Math.max(15, len + 5); // minimum 15, or header length + 5 spaces buffer
        }

        // Build formatted header line with spaces between columns
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < headers.length; i++) {
            builder.append(String.format("%-" + widths[i] + "s", headers[i]));
            builder.append("  "); // two spaces between columns
        }
        builder.append("\n");

        // Calculate total width including spaces
        int totalWidth = 0;
        for (int w : widths) totalWidth += w;
        totalWidth += (headers.length - 1) * 2; // spaces between columns
        builder.append("-".repeat(totalWidth)).append("\n");

        // Read each member line and format accordingly
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",", -1); // -1 to keep trailing empty columns
            if (parts.length != headers.length) {
                // Malformed line, just print raw line or skip
                builder.append(line).append("\n");
                continue;
            }

            for (int i = 0; i < parts.length; i++) {
                builder.append(String.format("%-" + widths[i] + "s", parts[i]));
                builder.append("  "); // two spaces between columns
            }
            builder.append("\n");
        }

        textArea.setText(builder.toString());

    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error reading from file: " + e.getMessage());
    }

    displayFrame.add(scrollPane);
    displayFrame.setVisible(true);
}


    private void displayMembers() {
        displayArea.setText("");  // Clear existing text
        
        if (members.isEmpty()) {
            displayArea.append("No members registered yet.\n");
            return;
        }
        
        // Create a DecimalFormat instance to format prices
        DecimalFormat df = new DecimalFormat("$#.00");
    
        for (GymMember member : members) {
            displayArea.append("----------------------------------------\n");
            displayArea.append("Member ID: " + member.getId() + "\n");
            displayArea.append("Name: " + member.getName() + "\n");
            displayArea.append("Location: " + member.getLocation() + "\n");
            displayArea.append("Phone: " + member.getPhone() + "\n");
            displayArea.append("Email: " + member.getEmail() + "\n");
            displayArea.append("Gender: " + member.getGender() + "\n");
            displayArea.append("Date of Birth: " + member.getDOB() + "\n");
            displayArea.append("Membership Start Date: " + member.getMembershipStartDate() + "\n");
            displayArea.append("Status: " + (member.isActiveStatus() ? "Active" : "Inactive") + "\n");
            
            if (member instanceof RegularMember) {
                RegularMember regularMember = (RegularMember) member;
                displayArea.append("Member Type: Regular Member\n");
                displayArea.append("Referral Source: " + regularMember.getReferralSource() + "\n");
                displayArea.append("Plan: " + regularMember.getPlan() + "\n");
                displayArea.append("Price: " + df.format(regularMember.getPrice()) + "\n");
                displayArea.append("Attendance: " + regularMember.getAttendance() + "\n");  
                displayArea.append("Loyalty Points: " + regularMember.getLoyaltyPoints() + "\n");
            } else if (member instanceof PremiumMember) {
                PremiumMember premiumMember = (PremiumMember) member;
                displayArea.append("Member Type: Premium Member\n");
                displayArea.append("Trainer's Name: " + premiumMember.getPersonalTrainer() + "\n");
                displayArea.append("Paid Amount: " + df.format(premiumMember.getPaidAmount()) + "\n");
                displayArea.append("Premium Charge: " + df.format(premiumMember.getPremiumCharge()) + "\n");
                displayArea.append("Discount Amount: " + df.format(premiumMember.getDiscountAmount()) + "\n");
                displayArea.append("Loyalty Points: " + premiumMember.getLoyaltyPoints() + "\n");
                displayArea.append("Payment Status: " + (premiumMember.isFullPayment() ? "Complete" : "Incomplete") + "\n");

            }
            
            displayArea.append("----------------------------------------\n\n");
        }
    }
    
    private GymMember findMemberById(int id) {
        for (GymMember member : members) {
            if (member.getId() == id) {
                return member;
            }
        }
        return null;  // Return null if no member is found
    }
    
    
    private void clearFields() {
        // Clear text fields
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        phoneField.setText("");
        emailField.setText("");
        referralSourceField.setText("");
        paidAmountField.setText("");
        removalReasonField.setText("");
        trainerNameField.setText("");
        priceField.setText("");  // Consider setting default value like "0.00"
        premiumChargeField.setText("");  // Consider setting default value like "0.00"
        discountAmountField.setText("");  // Consider setting default value like "0.00"
    
        // Clear radio button selections (gender selection)
        genderGroup.clearSelection();
    
        // Reset combo boxes to default values
        dobYearComboBox.setSelectedIndex(0);  // Ensure 0 is a valid default state, otherwise select a neutral state
        dobMonthComboBox.setSelectedIndex(0);  // Consider "Choose Month" as index 0
        dobDayComboBox.setSelectedIndex(0);  // Consider "Choose Day" as index 0
        
        msYearComboBox.setSelectedIndex(0);  // Ensure 0 is a valid default state
        msMonthComboBox.setSelectedIndex(0);  // Consider "Choose Month" as index 0
        msDayComboBox.setSelectedIndex(0);  // Consider "Choose Day" as index 0
    
        // Reset selection of plan combo box (if applicable)
        planComboBox.setSelectedIndex(0);  // Ensure 0 is a neutral state, otherwise provide a placeholder value
    }
    
        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                new GymGUI().setVisible(true); // Create and show the Gym GUI
            });
        }
}