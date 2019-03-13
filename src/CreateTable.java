import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * Acknowledgments: This example is a modification of code provided by Dimitri
 * Rakitine. Further modified by Shrikanth N C for MySql(MariaDB) support.
 * Replace all $USER$ with your unity id and $PASSWORD$ with your 9 digit
 * student id or updated password (if changed)
 * 
 */

public class CreateTable {
	static final String jdbcURL = "jdbc:mariadb://classdb2.csc.ncsu.edu:3306/xfang7";
	// Put your oracle ID and password here

	private static Connection connection = null;
	private static Statement statement = null;
	private static ResultSet result = null;

	public static void main(String[] args) {
		initialize();

		close();
	}

	private static void initialize() {
		try {
			connectToDatabase();

			statement.executeUpdate("CREATE TABLE Patients (PID INT, SSN VARCHAR(9), Name VARCHAR(40) NOT NULL, DOB VARCHAR(8) NOT NULL, Gender VARCHAR(1) NOT NULL, " +
					"Age INT NOT NULL, Status VARCHAR(64) NOT NULL, Address VARCHAR(64), PhoneNum VARCHAR(10) NOT NULL, Primary Key(PID))");
			statement.executeUpdate("CREATE TABLE Wards (WNum INT, SID INT, Capacity INT BOT NULL, ChargePerDay FLOAT NOT NULL, " +
					"Primary Key(WNum), Foreign Key(SID) references Staff(SID))");
			statement.executeUpdate("CREATE TABLE Staff (SID INT, Name VARCHAR(40) NOT NULL, Age INT NOT NULL, Gender VARCHAR(1) NOT NULL, JobTitle VARCHAR(20) NOT NULL, " +
					"ProfTitle VARCHAR(20), Department VARCHAR(20), Address VARCHAR(64) NOT NULL, PhoneNum VARCHAR(10) NOT NULL, Type VARCHAR(10), " +
					"Primary Key(SID, Type))");
			statement.executeUpdate("CREATE TABLE BillingAccounts (PID INT, BillingAddr VARCHAR(64) NOT NULL, CreatedDate VARCHAR(8) NOT NULL, " +
					"Balance FLOAT NOT NULL DEFAULT 0.0, Primary Key(PID), Foreign Key(PID) refrences Patients(PID))");
			statement.executeUpdate("CREATE TABLE BillingRecords (RID INT, PID INT, RecordType VARCHAR(20) NOT NULL, CreatedDate VARCHAR(8) NOT NULL, " +
					"Primary Key(RID), Foreign Key(PID) references Patients(PID))");
			statement.executeUpdate("CREATE TABLE PaymentMethods (PMID INT, PID INT, SID INT, PMName VARCHAR(20) NOT NULL, " +
					"CardNum VARCHAR(16) NOT NULL, CardValidDate VARCHAR(4) NOT NULL), Primary Key(PMID, PID, SID), Foreign Key(PID) references Patients(PID), Foreign Key(SID) references Staff(SID))");
			statement.executeUpdate("CREATE TABLE MedicalRecord (MID INT, PID INT, SID INT, StartDate VARCHAR(8) NOT NULL, EndDate VARCHAR(8) NOT NULL, " +
					"Prescription VARCHAR(256), Diagnosis VARCHAR(256)), Primary Key(MID, PID, SID), Foreign Key(PID) references Patients(PID), Foreign Key(SID) references Staff(SID)");
			statement.executeUpdate("CREATE TABLE CheckIn (PID INT, WNum VARCHAR(20) NOT NULL, StartDate VARCHAR(8) NOT NULL, " +
					"EndDate VARCHAR(8) NOT NULL, BedNum VARCHAR(20) NOT NULL, Primary Key(PID, WNum), Foreign Key(WNum) references Patients(PID))");

			statement.executeUpdate("CREATE TABLE TreatedBy (PID INT, SID INT, Primary Key(PID, SID))");

					//+ "School VARCHAR(10), Age INTEGER, FundingReceived INTEGER, Income INTEGER, Sex CHAR(1))");
/*
			statement.executeUpdate("INSERT INTO Students VALUES ('Todd', 'NC State'," + " 18, 16000, 30000, 'M')");
			statement.executeUpdate("INSERT INTO Students VALUES ('Max', 'Stanford'," + " 21, 20000, 70000, 'M')");
			statement.executeUpdate("INSERT INTO Students VALUES ('Alex', 'UNC'," + " 19, 8000, 40000, 'M')");
			statement.executeUpdate("INSERT INTO Students VALUES ('Natasha', 'Harvard'," + " 22, 15000, 75000, 'F')");
			statement.executeUpdate("INSERT INTO Students VALUES ('Kelly', 'UCLA'," + " 23, 2000, 50000, 'F')");
			statement.executeUpdate("INSERT INTO Students VALUES ('Angela', 'NYU'," + "18, 8000, 45000, 'F')");

			statement.executeUpdate("CREATE TABLE Schools (Name VARCHAR(10), Location VARCHAR(30), "
					+ "TuitonFees INTEGER, LivingExpenses INTEGER)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('NC State', 'North Carolina', 24000, 20000)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('Stanford', 'California', 44000, 35000)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('UNC', 'North Carolina', 34000, 20000)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('Harvard', 'Massachusetts', 50000, 38000)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('UCLA', 'California', 36000, 30000)");
			statement.executeUpdate("INSERT INTO Schools VALUES ('NYU', 'New York', 22000, 41000)");
*/
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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