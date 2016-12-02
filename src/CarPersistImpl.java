import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class CarPersistImpl{
	
	private static DbAccessImpl dbAccess = new DbAccessImpl();
	
	public ResultSet returnClassified(String postId){
		Connection con = dbAccess.connect();
		String query = ("select car_info_id from car_post where id=" + postId);
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public ResultSet validate(int userId){
		Connection con = dbAccess.connect();
		ResultSet userSet = dbAccess.retrieve(con, "select * from user where id=" + userId);
		return userSet;
	}
	
	public void deletePost(String postId, int infoId){
		Connection con = dbAccess.connect();
		String pDeleteQ = "DELETE from car_post where id=" + postId;
		String iDeleteQ = "DELETE from car_info where id=" + infoId;
		dbAccess.delete(con, pDeleteQ);
		dbAccess.delete(con, iDeleteQ);
	}
	
	public void updatePost(int infoId, String postId, String price, String make, String model, 
			String year, String title, String color, String driveType, String bodyStyle, 
			String engine, int horsepower, String description, int odometer, String addr){
		
		Connection con = dbAccess.connect();
		String infoQuery = String.format("UPDATE car_info SET year='%s', make='%s', model='%s', color='%s', body_style='%s', engine='%s', power_hp='%s', odometer='%s' WHERE id=%d", 
				year, make, model, color, bodyStyle, engine, horsepower, odometer, infoId);
		String postQuery = String.format("UPDATE car_post SET title='%s', price='%s', description='%s', addr='%s' WHERE id=%s", title, price, description, addr, postId);

		dbAccess.update(con, infoQuery);
		dbAccess.update(con, postQuery);
	}
	
	public ResultSet checkValidUsername(String user){
		Connection con = dbAccess.connect();
		String query = String.format("select id from user where user='%s'", user);
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public ResultSet getClassified(int postId){
		Connection con = dbAccess.connect();
		String query = String.format("select * from car_post c JOIN car_info i where c.id=%d AND c.car_info_id=i.id", postId);
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public ResultSet loginUser(String username, String password){
		Connection con = dbAccess.connect();
		String query = String.format("select id from user where user=\"%s\" AND pass=\"%s\" limit 1", username, password);
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public ResultSet getImage(String imgStr, String postId){
		
		Connection con = dbAccess.connect();
		String query = "select " + imgStr + " from car_post where id=" + postId;
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public ResultSet viewPosts(String searchParam){
		Connection con = dbAccess.connect();
		String query = "";
		if(searchParam == null) {
			query = "select make,model,year,title,c.id,user_id,color,drive_type,body_style,odometer,addr from car_post c JOIN car_info i where c.car_info_id=i.id";
		} else {
			query = String.format("select make,model,year,title,c.id,user_id,color,drive_type,body_style,odometer,addr from car_post c JOIN car_info i where c.car_info_id=i.id AND (i.make='%s' OR i.model='%s' OR i.year='%s')", searchParam, searchParam, searchParam);
		}
		ResultSet rs = dbAccess.retrieve(con, query);
		return rs;
	}
	
	public void uploadInfo(int userId, String vin, String color, String bodyStyle, 
			String driveType, String year, String make, String model, 
			String engine, int horsepower, int odometer){
		Connection con = dbAccess.connect();
		String carInfoSql = "INSERT INTO car_info (id, vin, color, body_style, drive_type, year, make, model, engine, power_hp, odometer) " 
				+ "values (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int carInfoId = -1;
		try {
			System.out.println("info id is " + carInfoId);
			PreparedStatement statement = con.prepareStatement(carInfoSql, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, vin); //vin
			statement.setString(2, color); //color
			statement.setString(3, bodyStyle); //body style
			statement.setString(4, driveType); //drive type
			statement.setInt(5, Integer.parseInt(year)); //year
			statement.setString(6, make); //make
			statement.setString(7, model); //model
			statement.setString(8, engine); //engine
			statement.setInt(9, horsepower); //hp
			statement.setInt(10, odometer); //odo
			
			//BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD 
			statement.executeUpdate(); 
			ResultSet rs = statement.getGeneratedKeys();
		    rs.next();
			carInfoId = rs.getInt(1);
			System.out.println("info id is " + carInfoId);
			//BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD BAD
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadPost(int userId, String title, boolean hasCarfax, 
			int carInfoId, String description, int price, String addr){
		Connection con = dbAccess.connect();
		String sql = "INSERT INTO car_post (id, user_id, title, img1, img2, img3, img4, img5, has_carfax, car_info_id, description, price, addr) values (NULL, ?, ?, ?,?,?,?,?, ?, ?,?,?,?)";
        try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setString(2, title);
			
			int count = 3;
		
			statement.setBoolean(8, hasCarfax);
			statement.setInt(9, carInfoId);
			statement.setString(10, description); //odo
			statement.setInt(11, price); //odo
			statement.setString(12, addr); //odo
		
			System.out.println(statement.toString());
			int row = statement.executeUpdate();
		
        } catch (SQLException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }
	}
}