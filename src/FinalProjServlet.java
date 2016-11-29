
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.jdbc.Statement;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/FinalProjServlet")
@MultipartConfig(maxFileSize = 16177215)
public class FinalProjServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DbAccessImpl dbAccess;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalProjServlet() {
        super();
        dbAccess = new DbAccessImpl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//connect to db
		Connection con = dbAccess.connect();
		String action = request.getParameter("action");
		switch(action) {
		case "login":
			loginUser(con, request, response);
			break;
		case "register":
			loginUser(con, request, response);
			break;
		case "upload":
			uploadPost(con, request, response);
			break;
		case "update":
			updatePost(con, request, response);
			break;
		case "view":
			viewPosts(con, request, response);
			break;
		case "getImg":
			getImage(con, request, response);
			break;
		case "classified":
			getClassified(con, request, response);
			break;
		case "regval":
			checkValidUsername(con, request, response);
		case "delete":
			deletePost(con, request, response);
		default:
			break;		
		}
	}

	private void deletePost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String postId = request.getParameter("post_id");
		String user = request.getParameter("user"), pass = request.getParameter("pass");
		ResultSet tempSet = dbAccess.retrieve(con, "select car_info_id from car_post where id=" + postId);
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
		
		//you have postId and infoId and userId
		//need to validate user before doing any kind of delete
		ResultSet userSet = dbAccess.retrieve(con, "select * from user where id=" + userId);
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
		if(validated) {
			String pDeleteQ = "DELETE from car_post where id=" + postId;
			String iDeleteQ = "DELETE from car_info where id=" + infoId;
			dbAccess.delete(con, pDeleteQ);
			dbAccess.delete(con, iDeleteQ);
		}
		
	}

	private void updatePost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String userId = null;
		
		String postId = request.getParameter("post_id");
		ResultSet tempSet = dbAccess.retrieve(con, "select car_info_id from car_post where id=" + postId);
		int infoId = 0;
		try {
			tempSet.next();
			infoId = tempSet.getInt("car_info_id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String price = request.getParameter("price");
		String vin = request.getParameter("vin");
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String title = request.getParameter("title");
		String color = request.getParameter("color").toUpperCase();
		String driveType = request.getParameter("drive_type").toUpperCase();
		String bodyStyle = request.getParameter("body_style").toUpperCase();
		String engine = request.getParameter("engine").toUpperCase();
		int horsepower = Integer.parseInt(request.getParameter("hp"));
		String description = request.getParameter("description");
		System.out.println(description);
		int odometer = Integer.parseInt(request.getParameter("odometer"));
		
		String infoQuery = String.format("UPDATE car_info SET year='%s', make='%s', model='%s', color='%s', body_style='%s', engine='%s', power_hp='%s', odometer='%s' WHERE id=%d", 
				year, make, model, color, bodyStyle, engine, horsepower, odometer, infoId);
		String postQuery = String.format("UPDATE car_post SET title='%s', price='%s', description='%s' WHERE id=%s", title, price, description, postId);

		dbAccess.update(con, infoQuery);
		dbAccess.update(con, postQuery);
	}

	private void checkValidUsername(Connection con, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		String user = request.getParameter("username");
		System.out.println(user);
		String query = String.format("select id from user where user='%s'", user);
		
		try {
			PrintWriter pw = response.getWriter();

			ResultSet rs = dbAccess.retrieve(con, query);
			if(rs == null) {
				pw.write("true");
			} else {
				pw.write("false");
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	private void getClassified(Connection con, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Hello test");
		HttpSession session = request.getSession();
		
		Integer currentUser = ((Integer)session.getAttribute("id"));
		if(currentUser == null) {
			currentUser = -1;
		}
		System.out.println("Current user is: " + currentUser);
		
		int postId = Integer.parseInt(request.getParameter("postId"));
		String query = String.format("select * from car_post c JOIN car_info i where c.id=%d AND c.car_info_id=i.id", postId);
		System.out.println(query);
		String id = ""+postId,  userId = null, year = null, make = null, model = null, title = null;
		String carInfoId = null;
		int price = 0;
		String description = null, postTime = null;
		boolean hasCarfax = false;
		boolean currentUsersPost = false;
		
		String color = null, bodyStyle = null, engine = null, driveType = null;
		int odometer = 0, horsepower = 0;
		String vin = null;
		
		
		//get car_post info
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs != null) {
			try {
				while(rs.next()) {
					carInfoId = rs.getString("car_info_id");
					userId = rs.getString("user_id");
					year = rs.getString("year");
					make = rs.getString("make");
					model = rs.getString("model");
					title = rs.getString("title");
					price = rs.getInt("price");
					vin = rs.getString("vin");
					description = rs.getString("description");
					postTime = rs.getString("post_time");
					hasCarfax = rs.getBoolean("has_carfax");

					color = rs.getString("color");
					bodyStyle = rs.getString("body_style");
					engine = rs.getString("engine");
					driveType = rs.getString("drive_type");
					odometer = rs.getInt("odometer");
					horsepower = rs.getInt("power_hp");

					currentUsersPost = (currentUser == Integer.parseInt(userId));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		//get specific car_info
//		query = String.format("select * from car_info where id=%s", postId);
//		rs = dbAccess.retrieve(con, query);
//		if(rs != null) {
//			try {
//				while(rs.next()) {
////					carInfoId = rs.getString("car_info_id");
////					userId = rs.getString("user_id");
//					year = rs.getString("year");
//					make = rs.getString("make");
//					model = rs.getString("model");
////					title = rs.getString("title");
////					price = rs.getInt("price");
////					description = rs.getString("description");
////					postTime = rs.getString("post_time");
////					hasCarfax = rs.getBoolean("has_carfax");
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		CarPost post = new CarPost(id, userId, title, price, description, postTime, hasCarfax);
//		CarInfo info = new CarInfo(color, bodyStyle, engine, driveType, odometer, horsepower);
		
		CarPost post = new CarPost(id, userId, title, price, description, postTime, hasCarfax);
		CarInfo info = new CarInfo(vin, color, bodyStyle, engine, driveType, odometer, horsepower, year, make, model);
		Map<String, Object> root = new HashMap<>();
		root.put("post", post);
		root.put("info", info);
		root.put("currentUsersPost", currentUsersPost);
		
		//freemarker setup
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		try {					
			String path = getServletContext().getRealPath("/WEB-INF/templates/");
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			cfg.setLogTemplateExceptions(false);		
		} catch (IOException e) {
				e.printStackTrace();
		}
		Template temp;
		try {
			temp = cfg.getTemplate("classifiedview.ftlh");
			Writer out = new OutputStreamWriter(response.getOutputStream());
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	private void loginUser(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
	
		int id = -1;
		
		String query = String.format("select id from user where user=\"%s\" AND pass=\"%s\" limit 1", username, password);
		ResultSet rs = dbAccess.retrieve(con, query);
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
		
		if(id != -1) {
			//resultset wasn't empty!
			
			HttpSession session = request.getSession();  
	        session.setAttribute("id",id);
	        
			Map<String, Object> root = new HashMap<>();
			root.put("username", username);
			
			//freemarker setup
			Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
			try {					
				String path = getServletContext().getRealPath("/WEB-INF/templates/");
				cfg.setDirectoryForTemplateLoading(new File(path));
				cfg.setDefaultEncoding("UTF-8");
				cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
				cfg.setLogTemplateExceptions(false);		
			} catch (IOException e) {
					e.printStackTrace();
			}
			Template temp;
			try {
				temp = cfg.getTemplate("mainpage.ftlh");
				Writer out = new OutputStreamWriter(response.getOutputStream());
				temp.process(root, out);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
		}
		
	}

	private void getImage(Connection con, HttpServletRequest request, HttpServletResponse response) {
		//http://stackoverflow.com/questions/38353711/retrieve-image-blob-from-mysql-using-servlet-and-display-it-at-jsp
		response.setContentType( "image/jpg" );
		String postId = request.getParameter("postId");
		int picNum = Integer.parseInt(request.getParameter("pic"));
		if(picNum < 1 || picNum > 5) {
			picNum = 1;
		}
		String imgStr = "img" + picNum;
		String query = "select " + imgStr + " from car_post where id=" + postId;
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs!=null) {
			try {
				if(rs.next()) {
					Blob blob = rs.getBlob(imgStr);
					BufferedOutputStream bos = new BufferedOutputStream( response.getOutputStream( ) );
					bos.write(blob.getBytes(1, (int)blob.length()));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
		
	}

	private void viewPosts(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String searchParam = request.getParameter("search");
		String query = "";
		if(searchParam == null) {
			query = "select make,model,year,title,c.id,user_id from car_post c JOIN car_info i where c.car_info_id=i.id";
		} else {
			query = String.format("select make,model,year,title,c.id,user_id from car_post c JOIN car_info i where c.car_info_id=i.id AND (i.make='%s' OR i.model='%s' OR i.year='%s')", searchParam, searchParam, searchParam);
		}
		
		//;
		
		ArrayList<ClassifiedObj> posts = new ArrayList<>();
		
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs != null) {
			try {
				while(rs.next()) {
					String make = rs.getString("make");
					String model = rs.getString("model");
					String year = rs.getString("year");
					String title = rs.getString("title");
					String id = rs.getString("id");
					String userId = rs.getString("user_id");
					ClassifiedObj post = new ClassifiedObj(id, userId, year, make, model, title);
//					CarPost post = new CarPost(null, userId, userId, title, 0, null, null, false);
					posts.add(post);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//freemarker setup
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_25);
		try {					
			String path = getServletContext().getRealPath("/WEB-INF/templates/");
			cfg.setDirectoryForTemplateLoading(new File(path));
			cfg.setDefaultEncoding("UTF-8");
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
			cfg.setLogTemplateExceptions(false);		
		} catch (IOException e) {
				e.printStackTrace();
		}
		Map<String, Object> root = new HashMap<>();
		root.put("posts", posts);
		Template temp;
		try {
			temp = cfg.getTemplate("view.ftlh");
			Writer out = new OutputStreamWriter(response.getOutputStream());
			temp.process(root, out);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	/*
	 * INCORRECT USE OF "Statement.executeUpdate()"
	 * SHOULD GET LAST ID FROM *NEW* FUNCTION IN DbAccessImpl
	 */
	private void uploadPost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
//		if(session == null) {
//			return;
//		}
		int userId = ((Integer)session.getAttribute("id"));
//		int userId = Integer.parseInt(request.getParameter("postman_id")); //POSTMAN USE ONLY
		String vin = request.getParameter("vin").toUpperCase();
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String title = request.getParameter("title");
		String color = request.getParameter("color").toUpperCase();
		String driveType = request.getParameter("drive_type").toUpperCase();
		String bodyStyle = request.getParameter("body_style").toUpperCase();
		String engine = request.getParameter("engine").toUpperCase();
		int horsepower = Integer.parseInt(request.getParameter("hp"));
		String description = request.getParameter("description");
		int odometer = Integer.parseInt(request.getParameter("odometer"));
		boolean hasCarfax = Boolean.parseBoolean(request.getParameter("has_carfax"));
		int price = Integer.parseInt(request.getParameter("price"));
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
		
		
//        try {
//			Part filePart = request.getPart("pic1");
//			if (filePart != null) {
//	            // obtains input stream of the upload file
//	            inputStream = filePart.getInputStream();
//	        }
//		} catch (IOException | ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        String sql = "INSERT INTO car_post (id, user_id, title, img1, img2, img3, img4, img5, has_carfax, car_info_id, description, price) values (NULL, ?, ?, ?,?,?,?,?, ?, ?,?,?)";
        try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, userId);
			statement.setString(2, title);
			
			int count = 3;
			while(count < 8) {

				InputStream inputStream = null; // input stream of the upload file
				try {
					Part filePart = request.getPart("pic" + (count -2));
					if (filePart != null) {
			            // obtains input stream of the upload file
			            inputStream = filePart.getInputStream();
			        }
				} catch (IOException | ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				statement.setBlob(count, inputStream);
				count++;
//				if (inputStream != null) {
//	                // fetches input stream of the upload file for the blob column
//	                
//	            } else {
//	            	
//	            }
			}
			statement.setBoolean(8, hasCarfax);
			statement.setInt(9, carInfoId);
			statement.setString(10, description); //odo
			statement.setInt(11, price); //odo
			
			System.out.println(statement.toString());
			int row = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
