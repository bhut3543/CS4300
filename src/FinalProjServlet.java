
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.sql.Blob;
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
		case "view":
			viewPosts(con, request, response);
			break;
		case "getImg":
			getImage(con, request, response);
			break;
		case "classified":
			getClassified(con, request, response);
			break;
		default:
			break;		
		}
	}

	private void loginUser(Connection con, HttpServletRequest request, HttpServletResponse response) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(username + " " + password);
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
		String query = "select img1 from car_post where id=" + postId;
		System.out.println(query);
		ResultSet rs = dbAccess.retrieve(con, query);
		if(rs!=null) {
			try {
				if(rs.next()) {
					Blob blob = rs.getBlob("img1");
					BufferedOutputStream bos = new BufferedOutputStream( response.getOutputStream( ) );
					bos.write(blob.getBytes(1, (int)blob.length()));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	private void viewPosts(Connection con, HttpServletRequest request, HttpServletResponse response) {
		ArrayList<CarPost> posts = new ArrayList<>();
		String query = "select * from car_post";
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
					CarPost post = new CarPost(id, userId, year, make, model, title);
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

	private void uploadPost(Connection con, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		int userId = ((Integer)session.getAttribute("id"));
		String make = request.getParameter("make");
		String model = request.getParameter("model");
		String year = request.getParameter("year");
		String title = request.getParameter("title");

		InputStream inputStream = null; // input stream of the upload file
        try {
			Part filePart = request.getPart("pic1");
			if (filePart != null) {
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
			statement.setInt(1, userId);
			statement.setInt(2, Integer.parseInt(year));
			statement.setString(3, make);
			statement.setString(4, model);
			statement.setString(5, title);
			if (inputStream != null) {
                // fetches input stream of the upload file for the blob column
                statement.setBlob(6, inputStream);
            }
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
