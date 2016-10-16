

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DbAccessImpl 
 * Allows to communicate with the database
 * @author jason
 *
 */
public class DbAccessImpl extends DbAccessConfiguration implements DbAccessInterface {

	@Override
	public Connection connect() {
		Connection connection = null;
		try {
			Class.forName(DRIVE_NAME);
			connection = DriverManager.getConnection(CONNECTION_URL, DB_CONNECTION_USERNAME, DB_CONNECTION_PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	@Override
	/**
	 * ResultSet retrieve
	 * Allows user to do basic query search on the Database
	 */
	public ResultSet retrieve(Connection con, String query) {
		ResultSet rset = null;
		try {
			Statement stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			return rset;
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
			return rset;
		}
	}
	@Override
	/**
	 * Creates something on the DB
	 */
	public int create(Connection con, String query) {
		return update(con, query);
	}

	@Override
	/**
	 * Update something on the DB based on ID
	 */
	public int update(Connection con, String query) {
		int result = 0;
		try {
			Statement stmt = con.prepareStatement(query);
			result = stmt.executeUpdate(query);
			return result;
		} catch (SQLException e) {
			System.err.println("SQL Error: " + e.getMessage());
		}
		return result;
	}

	@Override
	/**
	 * Delete something on the DB based on ID
	 */
	public int delete(Connection con, String query) {
		return update(con, query);
	}

	@Override
	/**
	 * Disconnect from the database
	 */
	public void disconnect(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	

}
