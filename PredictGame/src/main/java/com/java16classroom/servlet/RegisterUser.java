package com.java16classroom.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java16classroom.helper.WebPage;
@WebServlet(name = "registerUser" , urlPatterns = WebPage.RESGITER)
public class RegisterUser extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		String name = (String) req.getSession().getAttribute("username");
		PrintWriter writer = resp.getWriter();
		writer.print(" <style>\n"
				+ "        .center {\n"
				+ "         text-align: center; \n"
				+ "         max-width: 100%;\n"
				+ "         margin-top: 20%;\n"
				+ "         }\n"
				+ "         .center form label{\n"
				+ "            \n"
				+ "         }\n"
				+ "     </style>\n"
				+ "    <div class='center'>\n"
				+ "        <h1> <span style=\"color : red\">Number</span> <span style=\"color : black\">Predict</span>  <span style=\"color : green\">Game</span> :)</h1>\n"
				+ "        \n"
				+ "        <form method=\"post\"> \n"
				+ "            <label  for=\"username\">Name :</label>\n"
				+ "            <input type='text'  name=\"username\" id=\"username\" value=\" "+name+ "\"/>\n"
				+ "            <br>\n"
				+ "            <input type=\"submit\"  value=\"Start\">\n"
				+ "        </form>\n"
				+ "      \n"
				+ "    </div>");
		writer.close();
		}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String username = req.getParameter("username");
		
		System.out.println(username);
		if (!username.isEmpty()) {
			req.getSession().setAttribute("username", username);
			resp.sendRedirect(req.getContextPath()+ WebPage.GAME);
		} else {
			req.getSession().setAttribute("username", "");
			doGet(req, resp);
		}	
	}
}
