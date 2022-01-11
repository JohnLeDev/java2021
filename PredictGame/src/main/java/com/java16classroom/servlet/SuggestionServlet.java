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
@WebServlet(name = "suggestionServlet", urlPatterns = WebPage.SUGGEST)
public class SuggestionServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		List<User> usersScore =(List<User>) req.getSession().getAttribute(WebPage.USERSSCORE);
		System.out.println(listHtmlScore(usersScore));
		PrintWriter writer = resp.getWriter();
		writer.print("<style>\n"
				+ "        .center {\n"
				+ "            text-align: center;\n"
				+ "            max-width: 100%;\n"
				+ "            margin-top: 20%;\n"
				+ "        }\n"
				+ "\n"
				+ "        .table {\n"
				+ "            text-align: -webkit-center;\n"
				+ "\n"
				+ "        }\n"
				+ "    </style>\n"
				+ "    <div class='center'>\n"
				+ "        <h1> <span style=\"color : red\">Number</span> <span style=\"color : black\">Predict</span> <span\n"
				+ "                style=\"color : green\">Game</span> :)</h1>\n"
				+ "        <h1>Congration</h1>\n"
				+ "\n"
				+ "<h1>best player"+ bestPlayer(usersScore) +"</h1>\n"
				+ "        <form method=\"post\">\n"
				+ "            <input type=\"submit\" value=\"Play again\">\n"
				+ "        </form>\n"
				+ "        <div class=\"table\">\n"
				+ "            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"325\">\n"
				+ "                <tr>\n"
				+ "                    <td>\n"
				+ "                        <table cellspacing=\"0\" cellpadding=\"1\" border=\"1\" width=\"300\">\n"
				+ "                            <tr style=\"color:white;background-color:grey\">\n"
				+ "                                <th>Name</th>\n"
				+ "                                <th>Score</th>\n"
				+ "                            </tr>\n"
				+ "                        </table>\n"
				+ "                    </td>\n"
				+ "                </tr>\n"
				+ "                <tr>\n"
				+ "                    <td>\n"
				+ "                        <div style=\"width:320px; height:200px; overflow:auto;\">\n"
				+ "                            <table cellspacing=\"0\" cellpadding=\"1\" border=\"1\" width=\"300\">\n"
				+ listHtmlScore(usersScore)
				+ "                            </table>\n"
				+ "                        </div>\n"
				+ "                    </td>\n"
				+ "                </tr>\n"
				+ "            </table>\n"
				+ "        </div>\n"
				+ "    </div>>");
		writer.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().setAttribute(WebPage.YOURCHOICE, "-1");
		resp.sendRedirect(req.getContextPath()+ WebPage.RESGITER);
	}
	
	private String listHtmlScore(List<User> usersScore ) {
		String result ="";
		if(usersScore==null) {
			return "";
		}
		for(User user : usersScore) {
			String htmlresult = "                                <tr>\n"
					+ "                                    <td>"+ user.getName() +"</td>\n"
					+ "                                    <td>"+user.getScore()+"</td>\n"
					+ "                                </tr>\n";
			result= result+htmlresult;
		}
		return  result;
	}
	private String bestPlayer(List<User> usersScore) {
		if(usersScore==null) {
			return "";
		}
		int max =0;
		int maxindex=-1;
		int index =0;
		for(User user : usersScore) {
			if(max < user.getScore()) {
				max= user.getScore();
				maxindex = index;
			}
			index++;
		}
		return usersScore.get(maxindex).getName();
	}
}
