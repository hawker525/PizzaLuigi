package be.vdab.servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class VoorkeurPizzaServlet
 */
@WebServlet("/pizzas/voorkeuren.htm")
public class VoorkeurPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final PizzaDAO pizzaDao = new PizzaDAO();
	private static final String VIEW = "/WEB-INF/JSP/voorkeurpizzas.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoorkeurPizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("pizzas", pizzaDao.findAll());
		if(request.getParameterValues("id")!=null) {
			List<Pizza> voorkeurPizzas = Arrays.stream(request.getParameterValues("id"))
					.map(id -> pizzaDao.read(Long.parseLong(id)))
					.collect(Collectors.toList());
			request.setAttribute("voorkeurPizzas", voorkeurPizzas);
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
