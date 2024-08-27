import java.util.Scanner;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
import java.util.LinkedList;

class Medicines {
    String medicine_name;
    double price;
    int quantity;
    String m_date;
    String e_date;
    String description;

    Medicines(String medicine_name, double price, int quantity, String m_date, String e_date, String description) {
        this.medicine_name = medicine_name;
        this.price = price;
        this.quantity = quantity;
        this.m_date = m_date;
        this.e_date = e_date;
        this.description = description;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getM_date() {
        return m_date;
    }

    public String getE_date() {
        return e_date;
    }

    public String getDescription() {
        return description;
    }
}

class Purchase {
    private String medicine_name;
    int p_quantity;

    Purchase(String medicine_name, int p_quantity) {
        this.medicine_name = medicine_name;
        this.p_quantity = p_quantity;
    }

    public String getMedicineName() {
        return medicine_name;
    }

    public int getQuantity() {
        return p_quantity;
    }

    Purchase() {
    }
}

class Main {
    static Connection con = null;
    static Scanner sc = new Scanner(System.in);
    static Statement st = null;
    static String sql = null;
    static PreparedStatement pst = null;
    static ResultSet rs = null;
    static LinkedList<Medicines> medi = new LinkedList<>();
    static LinkedList<Purchase> pur = new LinkedList<>();
    static double amount = 0;

