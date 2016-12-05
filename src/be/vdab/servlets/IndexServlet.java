package be.vdab.servlets;


import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IndexServlet
 */
@WebServlet(urlPatterns = "/index.htm")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/index.jsp";
	private final AtomicInteger aantalKeerBekeken = new AtomicInteger();
	private static final String INDEX_REQUESTS = "indexRequests";

    /**
     * Default constructor. 
     */
    public void init() throws ServletException {
    	ServletContext context = this.getServletContext();
    	context.setAttribute(INDEX_REQUESTS, new AtomicInteger());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uur = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		((AtomicInteger) this.getServletContext().getAttribute(INDEX_REQUESTS)).incrementAndGet();
		
		request.setAttribute("aantalKeerBekeken", aantalKeerBekeken.incrementAndGet());
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
