package be.vdab.servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CookieServlet
 */
@WebServlet("/cookies.htm")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW ="/WEB-INF/JSP/cookies.jsp";
	private static final int COOKIE_MAXIMUM_LEEFTIJD = 60 * 30;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie cookie = new Cookie("naam", URLEncoder.encode(request.getParameter("naam"), "UTF-8"));
		cookie.setMaxAge(COOKIE_MAXIMUM_LEEFTIJD);
		response.addCookie(cookie);
		response.sendRedirect(request.getRequestURI());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Optional<Cookie> co = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals("naam")).findFirst();
		if(co.isPresent()) {
			request.setAttribute("naam", co.get().getValue());
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
