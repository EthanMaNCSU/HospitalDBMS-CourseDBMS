import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/xfang7";
    // Put your oracle ID and password here

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet result = null;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        connectToDatabase();
      //  dropTables();

      //  createTables();

      //  insertData();

        close();

    }

    /**
     * Maintaining medical records for each patient: Enter/update a new medical record for each treatment, test, and check-in.
     * 'treatment','test', 'check-in', 'accommodation'
     */
    public static void maintainMedicalRecord(){
        try {
            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 2, 1, '2019-03-15', '2019-03-18', NULL, NULL)");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    /**
     * temp: just for assignment 4.1
     */
    public static void infoProcess(){
        processStaff();
        processPatient();
        processWard();
        assignWard();
    }

    /**
     * temp: just for assignment 4.1
     */
    public static void processStaff() {
        try {
            //add
            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Tom Smith', 35, 'F', " +
                    "'Nurse', 'Nursing Supervisor', 'Primary Care', '3761 Giovanni St., Raleigh, NC, USA, 27606', '9197891411', 'nurse')");
            //update
            statement.executeUpdate("UPDATE Staff SET Age = 39 WHERE Name = 'Tom Smith'");

            // Delete
            statement.executeUpdate("DELETE from Staff WHERE Name like 'Tom %'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * temp: just for assignment 4.1
     */
    public static void processPatient() {
        try {
            //add
            statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '333456789', 'Alice Sparrow', " +
                    "'1998-01-01', 'F', 22, 'in ward', '123 The Black Pearl, Raleigh, NC, USA, 27606', '9991234567')");

            //update
            statement.executeUpdate("UPDATE Patients SET PhoneNum = '8881234567' WHERE Name like '% Sparrow' and Age = 22");

            // Delete
            statement.executeUpdate("DELETE from Patients WHERE  PhoneNum = '8881234567'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * temp: just for assignment 4.1
     */
    public static void processWard() {
        try {
            //add
            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 2, 20.0,'N')");

            //update
            statement.executeUpdate("UPDATE Wards SET Capacity = '1' and ChargePerDay = '10.0' WHERE WNum = 5");

            // Delete
            statement.executeUpdate("DELETE from Wards WHERE WNum = 5");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * temp: just for assignment 4.1
     */
    public static boolean  assignWard(){

        try {
            // query  available wards
            ResultSet availabe = statement
                    .executeQuery("SELECT * FROM Wards WHERE Occupied = 'N'");
            //query bed = 1 , 2, 4
            ResultSet result1 = statement
                    .executeQuery("SELECT * FROM Wards WHERE Capacity = 1");

            ResultSet result2 = statement
                    .executeQuery("SELECT * FROM Wards WHERE Capacity = 2");

            ResultSet result4 = statement
                    .executeQuery("SELECT * FROM Wards WHERE Capacity = 4");

            // assign to patient# 1
            ResultSet assign1 = statement
                    .executeQuery("SELECT @wId := WNum FROM Wards WHERE Capacity = 1 and Occupied = 'N' limit 1");
            if (assign1.next()) {
                statement.executeUpdate("INSERT INTO CheckIn VALUES (1, @wId, '2019-03-12', '2019-03-18')");
                statement.executeUpdate("UPDATE Wards SET Occupied = 'Y' WHERE WNum = @wId");
            }

            // assign to patient# 2
            ResultSet assign2 = statement
                    .executeQuery("SELECT @wId := WNum FROM Wards WHERE Capacity = 2 and Occupied = 'N' limit 1");
            if (assign2.next()) {
                statement.executeUpdate("INSERT INTO CheckIn VALUES (2, @wId, '2019-03-17', '2019-03-18')");
                statement.executeUpdate("UPDATE Wards SET Occupied = 'Y' WHERE WNum = @wId");
            }
            // assign to patient# 4
            //SELECT @wId :=WNum FROM Wards WHERE Capacity = 4 and Occupied = 'N' limit 1;
            ResultSet assign4 = statement
                    .executeQuery("SELECT @wId := WNum FROM Wards WHERE Capacity = 4 and Occupied = 'N' limit 1");
            if (assign4.next()) {
                //int wId= assign4.getInt("WNum");
                statement.executeUpdate("INSERT INTO CheckIn VALUES (3,@wId, '2019-03-17', '2019-03-18')");
                statement.executeUpdate("UPDATE Wards SET Occupied = 'Y' WHERE WNum = @wId");
            }

            //Release a ward

//            ResultSet release = statement
//                    .executeQuery("SELECT * FROM CheckIn WHERE PID = 3");
//            int wId  = release.getInt("WNum");
//            statement.executeUpdate("DELETE from CheckIn WHERE PID = 3");
//            statement.executeUpdate("UPDATE Wards SET Occupied = 'N' WHERE WNum = $[wId]");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }



    private static void insertData() {
        try {
            statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '123456789', 'Jack Sparrow', " +
					"'1900-01-01', 'M', 119, 'in ward', '0001 The Black Pearl, Raleigh, NC, USA, 27606', '9191234567')");
			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '234567891', 'Tyrion Lannister', " +
					"'1900-01-01', 'M', 119, 'completing treatment', '0002 Red Keep, Raleigh, NC, USA, 27606', '9192345678')");
			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '345678912', 'Jamie Lannister', " +
					"'1897-01-01', 'F', 122, 'processing treatment plan', '0001 Red Keep, Raleigh, NC, USA, 27606', '9193456789')");
			statement.executeUpdate("INSERT INTO Patients VALUES (NULL, '456789123', 'Cho Akiyama', " +
					"'1905-01-01', 'M', 114, 'in ward', '0003 Duima St., Raleigh, NC, USA, 27606', '9194567891')");


			statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Jared Diamond', 119, 'M', " +
					"'doctor', 'MD', 'Primary Care', '0001 GSG St., Raleigh, NC, USA, 27606', '9195678912', 'doctor')");
            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Megumi Kato', 22, 'F', " +
                    "'doctor', 'MD', 'Primary Care', '0001 Aki St., Raleigh, NC, USA, 27606', '9196789123', 'doctor')");
            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Ezio Auditore', 35, 'M', " +
                    "'nurse', 'nurse', 'Primary Care', '0001 Giovanni St., Raleigh, NC, USA, 27606', '9197891234', 'nurse')");
            statement.executeUpdate("INSERT INTO Staff VALUES (NULL, 'Tomoya Aki', 22, 'M', " +
                    "'billing staff', NULL, 'Primary Care', '0002 Aki St., Raleigh, NC, USA, 27606', '9198912345', 'operator')");


            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 2, 20.0,'N')");
            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 1, 10.0,'N')");
            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 4, 40.0,'N')");
            statement.executeUpdate("INSERT INTO Wards VALUES (NULL, 3, 1, 50.0,'N')");


            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (1, '0001 The Black Pearl, Raleigh, NC, USA, 27606', " +
                    "'2019-03-12', 0.0)");
            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (2, '0002 Red Keep, Raleigh, NC, USA, 27606', " +
                    "'2019-03-12', 0.0)");
            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (3, '0001 Red Keep, Raleigh, NC, USA, 27606', " +
                    "'2019-03-12', 0.0)");
            statement.executeUpdate("INSERT INTO BillingAccounts VALUES (4, '0003 Duima St., Raleigh, NC, USA, 27606', " +
                    "'2019-03-12', 0.0)");


            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 1, 1, '2019-03-12')");
            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 2, 2, '2019-03-12')");
            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 3, 3, '2019-03-12')");
            statement.executeUpdate("INSERT INTO BillingRecords VALUES (NULL, 4, 4, '2019-03-12')");


            statement.executeUpdate("INSERT INTO PaymentMethods VALUES ('0123456789012345', 1, 'debit',  '0420')");
            statement.executeUpdate("INSERT INTO PaymentMethods VALUES ('1234567890123456', 2, 'debit',  '0420')");
            statement.executeUpdate("INSERT INTO PaymentMethods VALUES ('2345678901234567', 3, 'credit', '0323')");
            statement.executeUpdate("INSERT INTO PaymentMethods VALUES ('3456789012345678', 4, 'credit', '0323')");


            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 2, 1,'check-in' '2019-03-12', '2019-03-18', NULL, NULL)");
            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 3, 1,'treatment' '2019-03-12', '2019-03-18', NULL, NULL)");
            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 4, 2, 'test','2019-03-12', '2019-03-18', NULL, NULL)");
            statement.executeUpdate("INSERT INTO MedicalRecord VALUES (NULL, 1, 2, 'treatment','2019-03-12', '2019-03-18', NULL, NULL)");


            statement.executeUpdate("INSERT INTO CheckIn VALUES (2, 1, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (3, 2, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (4, 3, '2019-03-12', '2019-03-18')");
            statement.executeUpdate("INSERT INTO CheckIn VALUES (1, 4, '2019-03-12', '2019-03-18')");


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
        statement.executeUpdate("CREATE TABLE Patients (PID INT AUTO_INCREMENT, SSN VARCHAR(9), Name VARCHAR(40) NOT NULL, DoB DATE NOT NULL, Gender CHAR(1) check (Gender IN ('F','M')), " +
                "Age INT NOT NULL, Status CHAR(64) NOT NULL, Address VARCHAR(64), PhoneNum VARCHAR(10) NOT NULL, Primary Key(PID))");

        statement.executeUpdate("CREATE TABLE Staff (SID INT AUTO_INCREMENT, Name VARCHAR(40) NOT NULL, Age INT NOT NULL, Gender VARCHAR(1) check (Gender IN ('F','M')), JobTitle VARCHAR(20) NOT NULL, " +
                "ProfTitle VARCHAR(20), Department VARCHAR(20), Address VARCHAR(64) NOT NULL, PhoneNum VARCHAR(10) NOT NULL, Type VARCHAR(10) check (Type IN ('Doctor','Nurse','Operator','Others')), " +
                "Primary Key(SID))");

        statement.executeUpdate("CREATE TABLE Wards (WNum INT AUTO_INCREMENT, SID INT, Capacity INT NOT NULL, ChargePerDay FLOAT NOT NULL,Occupied CHAR(1) DEFAULT 'N' check (Occupied IN ('Y','N')), " +
                "Primary Key(WNum), Foreign Key(SID) references Staff(SID))");

        statement.executeUpdate("CREATE TABLE BillingAccounts (PID INT, BillingAddr VARCHAR(64) NOT NULL, CreatedDate DATE NOT NULL, " +
                "Balance FLOAT NOT NULL DEFAULT 0.0, Primary Key(PID), Foreign Key(PID) references Patients(PID))");

        statement.executeUpdate("CREATE TABLE BillingRecords (RID INT AUTO_INCREMENT, PID INT, MID INT, CreatedDate DATE NOT NULL, " +
                "Primary Key(RID), Foreign Key(PID) references Patients(PID),Foreign Key(MID) references MedicalRecord(MID))");

        statement.executeUpdate("CREATE TABLE PaymentMethods (CardNum VARCHAR(16), PID INT,  Type VARCHAR(20) check (Type IN ('debit','credit')), " +
                "CardValidDate VARCHAR(4) NOT NULL, Primary Key(CardNum), Foreign Key(PID) references Patients(PID))");

        statement.executeUpdate("CREATE TABLE MedicalRecord (MID INT AUTO_INCREMENT, PID INT, SID INT, RecordType VARCHAR(20) check (RecordType in ('treatment','test', 'check-in', 'accommodation')),StartDate DATE NOT NULL, EndDate DATE NOT NULL, " +
                "Prescription VARCHAR(256), Diagnosis VARCHAR(256), Primary Key(MID), Foreign Key(PID) references Patients(PID), Foreign Key(SID) references Staff(SID))");

        statement.executeUpdate("CREATE TABLE CheckIn (PID INT, WNum INT, StartDate DATE NOT NULL, " +
                "EndDate DATE NOT NULL, Primary Key(PID, WNum), Foreign Key(PID) references Patients(PID),Foreign Key(WNum) references Wards(WNum))");

    }


    private static void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        String user = "xfang7";
        String password = "200262795";

        connection = DriverManager.getConnection(jdbcURL, user, password);
        statement = connection.createStatement();

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