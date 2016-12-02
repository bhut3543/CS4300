
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
	private static LogicImpl logic;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinalProjServlet() {
        super();
        dbAccess = new DbAccessImpl();
        logic = new LogicImpl();
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
		logic.deletePost(postId, user, pass);
	}

	private void updatePost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String userId = null;
		
		String postId = request.getParameter("post_id");

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
		String addr = request.getParameter("addr");
		int odometer = Integer.parseInt(request.getParameter("odometer"));
		
		logic.updatePost(postId, price, make, model, year, title, color, driveType, bodyStyle, engine, horsepower, description, odometer, addr);
	}

	private void checkValidUsername(Connection con, HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/plain");
		String user = request.getParameter("username");
		System.out.println(user);
		String valid = logic.checkValidUsername(user);
		PrintWriter pw;
		try {
			pw = response.getWriter();
			pw.write(valid);
			System.out.println("test2");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("test");
			e.printStackTrace();
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
		boolean currentUsersPost = false;
		
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
		
		CarPost post = logic.getClassifiedCarPost(postId, currentUser);
		currentUsersPost = (currentUser == Integer.parseInt(post.getUserId()));
		CarInfo info = logic.getClassifiedCarInfo(postId, currentUser);
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
		id = logic.loginUser(username, password);
		
		if(id != -1) {
			//resultset wasn't empty!
			
			HttpSession session = request.getSession();  
	        session.setAttribute("id",id);
	        
			Map<String, Object> root = new HashMap<>();
			root.put("username", username);
			root.put("id", id);
			
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
		try {
				BufferedOutputStream bos = new BufferedOutputStream( response.getOutputStream( ) );
				bos.write(logic.getImage(postId, picNum));
			}catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
	}

	private void viewPosts(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String searchParam = request.getParameter("search");
		Integer currentId = ((Integer)request.getSession().getAttribute("id"));

		Map<String, Object> root = logic.viewPosts(searchParam, currentId);
		
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
		String addr = request.getParameter("addr");
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
        String sql = "INSERT INTO car_post (id, user_id, title, img1, img2, img3, img4, img5, has_carfax, car_info_id, description, price, addr) values (NULL, ?, ?, ?,?,?,?,?, ?, ?,?,?,?)";
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
			statement.setString(12, addr); //odo
			
			System.out.println(statement.toString());
			int row = statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			response.sendRedirect("http://localhost:8080/4300Project/FinalProjServlet?action=view&search=" + title);
		} catch (IOException e) {
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
