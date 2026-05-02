import java.util.ArrayList;

public class PremiumMember extends GymMember {
    private final double premiumCharge;
    private String personalTrainer;
    private boolean isFullPayment;
    private double paidAmount;
    private double discountAmount;
    private boolean isActive;  // Field to track active status
    private ArrayList<Double> paymentHistory;  // Store payment history

    // Constructor
    public PremiumMember(int id, String name, String location, String phone, String email,
                         String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        
        if (personalTrainer == null || personalTrainer.isEmpty()) {
            throw new IllegalArgumentException("Personal trainer cannot be null or empty");
        }
        
        this.premiumCharge = 50000;
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
        this.isActive = true;  // Set the member as active by default
        this.paymentHistory = new ArrayList<>();  // Initialize payment history
    }

    // Accessor methods
    public double getPremiumCharge() {
        return premiumCharge;
    }

    public String getPersonalTrainer() {
        return personalTrainer;
    }

    public boolean isFullPayment() {
        return isFullPayment;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    // Implementation of abstract method markAttendance
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 10;  // Premium members earn more loyalty points
        
    }

    // Method to pay amount
    public String payAmount(double payment) {
        // Check if payment is valid
        if (payment <= 0) {
            return "Payment must be a positive amount.";
        }

        // Check if payment is already full
        if (this.isFullPayment) {
            return "Payment is already complete. No due amount remaining.";
        }

        double remainingAmount = premiumCharge - this.paidAmount;
        
        // Check if the payment would exceed the charge
        if (this.paidAmount + payment > premiumCharge) {
            return "Payment amount exceeds the premium charge. Maximum payable amount is: " + (premiumCharge - this.paidAmount);
        }

        // Process the payment
        this.paidAmount += payment;
        this.paymentHistory.add(payment);  // Record the payment

        // Check if payment is complete
        this.isFullPayment = (this.paidAmount == premiumCharge);

        // Update active status
        this.isActive = this.isFullPayment;

        // Provide feedback with remaining amount
        remainingAmount = premiumCharge - this.paidAmount;
        if (isFullPayment) {
            return String.format("Payment successful. Remaining amount to be paid: %.2f. Payment is complete!", remainingAmount);
        } else {
            return String.format("Payment successful. Remaining amount to be paid: %.2f", remainingAmount);
        }
    }

    // Method to calculate discount
    public String calculateDiscount() {
        if (this.isFullPayment) {
            this.discountAmount = premiumCharge * 0.10; // 10% discount
            return String.format("Discount calculated successfully. 10%% discount on premium charge. Discount amount: %.2f", this.discountAmount);
        } else {
            return "No discount available. Complete the full payment to avail 10% discount.";
        }
    }

    // Method to revert premium member
    @Override

    public void resetMember() {
        super.resetMember();
        this.isActive = false;  // Set active status to false
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
        if (paymentHistory != null) {
            paymentHistory.clear();  // Clear payment history
        }

        System.out.println("Premium member reverted to initial state.");
    }

    // Override display method
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + String.format("%.2f", paidAmount));
        System.out.println("Payment Status: " + (isFullPayment ? "Complete" : "Incomplete"));
        System.out.println("Active Status: " + (isActive ? "Active" : "Inactive"));
        
        // Calculate and display remaining amount
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount to be Paid: " + String.format("%.2f", remainingAmount));
        
        // Display discount amount only if payment is complete
        if (isFullPayment) {
            System.out.println("Discount Amount: " + String.format("%.2f", discountAmount));
        }
        
        // Show payment history in a concise manner
        if (!paymentHistory.isEmpty()) {
            System.out.println("Payment History: ");
            for (Double payment : paymentHistory) {
                System.out.println(" - " + String.format("%.2f", payment));
            }
        }
    }

    // Implement the setActiveStatus method
    @Override
    protected void setActiveStatus(boolean status) {
        this.isActive = status;
        if (status) {
            System.out.println("Member is now active.");
        } else {
            System.out.println("Member is now inactive.");
        }
    }
}
