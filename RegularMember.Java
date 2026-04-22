public class RegularMember extends GymMember {
   private final int attendanceLimit = 30;
   private boolean isEligibleForUpgrade = false;
   private String removalReason = "";
   private String referralSource;
   private String plan;
   private double price;

   public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource, String plan) {
      super(id, name, location, phone, email, gender, DOB, membershipStartDate);
      this.referralSource = referralSource;
      this.plan = plan.toLowerCase();
      this.price = this.getPlanPrice(this.plan);
      if (this.price == (double)-1.0F) {
         this.plan = "basic";
         this.price = (double)6500.0F;
      }

   }

   public int getAttendanceLimit() {
      return this.attendanceLimit;
   }

   public boolean isEligibleForUpgrade() {
      return this.isEligibleForUpgrade;
   }

   public String getRemovalReason() {
      return this.removalReason;
   }

   public String getReferralSource() {
      return this.referralSource;
   }

   public String getPlan() {
      return this.plan;
   }

   public double getPrice() {
      return this.price;
   }

   public void markAttendance() {
      ++this.attendance;
      this.loyaltyPoints += (double)5.0F;
      System.out.println("Attendance: " + this.attendance);
      if (this.attendance >= this.attendanceLimit) {
         this.isEligibleForUpgrade = true;
         System.out.println("Eligible for upgrade: " + this.isEligibleForUpgrade);
      }

   }

   public double getPlanPrice(String plan) {
      switch (plan.toLowerCase()) {
         case "basic" -> {
            return (double)6500.0F;
         }
         case "standard" -> {
            return (double)12500.0F;
         }
         case "deluxe" -> {
            return (double)18500.0F;
         }
         case "premium" -> {
            return (double)25000.0F;
         }
         default -> {
            return (double)-1.0F;
         }
      }
   }

   public String upgradePlan(String newPlan) {
      if (newPlan != null && !newPlan.trim().isEmpty()) {
         newPlan = newPlan.trim().toLowerCase();
         System.out.println("Current Plan: " + this.plan);
         System.out.println("Requested Upgrade to Plan: " + newPlan);
         System.out.println("Attendance: " + this.attendance);
         System.out.println("Eligible for Upgrade: " + this.isEligibleForUpgrade);
         if (this.attendance >= this.attendanceLimit) {
            this.isEligibleForUpgrade = true;
         }

         if (!this.isEligibleForUpgrade) {
            return "Not eligible for plan upgrade. Attendance must reach " + this.attendanceLimit + ". Current attendance: " + this.attendance;
         } else if (this.plan.equalsIgnoreCase(newPlan)) {
            return "Already subscribed to the " + newPlan + " plan.";
         } else {
            double newPrice = this.getPlanPrice(newPlan);
            if (newPrice == (double)-1.0F) {
               return "Invalid plan selected. Available plans: basic, standard, deluxe, premium.";
            } else {
               this.plan = newPlan;
               this.price = newPrice;
               System.out.println("Upgrade successful to " + newPlan + " plan. New price: " + newPrice);
               return "Successfully upgraded to " + newPlan + " plan. New price: " + newPrice;
            }
         }
      } else {
         return "Invalid plan input.";
      }
   }

   public void revertRegularMember(String removalReason) {
      super.resetMember();
      this.isEligibleForUpgrade = false;
      this.plan = "basic";
      this.price = (double)6500.0F;
      this.removalReason = removalReason;
   }

   protected void setActiveStatus(boolean status) {
      this.activeStatus = status;
   }

   public void display() {
      super.display();
      System.out.println("Plan: " + this.plan);
      System.out.println("Price: " + this.price);
      System.out.println("Referral Source: " + this.referralSource);
      System.out.println("Eligible for Upgrade: " + this.isEligibleForUpgrade);
      if (!this.removalReason.isEmpty()) {
         System.out.println("Removal Reason: " + this.removalReason);
      }

   }
}
