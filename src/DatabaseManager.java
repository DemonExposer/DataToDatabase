import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

/**
 * Manages Connection to the database
 */
public class DatabaseManager {
	/**
	 * url of the mysql database
	 */
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	/**
	 * Connection to the database
	 */
	private Connection connection;
	/**
	 * database username
	 */
	private final String DB_USER;
	/**
	 * database password
	 */
	private final String DB_PASS;

	/**
	 * main method for making a databasemanager
	 * @param args would be login information
	 */
	public static void main(String[] args) {
		try {
			new DatabaseManager();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructor for databaseManager , calls initDatabase()
	 *
	 * @throws SQLException for sql error
	 */
	public DatabaseManager() throws SQLException, IOException {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Put your MySql username here: ");
		DB_USER = scanner.nextLine();
		System.out.print("Put your MySql password here: ");
		DB_PASS = scanner.nextLine();
		initDatabase();
	}

	/**
	 * initializes database connection
	 * @throws SQLException for sql error
	 */
	private void initDatabase() throws SQLException, IOException {
		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);

		File sql = new File("properties.sql");

		String[] statements = FileInteraction.read(sql).split("\n\n");

		int counter = 0;
		String[] s = statements[0].split(";");
		for(String statement : s) {
			counter++;
			connection.prepareStatement(statement).execute();
		}

		statements = statements[1].split("\n");

		for(String statement : statements) {
			if(++counter%100 == 0)
				System.out.printf("%.3f%c%n", counter * 100 / ((double) statements.length), '%');
			connection.prepareStatement(statement).execute();
		}
		close();
	}

	/**
	 * closes connection to the database
	 * @throws SQLException sql error
	 */
	public void close() throws SQLException {
		connection.close();
	}
}
