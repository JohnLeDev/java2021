package com.java16classroom.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java16classroom.helper.WebPage;
import com.java16classroom.model.GameRecord;
import com.java16classroom.model.User;

@WebServlet(name ="gameServlet" ,urlPatterns = WebPage.GAME)
public class HomeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int result = randomNumber();
	int yourChoice = -1;
	User user = new User();
	GameRecord usersScore = new GameRecord();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String name = (String) req.getSession().getAttribute(WebPage.USERNAME);
		if(user.getName().isEmpty()) {
			user.setName(name);
		}
		String compare = yourChoice!=-1?(result>yourChoice?WebPage.SMALLSTATUS:WebPage.GREATSTATUS ):"";
		if(compare.equals(WebPage.SMALLSTATUS) || compare.equals(WebPage.GREATSTATUS)) {
			user.setScore();
		}
		System.out.println(user.getScore() + " "+ user.getName());
		System.out.println(compare);
		PrintWriter writer = resp.getWriter();
		writer.print(" <style>\n"
				+ "        .center {\n"
				+ "            text-align: center;\n"
				+ "            max-width: 100%;\n"
				+ "            margin-top: 20%;\n"
				+ "        }\n"
				+ "\n"
				+ "        .blur-text {\n"
				+ "            filter: blur(0px)\n"
				+ "        }\n"
				+ "        .float{\n"
				+ "            text-align: center;\n"
				+ "            display: flex;\n"
				+ "            justify-content: center;\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "    <div class='center'>\n"
				+ "        <h1> <span style=\"color : red\">Number</span> <span style=\"color : black\">Predict</span> <span\n"
				+ "                style=\"color : green\">Game</span> :)</h1>\n"
				+ "        <h1> Hello " + name + ", Your choice</h1>\n"
				+ "			<h6>"+ compare + "</h6>"
				+ "        <form method=\"post\">\n"
				+ "\n"
				+ "            <input type='text' name=\"yourChoice\" id=\"yourChoice\" />\n"
				+ "            <br>\n"
				+ "            <input type=\"submit\"  value=\"Play\">\n"
				+ "        </form>\n"
				+ "        <br>\n"
				+ "        <div >\n"
				+ "            <div class=\"float\">\n"
				+ "                <h3>Random number : </h3>\n"
				+ "                <h2 class=\"blur-text\">"+ result +"</h2>\n"
				+ "            </div>\n"
				+ "        </div>\n"
				+ "    </div>");
		writer.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		try {
			yourChoice = (int) Integer.parseInt(req.getParameter(WebPage.YOURCHOICE));
		}catch(Exception e) {
			e.printStackTrace();
		}
		

		if( yourChoice == result) {
			
				usersScore.getUsers().add(user);
			
		
			List<User> lisda= usersScore.getUsers();
			
			req.getSession().setAttribute(WebPage.USERSSCORE, lisda);
			yourChoice=-1;
			result = randomNumber();
			user = new User();
			resp.sendRedirect(req.getContextPath()+ WebPage.SUGGEST);
		}else {
			doGet(req,resp);
		}
		
	}
	
	private int randomNumber() {
		int max = 1000;
		int min = 0;
		return (int)Math.floor(Math.random()*(max-min+1)+min);
		 
	}
}
