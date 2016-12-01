package be.vdab.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.PizzaDAO;

/**
 * Servlet implementation class PizzaDetailServlet
 */
@WebServlet("/pizzas/detail.htm")
public class PizzaDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzadetail.jsp";
	private final PizzaDAO pizzaDAO = new PizzaDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PizzaDetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("pizza", pizzaDAO.read(Long.parseLong(request.getParameter("id"))));
		} catch (NumberFormatException ex){ // request param bevat geen getal
			request.setAttribute("fout", "Nummer niet correct");
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
