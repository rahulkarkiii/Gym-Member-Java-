/**
 * Represents a regular gym member.
 * Contains additional attributes such as referral source, plan type,
 * attendance, price and loyalty points.
 * Inherits the common member details from GymMember.
 * 
 * @author Rahul Karki
 * version 1.0
 */

public class RegularMember extends GymMember {
    private final int attendanceLimit;
    private boolean isEligibleForUpgrade;
    private String removalReason;
    private String referralSource;
    private String plan;
    private double price;

    // Constructor
    public RegularMember(int id, String name, String location, String phone, String email,
                         String gender, String DOB, String membershipStartDate,
                         String referralSource, String plan) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.attendanceLimit = 30;
        this.isEligibleForUpgrade = false;
        this.removalReason = "";
        this.referralSource = referralSource;
        this.plan = plan.toLowerCase();
        this.price = getPlanPrice(this.plan);
        if (this.price == -1) {
            this.plan = "basic";
            this.price = 6500;
        }
    }

    // Accessor methods
    public int getAttendanceLimit() {
        return attendanceLimit;
    }

    public boolean isEligibleForUpgrade() {
        return isEligibleForUpgrade;
    }

    public String getRemovalReason() {
        return removalReason;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public String getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }
/**
 * Increases attendance by 1 and updates loyalty points accordigly.
 */
    // Override abstract method to mark attendance
    @Override
    public void markAttendance() {
        this.attendance++;
        this.loyaltyPoints += 5;
        System.out.println("Attendance: " + this.attendance);
        if (this.attendance >= attendanceLimit) {
            this.isEligibleForUpgrade = true;
                System.out.println("Eligible for upgrade: " + this.isEligibleForUpgrade);
        }
    }

  public double getPlanPrice(String plan) {
    switch (plan.toLowerCase()) {
        case "basic":
            return 6500;
        case "standard":
            return 12500;
        case "deluxe":
            return 18500;
        case "premium":  // Added premium plan here
            return 25000;  // Assuming the price for premium
        default:
            return -1;
    }
}
public String upgradePlan(String newPlan) {
    if (newPlan == null || newPlan.trim().isEmpty()) {
        return "Invalid plan input.";
    }

    newPlan = newPlan.trim().toLowerCase();

    // Debug output
    System.out.println("Current Plan: " + this.plan);
    System.out.println("Requested Upgrade to Plan: " + newPlan);
    System.out.println("Attendance: " + this.attendance);
    System.out.println("Eligible for Upgrade: " + this.isEligibleForUpgrade);

    // Check if eligible
    if (this.attendance >= attendanceLimit) {
        this.isEligibleForUpgrade = true;
    }

    if (!isEligibleForUpgrade) {
        return "Not eligible for plan upgrade. Attendance must reach " + attendanceLimit +
               ". Current attendance: " + this.attendance;
    }

    // If same plan
    if (this.plan.equalsIgnoreCase(newPlan)) {
        return "Already subscribed to the " + newPlan + " plan.";
    }

    // Check price of new plan
    double newPrice = getPlanPrice(newPlan);
    if (newPrice == -1) {
        return "Invalid plan selected. Available plans: basic, standard, deluxe, premium.";
    }

    // Perform upgrade
    this.plan = newPlan;
    this.price = newPrice;

    // Final debug output
    System.out.println("Upgrade successful to " + newPlan + " plan. New price: " + newPrice);
    return "Successfully upgraded to " + newPlan + " plan. New price: " + newPrice;
}

    // Revert member status
    public void revertRegularMember(String removalReason) {
        super.resetMember();
        this.isEligibleForUpgrade = false;
        this.plan = "basic";
        this.price = 6500;
        this.removalReason = removalReason;
    }

    // Implement abstract method from GymMember
    @Override
    protected void setActiveStatus(boolean status) {
        this.activeStatus = status;
    }

    // Override display method to include RegularMember details
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        System.out.println("Referral Source: " + referralSource);
        System.out.println("Eligible for Upgrade: " + isEligibleForUpgrade);
        if (!removalReason.isEmpty()) {
            System.out.println("Removal Reason: " + removalReason);
        }
    }
}
