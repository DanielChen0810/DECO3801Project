package Main.Entities;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * SQL static class
 */
public class SqlDriver {

    private static Connection myConn;
    public final static String NOTFOUND = "NOT FOUND";
    private static Statement myStmt;

    public static void setUp() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&useSSL=false&allowPublicKeyRetrieval=true", "root", "1234");
            System.out.println("Accessing DB with credentiels: root + 1234");
            myStmt = myConn.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static boolean insertDonation(UUID donationID, String donorName, int amount, String donationDate, String charity) {
        setUp();
        try {
            String query = "insert INTO Donation VALUES (" + "'" + donationID + "'" + "," + "'" + donorName + "'" + "," + "'" +
                    amount + "'" + "," + "'" + donationDate + "'" + "," + "'" + charity + "'" + ")";
            System.out.println("Executing sql query :" + query);
            int updateDo = myStmt.executeUpdate(query);
            if (updateDo != 1) {
                return false;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }


    public static List<Donation> selectAllDonation() {
        List<Donation> info = new ArrayList<>();
        setUp();
        try {
            ResultSet myRs = myStmt.executeQuery("select * from Donation");
            System.out.println("Getting all donations");


            while (myRs.next()) {
                String name = myRs.getString("DonorName");
                Donation donation = new Donation(Integer.parseInt(myRs.getString("Amount")), name, myRs.getString("charity"));
                donation.setDonationID(UUID.fromString(myRs.getString("DonationId")));
                donation.setDonorName(name);
                donation.setDonationDate(LocalDateTime.parse(myRs.getString("DonationDate")));
                info.add(donation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * Get a list of all the current charities
     *
     * @return An array list of all charities
     */
    public static List<Charity> selectAllCharity() {
        List<Charity> info = new ArrayList<>();
        setUp();
        try {
            ResultSet myRs = myStmt.executeQuery("select * from charities");
            System.out.println("Getting all charities");


            while (myRs.next()) {

                Charity charity = new Charity(myRs.getString("name"), myRs.getString("username"), myRs.getString("password"));
                info.add(charity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    /**
     * Gets a donation from the database based upon its unique id.
     *
     * @param DonationId The id of the donation you want
     * @return A donation object containing the info of the donation
     */
    public static Donation selectDonation(String DonationId) {

        setUp();
        try {
            ResultSet myRs = myStmt.executeQuery("select * from Donation where " +
                    "DonationId = " + DonationId + "");

            if (myRs.next()) {
                String name = myRs.getString("DonorName");
                Donation donation = new Donation(Integer.parseInt(myRs.getString("Amount")), name, myRs.getString("charity"));
                donation.setDonationID(UUID.fromString(myRs.getString("DonationId")));
                donation.setDonorName(name);
                donation.setDonationDate(LocalDateTime.parse(myRs.getString("DonationDate")));
                return donation;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean updateDonorName(int DonationId, String newName) {
        setUp();
        try {
            ResultSet myRs = myStmt.executeQuery("select * from Donation where " +
                    "DonationId = " + DonationId + "");
            if (!myRs.next()) {
                return false;
            }
            int updateRs = myStmt.executeUpdate("UPDATE Donation SET DonorName = '" + newName + "' Where DonationId = " + DonationId + "");
            if (updateRs != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateAmount(int DonationId, int amount) {
        setUp();
        try {
            ResultSet myRs = myStmt.executeQuery("select * from Donation where " +
                    "DonationId = " + DonationId + "");
            if (!myRs.next()) {
                return false;
            }
            amount += Integer.parseInt(myRs.getString("Amount"));

            int updateRs =
                    myStmt.executeUpdate("UPDATE Donation SET Amount = " + amount +
                            " Where DonationId = " + DonationId);
            if (updateRs != 1) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Adds a charity spending row to the charity spending table
     *
     * @param id Spending ID
     * @param amount Spending amount
     * @param description Description about what the spending was for
     * @param charity Which charity spent the money
     * @return success of operation
     */

    public static boolean updateCharitySpending(String id, int amount, String description, String charity) {
        setUp();
        try {
            System.out.println("INSERT into charity_spending (id, amount, description, charity) VALUE('" + id + "','" +
                    amount + "','" + description + "'" + charity + "')");
            myStmt.execute("INSERT into charity_spending (id, amount, description, charity) VALUE('" + id + "','" +
                    amount + "','" + description + "','" + charity + "')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Get the charity spending of a specific charity
     *
     * @param charity The charity we are checking
     * @return A list lift of charity payment objects of the charity input.
     */
    public static List<CharityPayment> selectCharitySpending(String charity) {
        setUp();
        List<CharityPayment> charityPayments = new ArrayList<>();
        try {
            System.out.println("select * from charity_spending where " +
                    "charity = \"" + charity + "\"");
            ResultSet myRs = myStmt.executeQuery("select * from charity_spending where " +
                    "charity = \"" + charity + "\";");


            while (myRs.next()) {
                CharityPayment charityPayment = new CharityPayment(myRs.getString("id"), charity, myRs.getString("amount"), myRs.getString("description"));
                charityPayments.add(charityPayment);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return charityPayments;
    }
}
