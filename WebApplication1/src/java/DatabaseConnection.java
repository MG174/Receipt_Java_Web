import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class can be used to initialize the database connection
public class DatabaseConnection {
	protected static Connection initializeDatabase()
		throws SQLException, ClassNotFoundException
	{
		// Initialize all the information regarding
		// Database Connection


            
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbURL + dbName,
													dbUsername,
													dbPassword);
		return con;
	}
}
