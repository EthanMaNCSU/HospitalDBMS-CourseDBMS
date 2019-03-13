import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Acknowledgments: This example is a modification of code provided by Dimitri
 * Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support.
 * Replace all $USER$ with your unity id and $PASSWORD$ with your 9 digit
 * student id or updated password (if changed)
 */

public class CreateTable {
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/xfang7";
    // Put your oracle ID and password here

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        connectToDatabase();

        //dropTables();

        createTables();

        insertData();

        close();

    }



	private static void insertData() {
        try {
//            statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '123456789', 'Jack Sparrow', " +
//					"'1900-01-01', 'M', 119, 'in ward', '0001 The Black Pearl, Raleigh, NC, USA, 27606', '9191234567')");
//			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '234567891', 'Tyrion Lannister', " +
//					"'1900-01-01', 'M', 119, 'completing treatment', '0002 Red Keep, Raleigh, NC, USA, 27606', '9192345678')");
//			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '345678912', 'Jamie Lannister', " +
//					"'1897-01-01', 'M', 122, 'processing treatment plan', '0001 Red Keep, Raleigh, NC, USA, 27606', '9193456789')");
//			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '456789123', 'Cho Akiyama', " +
//					"'1905-01-01', 'M', 114, 'in ward', '0003 Duima St., Raleigh, NC, USA, 27606', '9194567891')");


//			statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Jared Diamond', 119, 'M', " +
//					"'doctor', 'MD', 'Primary Care', '0001 GSG St., Raleigh, NC, USA, 27606', '9195678912', 'doctor')");
//            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Megumi Kato', 22, 'F', " +
//                    "'doctor', 'MD', 'Primary Care', '0001 Aki St., Raleigh, NC, USA, 27606', '9196789123', 'doctor')");
//            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Ezio Auditore', 35, 'M', " +
//                    "'nurse', 'nurse', 'Primary Care', '0001 Giovanni St., Raleigh, NC, USA, 27606', '9197891234', 'nurse')");
//            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Tomoya Aki', 22, 'M', " +
//                    "'billing staff', NULL, 'Primary Care', '0002 Aki St., Raleigh, NC, USA, 27606', '9198912345', 'operator')");


//            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 2, 20.0)");
//            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 1, 10.0)");
//            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 4, 40.0)");
//            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 1, 50.0)");


//            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (2, '0001 The Black Pearl, Raleigh, NC, USA, 27606', " +
//                    "'2019-03-12', 0.0)");
//            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (3, '0002 Red Keep, Raleigh, NC, USA, 27606', " +
//                    "'2019-03-12', 0.0)");
//            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (4, '0001 Red Keep, Raleigh, NC, USA, 27606', " +
//                    "'2019-03-12', 0.0)");
//            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (5, '0003 Duima St., Raleigh, NC, USA, 27606', " +
//                    "'2019-03-12', 0.0)");


//            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 2, 'registration', '2019-03-12')");
//            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 3, 'registration', '2019-03-12')");
//            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 4, 'registration', '2019-03-12')");
//            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 5, 'registration', '2019-03-12')");


//            statement.executeUpdate("INSERT INTO PaymentMethods VALUES (NULL, 2, 1, 'pm_patient_1', '0123456789012345', '0420')");
//            statement.executeUpdate("INSERT INTO PaymentMethods VALUES (NULL, 3, 1, 'pm_patient_2', '1234567890123456', '0420')");
//            statement.executeUpdate("INSERT INTO PaymentMethods VALUES (NULL, 4, 2, 'pm_patient_3', '2345678901234567', '0323')");
//            statement.executeUpdate("INSERT INTO PaymentMethods VALUES (NULL, 5, 2, 'pm_patient_4', '3456789012345678', '0323')");
//
//
//            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 2, 1, '2019-03-12', '2019-03-18', NULL, NULL)");
//            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 3, 1, '2019-03-12', '2019-03-18', NULL, NULL)");
//            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 4, 2, '2019-03-12', '2019-03-18', NULL, NULL)");
//            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 5, 2, '2019-03-12', '2019-03-18', NULL, NULL)");


            statement.executeUpdate("INSERT INTO CheckIn VALUES (2, 7, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (3, 8, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (4, 9, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (5, 10, '2019-03-12', '2019-03-18')");


        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void dropTables() {
        try {

            statement.executeUpdate("DROP TABLE Wards ");
            statement.executeUpdate("DROP TABLE BillingAccounts ");
            statement.executeUpdate("DROP TABLE BillingRecords  ");
            statement.executeUpdate("DROP TABLE PaymentMethods  ");
            statement.executeUpdate("DROP TABLE MedicalRecord  ");
            statement.executeUpdate("DROP TABLE CheckIn  ");
            statement.executeUpdate("DROP TABLE Staff ");
            statement.executeUpdate("DROP TABLE Patients ");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTables() throws SQLException {
//        statement.executeUpdate("CREATE TABLE Patients (PID INT AUTO_INCREMENT, SSN VARCHAR(9), Name VARCHAR(40) NOT NULL, DOB DATE NOT NULL, Gender VARCHAR(1) NOT NULL, " +
//                "Age INT NOT NULL, Status VARCHAR(64) NOT NULL, Address VARCHAR(64), PhoneNum VARCHAR(10) NOT NULL, Primary Key(PID))");

//        statement.executeUpdate("CREATE TABLE Staff (SID INT AUTO_INCREMENT, Name VARCHAR(40) NOT NULL, Age INT NOT NULL, Gender VARCHAR(1) NOT NULL, JobTitle VARCHAR(20) NOT NULL, " +
//                "ProfTitle VARCHAR(20), Department VARCHAR(20), Address VARCHAR(64) NOT NULL, PhoneNum VARCHAR(10) NOT NULL, Type VARCHAR(10), " +
//                "Primary Key(SID, Type))");

//        statement.executeUpdate("CREATE TABLE Wards (WNum INT AUTO_INCREMENT, SID INT, Capacity INT NOT NULL, ChargePerDay FLOAT NOT NULL, " +
//                "Primary Key(WNum), Foreign Key(SID) references Staff(SID))");
//
//        statement.executeUpdate("CREATE TABLE BillingAccounts (PID INT, BillingAddr VARCHAR(64) NOT NULL, CreatedDate DATE NOT NULL, " +
//                "Balance FLOAT NOT NULL DEFAULT 0.0, Primary Key(PID), Foreign Key(PID) references Patients(PID))");
//
//        statement.executeUpdate("CREATE TABLE BillingRecords (RID INT AUTO_INCREMENT, PID INT, RecordType VARCHAR(20) NOT NULL, CreatedDate DATE NOT NULL, " +
//                "Primary Key(RID), Foreign Key(PID) references Patients(PID))");
//
//        statement.executeUpdate("CREATE TABLE PaymentMethods (PMID INT AUTO_INCREMENT, PID INT, SID INT, PMName VARCHAR(20) NOT NULL, " +
//                "CardNum VARCHAR(16) NOT NULL, CardValidDate VARCHAR(4) NOT NULL, Primary Key(PMID, PID, SID), Foreign Key(PID) references Patients(PID), Foreign Key(SID) references Staff(SID))");

//        statement.executeUpdate("CREATE TABLE MedicalRecord (MID INT AUTO_INCREMENT, PID INT, SID INT, StartDate DATE NOT NULL, EndDate DATE NOT NULL, " +
//                "Prescription VARCHAR(256), Diagnosis VARCHAR(256), Primary Key(MID), Foreign Key(PID) references Patients(PID), Foreign Key(SID) references Staff(SID))");

        statement.executeUpdate("CREATE TABLE CheckIn (PID INT, WNum INT, StartDate DATE NOT NULL, " +
                "EndDate DATE NOT NULL, Primary Key(PID, WNum), Foreign Key(PID) references Patients(PID),Foreign Key(WNum) references Wards(WNum))");

    }


    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        String user = "xfang7";
        String password = "200262795";

        connection = DriverManager.getConnection(jdbcURL, user, password);
        statement = connection.createStatement();

//		try {
//			statement.executeUpdate("DROP TABLE Students");
//			statement.executeUpdate("DROP TABLE Schools");
//		} catch (SQLException e) {
//		}
    }


    private static boolean checkAbilityToStudy(String studentName) {
        try {
            result = statement
                    .executeQuery("SELECT (FundingReceived+Income) AS TotalIncome, (TuitonFees+LivingExpenses) AS "
                            + "TotalFees FROM Students, Schools WHERE Students.School = Schools.Name AND Students.Name "
                            + "LIKE '" + studentName + "%'");

            if (result.next()) {
                return (result.getInt("TotalIncome") > result.getInt("TotalFees"));
            }
            throw new RuntimeException(studentName + " cannot be found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void modifyALittleBit1() throws SQLException {
        statement.executeUpdate("UPDATE Students SET Income = 39000 WHERE Name LIKE 'Angela%'");
        statement.executeUpdate("UPDATE Schools SET TuitonFees = 30000 WHERE Name = 'NYU'");
    }

    private static void modifyALittleBit2() throws SQLException {
        statement.executeUpdate("UPDATE Students SET Income = 55000 WHERE Name LIKE 'Angela%'");
        statement.executeUpdate("UPDATE Schools SET TuitonFees = 15000 WHERE Name = 'NYU'");
    }

    private static void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}