    public static void main(String[] args) throws Exception {

        String dburl = "jdbc:mysql://localhost:3306/pms";
        String dbuser = "root";
        String dbpass = "";
        String driverName = "com.mysql.jdbc.Driver";

        Class.forName(driverName);
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
        st = con.createStatement();

        // sql = "select * from medicines";
        // rs = st.executeQuery(sql);
        // while (rs.next()) {
        //     Medicines m = new Medicines(rs.getString(2), rs.getDouble(3), rs.getInt(4), rs.getString(5),
        //             rs.getString(6), rs.getString(7));
        //     medi.add(m);
        // }

        System.out.println("\n===========================================================");
        System.out.println("========>> Welcome to Pharmacy Management System <<========");
        System.out.println("===========================================================\n");

        int choice, r;
        System.out.println("\nCould you please confirm who are you?");
        System.out.println("Press [1] if you are Customer.");
        System.out.println("Press [2] if you are Admin.");
        System.out.print("Enter your choice => ");
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                boolean f1 = true;
                while (f1) {
                    System.out.println("\n\nPress [1] for Registration.");
                    System.out.println("Press [2] for Login.");
                    System.out.println("Press [3] for Exit.");
                    System.out.print("Enter your choice => ");
                    int ch1 = sc.nextInt();
                    switch (ch1) {
                        case 1:
                            System.out.print("\nEnter first name: ");
                            String c_fname = sc.next();
                            System.out.print("Enter last name: ");
                            String c_lname = sc.next();
                            System.out.print("Enter Gender: ");
                            String c_gender = sc.next();
                            System.out.print("Enter birth date(YYYY/MM/DD): ");
                            String c_birthdate = sc.next();
                            System.out.print("Enter email: ");
                            String c_email = sc.next();
                            System.out.print("Enter address: ");
                            String c_address = sc.next();
                            String customer_pass = "";
                            String c_phone_no = "";
                            String cust_id = "";
                            int t2 = 3;
                            int cnt0 = 0;
                            int cnt1 = 0;
                            int cnt2 = 0;
                            while (t2 >= 1) {
                                System.out.print("Enter Mobile no. : ");
                                sc.nextLine();
                                c_phone_no = sc.nextLine();
                                if (c_phone_no.length() == 10) {
                                    cnt1++;
                                    for (int i = 0; i < 10; i++) {
                                        if ((c_phone_no.charAt(i) >= 48) && (c_phone_no.charAt(i) <= 57)) {
                                            cnt1++;
                                        } else {
                                            System.out.print("");
                                        }
                                    }
                                    if (cnt1 == 11) {
                                        cnt0 = 1;
                                        break;
                                    } else {
                                        System.out.println("\nInvalid Mobile No.!");
                                        t2--;
                                    }
                                } else {
                                    System.out.println("\nInvalid number!");
                                    System.out.println("Please enter 10 digit number!");
                                    t2--;
                                }
                            }
                            if (cnt0 == 1) {
                                Random ran = new Random();
                                int iy = ran.nextInt(9);
                                int iz = ran.nextInt(9);
                                cust_id = c_fname + "" + iy + "" + iz;
                                System.out.println(
                                        "\nGenerated your Customer-Id : " + cust_id);
                                int t3 = 2;
                                while (t3 >= 1) {
                                    int cnt3 = 0;
                                    System.out.print("\nCreate Password : ");
                                    customer_pass = sc.nextLine();
                                    int lower_case = 0;
                                    int upper_case = 0;
                                    int digit_case = 0;
                                    for (int i = 0; i < customer_pass.length(); i++) {
                                        for (char j = 65; j <= 90; j++) {
                                            if (customer_pass.charAt(i) == j) {
                                                upper_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (upper_case == 1) {
                                        cnt3++;
                                    }
                                    for (int i = 0; i < customer_pass.length(); i++) {
                                        for (char j = 97; j <= 122; j++) {
                                            if (customer_pass.charAt(i) == j) {
                                                lower_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (lower_case == 1) {
                                        cnt3++;
                                    }
                                    for (int i = 0; i < customer_pass.length(); i++) {
                                        for (char j = 48; j <= 57; j++) {
                                            if (customer_pass.charAt(i) == j) {
                                                digit_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (digit_case == 1) {
                                        cnt3++;
                                    }
                                    if ((customer_pass.length() >= 8) && (customer_pass.length() <= 15)) {
                                        cnt3++;
                                    }
                                    System.out.print("Enter Confirm Password : ");
                                    String con_pass = sc.nextLine();
                                    if ((con_pass.equals(customer_pass)) && (cnt3 == 4)) {
                                        cnt2++;
                                        System.out.println("\nYour password must be created.");
                                        break;
                                    } else {
                                        System.out.println("\nIncorrect Password!");
                                        System.out.println("Please try again\n");
                                    }
                                }
                            } else {
                                System.out.print("");
                            }

                            if (cnt2 == 1) {
                                sql = "insert into customer values('" + cust_id + "','" + c_fname + "','" + c_lname
                                        + "','" + c_gender + "','" + c_birthdate + "'," + c_phone_no + ",'" + c_email
                                        + "','" + customer_pass + "','" + c_address + "')";
                                r = st.executeUpdate(sql);
                                if (r > 0) {
                                    System.out.println("\nCustomer registration successfully.");
                                    customerOperations(con);
                                } else {
                                    System.out.println("Customer registration fail.");
                                }
                            } else {
                                System.out.println("");
                            }
                            f1 = false;
                            break;

                        case 2:
                            int t4 = 3;
                            do {
                                System.out.print("\nEnter Customer id: ");
                                String check_cust_id = sc.next();
                                System.out.print("Enter password: ");
                                String check_cust_pass = sc.next();
                                sql = "select * from customer where c_id=? AND c_pass=?";
                                pst = con.prepareStatement(sql);
                                pst.setString(1, check_cust_id);
                                pst.setString(2, check_cust_pass);
                                rs = pst.executeQuery();
                                if (rs.next()) {
                                    System.out.println("\nLogin successfully.");
                                    break;
                                } else {
                                    System.out.println("\nInvalid customer ID or password. Login failed.");
                                    t4--;
                                }
                            } while (t4 != 0);
                            if (t4 == 0) {
                                System.out.println("\nYour account lock for 1 hour.");
                            } else {
                                customerOperations(con);
                            }
                            f1 = false;
                            break;
                        case 3:
                            f1 = false;
                            break;
                        default:
                            System.out.println("Enter valid choice");
                            break;
                    }
                }
                break;

            case 2:
                boolean f2 = true;
                while (f2) {
                    System.out.println("\n\nPress [1] for Registration.");
                    System.out.println("Press [2] for Login.");
                    System.out.println("Press [3] for Exit.");
                    System.out.print("Enter your choice => ");
                    int ch1 = sc.nextInt();
                    switch (ch1) {
                        case 1:
                            System.out.print("\nEnter first name: ");
                            String a_fname = sc.next();
                            System.out.print("Enter last name: ");
                            String a_lname = sc.next();
                            System.out.print("Enter Gender: ");
                            String a_gender = sc.next();
                            System.out.print("Enter birth date(YYYY/MM/DD): ");
                            String a_birthdate = sc.next();
                            System.out.print("Enter email: ");
                            String a_email = sc.next();
                            System.out.print("Enter address: ");
                            String a_address = sc.next();
                            String admin_pass = "";
                            String a_phone_no = "";
                            String admin_id = "";
                            int t5 = 3;
                            int cnt4 = 0;
                            int cnt5 = 0;
                            int cnt6 = 0;
                            while (t5 >= 1) {
                                System.out.print("Enter Mobile no. : ");
                                sc.nextLine();
                                a_phone_no = sc.nextLine();
                                if (a_phone_no.length() == 10) {
                                    cnt5++;
                                    for (int i = 0; i < 10; i++) {
                                        if ((a_phone_no.charAt(i) >= 48) && (a_phone_no.charAt(i) <= 57)) {
                                            cnt5++;
                                        } else {
                                            System.out.print("");
                                        }
                                    }
                                    if (cnt5 == 11) {
                                        cnt4 = 1;
                                        break;
                                    } else {
                                        System.out.println("\nInvalid Mobile No.!");
                                        t5--;
                                    }
                                } else {
                                    System.out.println("\nInvalid number!");
                                    System.out.println("Please enter 10 digit number!");
                                    t5--;
                                }
                            }
                            if (cnt4 == 1) {
                                Random ran = new Random();
                                int iy = ran.nextInt(9);
                                int iz = ran.nextInt(9);
                                admin_id = a_fname + "" + iy + "" + iz;
                                System.out.println(
                                        "\nGenerated your Admin-Id : " + admin_id);
                                int t6 = 2;
                                while (t6 >= 1) {
                                    int cnt7 = 0;
                                    System.out.print("\nCreate Password : ");
                                    admin_pass = sc.nextLine();
                                    int lower_case = 0;
                                    int upper_case = 0;
                                    int digit_case = 0;
                                    for (int i = 0; i < admin_pass.length(); i++) {
                                        for (char j = 65; j <= 90; j++) {
                                            if (admin_pass.charAt(i) == j) {
                                                upper_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (upper_case == 1) {
                                        cnt7++;
                                    }
                                    for (int i = 0; i < admin_pass.length(); i++) {
                                        for (char j = 97; j <= 122; j++) {
                                            if (admin_pass.charAt(i) == j) {
                                                lower_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (lower_case == 1) {
                                        cnt7++;
                                    }
                                    for (int i = 0; i < admin_pass.length(); i++) {
                                        for (char j = 48; j <= 57; j++) {
                                            if (admin_pass.charAt(i) == j) {
                                                digit_case = 1;
                                                break;
                                            }
                                        }
                                    }
                                    if (digit_case == 1) {
                                        cnt7++;
                                    }
                                    if ((admin_pass.length() >= 8) && (admin_pass.length() <= 15)) {
                                        cnt7++;
                                    }
                                    System.out.print("Enter Confirm Password : ");
                                    String con_pass = sc.nextLine();
                                    if ((con_pass.equals(admin_pass)) && (cnt7 == 4)) {
                                        cnt6++;
                                        System.out.println("\nYour password must be created.");
                                        break;
                                    } else {
                                        System.out.println("\nIncorrect Password!");
                                        System.out.println("Please try again\n");
                                    }
                                }
                            } else {
                                System.out.print("");
                            }

                            if (cnt6 == 1) {
                                sql = "insert into admin values('" + admin_id + "','" + a_fname + "','" + a_lname
                                        + "','" + a_gender + "','" + a_birthdate + "'," + a_phone_no + ",'" + a_email
                                        + "','" + admin_pass + "','" + a_address + "')";
                                r = st.executeUpdate(sql);
                                if (r > 0) {
                                    System.out.println("\nAdmin registration successfully.");
                                    adminOperations(con);
                                } else {
                                    System.out.println("Admin registration fail.");
                                }
                            } else {
                                System.out.println("");
                            }
                            f2 = false;
                            break;

                        case 2:
                            int t7 = 3;
                            do {
                                System.out.print("\nEnter Admin id: ");
                                String check_admin_id = sc.next();
                                System.out.print("Enter password: ");
                                String check_admin_pass = sc.next();
                                sql = "select * from admin where a_id=? AND admin_pass=?";
                                pst = con.prepareStatement(sql);
                                pst.setString(1, check_admin_id);
                                pst.setString(2, check_admin_pass);
                                rs = pst.executeQuery();
                                if (rs.next()) {
                                    System.out.println("\nLogin successfully.");
                                    break;
                                } else {
                                    System.out.println("\nInvalid Admin ID or password. Login failed.");
                                    t7--;
                                }
                            } while (t7 != 0);
                            if (t7 == 0) {
                                System.out.println("\nYour account lock for 1 hour.");
                            } else {
                                adminOperations(con);
                            }
                            f2 = false;
                            break;

                        case 3:
                            f2 = false;
                            break;
                        default:
                            System.out.println("Enter valid choice");
                            break;
                    }
                }
                break;
            default:
                System.out.println("Enter valid choice.");
                break;
        }
        sc.close();
    }

    static void customerOperations(Connection con) throws Exception {
        int customerChoice;
        boolean z = true;
        do {
            System.out.println("\nCustomer Operations:");
            System.out.println("Press [1] for Buy Medicine.");
            System.out.println("Press [2] for Show Medicine.");
            System.out.println("Press [3] for Remove Medicine.");
            System.out.println("Press [4] for Request Medicine.");
            System.out.println("Press [5] Make a Payment.");
            System.out.println("Press [6] Exit.");
            System.out.print("Enter your choice: ");
            customerChoice = sc.nextInt();

            switch (customerChoice) {
                case 1:
                    amount += buyMedicine(con);
                    break;
                case 2:
                    showMedicine();
                    break;
                case 3:
                    amount -= removeMedicine(con);
                    break;
                case 4:
                    requestMedicine(con);
                    break;
                case 5:
                    bill(amount);
                    z = false;
                    break;
                case 6:
                    z = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (z);
    }

    static void adminOperations(Connection con) throws Exception {
        int adminChoice;
        do {
            System.out.println("\nAdmin Operations:");
            System.out.println("Press [1] for Stock Check of Medicine.");
            System.out.println("Press [2] for Add New Medicine.");
            System.out.println("Press [3] for Remove Medicine.");
            System.out.println("Press [4] for Update the quantity of Medicines.");
            System.out.println("Press [5] for Check Customer Requests.");
            System.out.println("Press [6] for Exit.");
            System.out.print("Enter your choice: ");
            adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1:
                    stockCheckMedicine(con);
                    break;
                case 2:
                    addNewMedicine(con);
                    break;
                case 3:
                    removeOldMedicine(con);
                    break;
                case 4:
                    updateQuantity(con);
                    break;
                case 5:
                    checkCustomerRequests(con);
                    break;
                case 6:
                    System.out.println("Exiting admin operations.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (adminChoice != 6);
    }

    static double getPrice(String name) {
        for (Medicines m : medi) {
            if (m.getMedicine_name().equalsIgnoreCase(name)) {
                return m.getPrice();
            }
        }
        return 0;
    }

    static int getQuan(String name) {
        for (Medicines m : medi) {
            if (m.getMedicine_name().equalsIgnoreCase(name)) {
                return m.getQuantity();
            }
        }
        return 0;
    }

    static double buyMedicine(Connection con) throws Exception {
        System.out.print("\nEnter medicine name: ");
        String m_name = sc.next();

        System.out.print("Enter quantity to buy: ");
        int buy_quantity = sc.nextInt();

        Medicines m1 = null;
        for (Medicines medic : medi) {
            if (medic.getMedicine_name().equalsIgnoreCase(m_name)) {
                m1 = medic;
                break;
            }
        }
        if (m1 == null) {
            System.out.println("\nMedicine not found.");
            return 0;
        } else {
            int available = m1.getQuantity();

            if (buy_quantity > available) {
                System.out.println("\nNot enough quantity available for purchase.");
                return 0;
            } else {
                m1.quantity -= buy_quantity;
                double totalMedicinePrice = m1.getPrice() * buy_quantity;

                sql = "update medicines set m_quantity = ? where m_name = ?";
                pst = con.prepareStatement(sql);
                pst.setInt(1, available - buy_quantity);
                pst.setString(2, m_name);
                pst.executeUpdate();

                System.out.println("\nMedicine(s) bought successfully.");
                pur.add(new Purchase(m_name, buy_quantity));
                return totalMedicinePrice;
            }
        }
    }

    static void showMedicine() {
        System.out.println("\n===== Available Medicines =====");
        for (Medicines medicine : medi) {
            System.out.println("\nMedicine Name: " + medicine.getMedicine_name());
            System.out.println("Price: " + medicine.getPrice());
            System.out.println("Quantity: " + medicine.getQuantity());
            System.out.println("Manufacturing Date: " + medicine.getM_date());
            System.out.println("Expiry Date: " + medicine.getE_date());
            System.out.println("Description: " + medicine.getDescription());
            System.out.println("-----------------------");
        }
    }

    static double removeMedicine(Connection con) throws Exception {
        System.out.print("\nEnter the name of the medicine to remove: ");
        String medicine_remove = sc.next();
        System.out.print("Enter the quantity of the medicine to remove: ");
        int quan = sc.nextInt();

        boolean found = false;
        double medicineCost = 0;
        for (Purchase p1 : pur) {
            if (p1.getMedicineName().equalsIgnoreCase(medicine_remove)) {
                if (quan == p1.getQuantity()) {
                    medicineCost = quan * getPrice(medicine_remove);
                    pur.remove(p1);
                    sql = "update medicines set m_quantity = ? where m_name = ?";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, getQuan(medicine_remove) + quan);
                    pst.setString(2, medicine_remove);
                    pst.executeUpdate();
                    System.out.println("Medicine removed successfully.");
                    found = true;
                    break;
                } else if (quan < p1.getQuantity()) {
                    p1.p_quantity -= quan;
                    medicineCost = quan * getPrice(medicine_remove);
                    sql = "update medicines set m_quantity = ? where m_name = ?";
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, getQuan(medicine_remove) + quan);
                    pst.setString(2, medicine_remove);
                    pst.executeUpdate();
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            System.out.println("Medicine not found.");
            return 0;
        }
        return medicineCost;
    }

    static void requestMedicine(Connection con) throws Exception {
        System.out.print("Enter your customer ID: ");
        String c_id = sc.next();
        System.out.print("Enter medicine name you want to request: ");
        String r_medicine_name = sc.next();
        System.out.print("Enter the quantity you want to request: ");
        int r_quantity = sc.nextInt();

        boolean medicineExists = false;
        for (Medicines medicine : medi) {
            if (medicine.getMedicine_name().equalsIgnoreCase(r_medicine_name)) {
                medicineExists = true;
                break;
            }
        }

        if (medicineExists) {
            System.out.println("The requested medicine is available.");
            return;
        }

        sql = "insert into request_list (c_id, med_name, med_quan, status) values (?, ?, ?, ?)";
        pst = con.prepareStatement(sql);
        pst.setString(1, c_id);
        pst.setString(2, r_medicine_name);
        pst.setInt(3, r_quantity);
        pst.setString(4, "Pending");
        int r = pst.executeUpdate();

        if (r > 0) {
            System.out.println("Request submitted successfully.");
        } else {
            System.out.println("Failed to submit the request.");
        }
    }

    static void bill(double a) {
        System.out.println("\n-------------: Medicine Bill :-------------");
        for (Purchase p : pur) {
            double price = getPrice(p.getMedicineName());
            double total = price * p.getQuantity();
            System.out.println("medicine name: " + p.getMedicineName() + " price: " + price + " Quantity: "
                    + p.getQuantity() + " Total: " + total);
        }
        System.out.println("\n-------------------------------------------");
        System.out.println("\nTotal amount: " + a);
        double totalWithGST = a + (a * 0.04);
        System.out.println("Total amount with GST(4%): " + totalWithGST);
        System.out.println("Thank you for shopping! Exiting customer operations.");

    }

    static void stockCheckMedicine(Connection con) throws Exception {
        st = con.createStatement();
        sql = "select * from medicines";
        rs = st.executeQuery(sql);
        System.out.println("=======================================");
        while (rs.next()) {
            String medicineName = rs.getString("m_name");
            int quantity = rs.getInt("m_quantity");
            System.out.println("\nMedicine: " + medicineName + ", Quantity: " + quantity);
        }
        System.out.println("\n=======================================");
    }

    static void addNewMedicine(Connection con) throws Exception {
        st = con.createStatement();
        System.out.print("\nEnter medicine name: ");
        String m_name = sc.next();
        System.out.print("Enter medicine price: ");
        double m_price = sc.nextDouble();
        System.out.print("Enter medicine Quantity: ");
        int m_quantity = sc.nextInt();
        System.out.print("Enter manufacturing date: ");
        String m_date = sc.next();
        System.out.println("Enter expaired date: ");
        String e_date = sc.next();
        System.out.println("Enter descripition: ");
        String descr = sc.next();
        Medicines m = new Medicines(m_name, m_price, m_quantity, m_date, e_date, descr);
        medi.add(m);

        sql = "insert into medicines(m_name,m_price,m_quantity,m_mdate,m_edate,m_descr) values('" + m_name + "',"
                + m_price + "," + m_quantity + ",'" + m_date + "','" + e_date + "','" + descr + "')";
        int r = st.executeUpdate(sql);
        if (r > 0) {
            System.out.println("Add medicine successfully");
        } else {
            System.out.println("failed");
        }

    }

    static void removeOldMedicine(Connection con) throws Exception {
        System.out.print("\nEnter the name of the medicine to remove: ");
        String remove_medicine_name = sc.next();

        Medicines m1 = null;
        for (Medicines medicine : medi) {
            if (medicine.getMedicine_name().equalsIgnoreCase(remove_medicine_name)) {
                m1 = medicine;
                break;
            }
        }
        if (m1 != null) {
            medi.remove(m1);
            sql = "delete from medicines where m_name = ?";
            pst = con.prepareStatement(sql);
            pst.setString(1, remove_medicine_name);
            int r = pst.executeUpdate();
            if (r > 0) {
                System.out.println("Medicine removed.");
            }
        } else {
            System.out.println("Medicine not found.");
        }
    }

    static void updateQuantity(Connection con) throws Exception {
        System.out.print("\nEnter the name of the medicine to update quantity: ");
        String medi_name = sc.next();
        System.out.print("Enter the quantity of the medicine to update: ");
        int up_quan = sc.nextInt();

        sql = "update medicines set m_quantity = ? where m_name = ?";
        pst = con.prepareStatement(sql);
        pst.setInt(1, up_quan + getQuan(medi_name));
        pst.setString(2, medi_name);
        int r = pst.executeUpdate();
        if (r > 0) {
            System.out.println("\nMedicine Quantity Updated.");
        } else {
            System.out.println("Medicine not Exist.");
        }
    }

    static void checkCustomerRequests(Connection con) throws Exception {
        st = con.createStatement();
        sql = "select * from request_list";
        rs = st.executeQuery(sql);

        System.out.println("\n======= Customer Requests =======");
        while (rs.next()) {
            int requestId = rs.getInt(1);
            String customerId = rs.getString(2);
            String medicineName = rs.getString(3);
            int quantity = rs.getInt(4);
            String status = rs.getString(5);

            System.out.println("Request ID: " + requestId);
            System.out.println("Customer ID: " + customerId);
            System.out.println("Medicine Name: " + medicineName);
            System.out.println("Quantity: " + quantity);
            System.out.println("Status: " + status);
            System.out.println("------------------------------");
        }
        System.out.println("=============================");

        System.out.print("\nDo you want to add a new medicine? (yes/no): ");
        String ch = sc.next();

        if (ch.equalsIgnoreCase("yes")) {
            addNewMedicine(con);
            System.out.print("\nEnter the request ID to mark as Completed: ");
            int complete = sc.nextInt();
            sql = "update request_list set status = 'Completed' where req_id = ?";
            pst = con.prepareStatement(sql);
            pst.setInt(1, complete);
            int r = pst.executeUpdate();
            if (r > 0) {
                System.out.println("\nRequest marked as Completed.");
            } else {
                System.out.println("Failed to update the request status.");
            }
        }
    }
}