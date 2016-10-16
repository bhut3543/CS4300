
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.mysql.jdbc.ResultSetMetaData;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
@MultipartConfig(maxFileSize = 16177215)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static DbAccessImpl dbAccess;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		case "upload":
			uploadPost(con, request, response);
			break;
		case "view":
			viewPosts(con, request, response);
			break;
		case "getImg":
			getImage(con, request, response);
		default:
			break;
				
		}
	}

	private void getImage(Connection con, HttpServletRequest request, HttpServletResponse response) {
		//http://stackoverflow.com/questions/38353711/retrieve-image-blob-from-mysql-using-servlet-and-display-it-at-jsp
		response.setContentType( "image/jpg" );
		String postId = request.getParameter("postId");
		String query = "select img1 from car_post where id=" + postId;
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs!=null) {
			try {
				while(rs.next()) {
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void viewPosts(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String query = "select * from car_post";
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs != null) {
			try {
				while(rs.next()) {
					String make = rs.getString("make");
					String model = rs.getString("model");
					System.out.println(make + " " + model);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void uploadPost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Upload!");
		String make = request.getParameter("make");
		String model = request.getParameter("model");

		InputStream inputStream = null; // input stream of the upload file
        try {
			Part filePart = request.getPart("pic1");
			if (filePart != null) {
	            // prints out some information for debugging
	            System.out.println(filePart.getName());
	            System.out.println(filePart.getSize());
	            System.out.println(filePart.getContentType());
	             
	            // obtains input stream of the upload file
	            inputStream = filePart.getInputStream();
	        }
		} catch (IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String sql = "INSERT INTO car_post (id, user_id, year, make, model, title, img1) values (NULL, ?, ?, ?, ?, ?, ?)";
        try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, 1);
			statement.setInt(2, 2005);
			statement.setString(3, make);
			statement.setString(4, model);
			statement.setString(5, "Test Upload");
			if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(6, inputStream);
            }
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
