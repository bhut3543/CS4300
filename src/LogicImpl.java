import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class LogicImpl{
	
	private CarPersistImpl carPersist = new CarPersistImpl();

	public void deletePost(String postId, String user, String pass){
		ResultSet tempSet = carPersist.returnClassified(postId);
		int infoId = 0;
		int userId = 0;
		try {
			tempSet.next();
			infoId = tempSet.getInt("car_info_id");
			userId = tempSet.getInt("user_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet userSet = carPersist.validate(userId);
		boolean validated = false;
		try {
			userSet.next();
			boolean userOk = user.equals(userSet.getString("user"));
			boolean passOk = user.equals(userSet.getString("pass"));
			validated = (userOk && passOk);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(validated){
			carPersist.deletePost(postId, infoId);
		}
	}
	
	public void updatePost(String postId, String price, String make, String model, 
			String year, String title, String color, String driveType, String bodyStyle, 
			String engine, int horsepower, String description, int odometer, String addr){
		ResultSet tempSet = carPersist.returnClassified(postId);
		int infoId = 0;
		try {
			tempSet.next();
			infoId = tempSet.getInt("car_info_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		carPersist.updatePost(infoId, postId, price, make, model, year, title, color, driveType, bodyStyle, engine, horsepower, description, odometer, addr);
	}
	
	public String checkValidUsername(String user){
		String valid = "";
		ResultSet rs = carPersist.checkValidUsername(user);
		if(rs == null) {
			valid = "true";
		} else {
			valid = "false";
		}
		return valid;
	}
	
	public CarPost getClassifiedCarPost(int postId, Integer currentUser){
		ResultSet rs = carPersist.getClassified(postId);
		if(currentUser == null) {
			currentUser = -1;
		}
		System.out.println("Current user is: " + currentUser);
		
		String query = String.format("select * from car_post c JOIN car_info i where c.id=%d AND c.car_info_id=i.id", postId);
		System.out.println(query);
		String id = ""+postId,  userId = null, title = null;
		int price = 0;
		String description = null, postTime = null, addr = null;
		boolean hasCarfax = false;
		if(rs != null) {
			try {
				while(rs.next()) {
					userId = rs.getString("user_id");
					title = rs.getString("title");
					price = rs.getInt("price");
					description = rs.getString("description");
					postTime = rs.getString("post_time");
					hasCarfax = rs.getBoolean("has_carfax");
					addr = rs.getString("addr");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CarPost post = new CarPost(id, userId, title, price, description, postTime, hasCarfax, addr);
		return post;
	}
	public CarInfo getClassifiedCarInfo(int postId, Integer currentUser){
		ResultSet rs = carPersist.getClassified(postId);
		if(currentUser == null) {
			currentUser = -1;
		}
		System.out.println("Current user is: " + currentUser);
		
		String query = String.format("select * from car_post c JOIN car_info i where c.id=%d AND c.car_info_id=i.id", postId);
		System.out.println(query);
		String year = null, make = null, model = null;
		
		String color = null, bodyStyle = null, engine = null, driveType = null;
		int odometer = 0, horsepower = 0;
		String vin = null;
		if(rs != null) {
			try {
				while(rs.next()) {
					year = rs.getString("year");
					make = rs.getString("make");
					model = rs.getString("model");
					vin = rs.getString("vin");

					color = rs.getString("color");
					bodyStyle = rs.getString("body_style");
					engine = rs.getString("engine");
					driveType = rs.getString("drive_type");
					odometer = rs.getInt("odometer");
					horsepower = rs.getInt("power_hp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		CarInfo info = new CarInfo(vin, color, bodyStyle, engine, driveType, odometer, horsepower, year, make, model);
		return info;
	}
	
	public int loginUser(String username, String password){
		ResultSet rs = carPersist.checkValidUsername(username);
		int id = -1;
		if(rs != null) {
			try {
				while(rs.next()) {
					id = rs.getInt("id");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	
	public byte [] getImage(String postId, int picNum){
		String imgStr = "img" + picNum;
		ResultSet rs = carPersist.getImage(imgStr, postId);
		byte [] byteArray = null;
		if(rs!=null) {
			try {
				if(rs.next()) {
					Blob blob = rs.getBlob(imgStr);
					byteArray= blob.getBytes(1, (int)blob.length());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		return byteArray;
	}
	
	public HashMap<String, Object> viewPosts(String searchParam, int currentUserId){
		ResultSet rs = carPersist.viewPosts(searchParam);
		ArrayList<ClassifiedObj> posts = new ArrayList<>();
		
		if(rs != null) {
			try {
				while(rs.next()) {
					String make = rs.getString("make");
					String model = rs.getString("model");
					String year = rs.getString("year");
					String title = rs.getString("title");
					String id = rs.getString("id");
					String userId = rs.getString("user_id");
					String color = rs.getString("color");
					String driveType = rs.getString("drive_type");
					String bodyStyle = rs.getString("body_style");
					String odometer = rs.getString("odometer");
					String addr = rs.getString("addr");
					ClassifiedObj post = new ClassifiedObj(id, userId, year, make, model, title, color, driveType, bodyStyle, odometer, addr);
//					CarPost post = new CarPost(null, userId, userId, title, 0, null, null, false);
					posts.add(post);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		HashMap<String, Object> root = new HashMap<>();
		root.put("currentId", currentUserId);
		root.put("posts", posts);
		
		return root;
	}
	
	public void uploadPost(int userId, String title, boolean hasCarfax, int carInfoId, String description, 
			int price, String vin, String color, String bodyStyle, 
			String driveType, String year, String make, String model, 
			String engine, int horsepower, int odometer, String addr){
		carPersist.uploadInfo(userId, vin, color, bodyStyle, driveType, year, make, model, engine, horsepower, odometer);
		carPersist.uploadPost(userId, title, hasCarfax, carInfoId, description, price, addr);
	}
	
}