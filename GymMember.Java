import java.io.Serializable;

public abstract class GymMember implements Serializable {
   private static final long serialVersionUID = 1L;
   protected final int id;
   protected String name;
   protected String location;
   protected String phone;
   protected String email;
   protected String gender;
   protected String DOB;
   protected String membershipStartDate;
   protected int attendance;
   protected double loyaltyPoints;
   protected boolean activeStatus;

   public GymMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate) {
      this.id = id;
      this.name = name;
      this.location = location;
      this.phone = phone;
      this.email = email;
      this.gender = gender;
      this.DOB = DOB;
      this.membershipStartDate = membershipStartDate;
      this.attendance = 0;
      this.loyaltyPoints = (double)0.0F;
      this.activeStatus = false;
   }

   public int getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getLocation() {
      return this.location;
   }

   public String getPhone() {
      return this.phone;
   }

   public String getEmail() {
      return this.email;
   }

   public String getGender() {
      return this.gender;
   }

   public String getDOB() {
      return this.DOB;
   }

   public String getMembershipStartDate() {
      return this.membershipStartDate;
   }

   public int getAttendance() {
      return this.attendance;
   }

   public double getLoyaltyPoints() {
      return this.loyaltyPoints;
   }

   public boolean isActiveStatus() {
      return this.activeStatus;
   }

   public abstract void markAttendance();

   public void activateMembership() {
      this.activeStatus = true;
   }

   public void deactivateMembership() {
      if (!this.activeStatus) {
         System.out.println("Membership is already inactive.");
      } else {
         this.activeStatus = false;
      }
   }

   public void resetMember() {
      this.activeStatus = false;
      this.attendance = 0;
      this.loyaltyPoints = (double)0.0F;
   }

   public void display() {
      System.out.println("Member Details:");
      int var10001 = this.id;
      System.out.println("ID: " + var10001);
      String var1 = this.name;
      System.out.println("Name: " + var1);
      var1 = this.location;
      System.out.println("Location: " + var1);
      var1 = this.phone;
      System.out.println("Phone: " + var1);
      var1 = this.email;
      System.out.println("Email: " + var1);
      var1 = this.gender;
      System.out.println("Gender: " + var1);
      var1 = this.DOB;
      System.out.println("Date of Birth: " + var1);
      var1 = this.membershipStartDate;
      System.out.println("Membership Start Date: " + var1);
      int var8 = this.attendance;
      System.out.println("Attendance: " + var8);
      double var9 = this.loyaltyPoints;
      System.out.println("Loyalty Points: " + var9);
      System.out.println("Active Status: " + (this.activeStatus ? "Active" : "Inactive"));
   }

   public String toString() {
      return String.format("GymMember[ID=%d, Name=%s, Active=%s]", this.id, this.name, this.activeStatus ? "Yes" : "No");
   }

   protected abstract void setActiveStatus(boolean var1);
}
