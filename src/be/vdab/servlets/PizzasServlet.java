package be.vdab.servlets;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.dao.PizzaDAO;
import be.vdab.entities.Pizza;

/**
 * Servlet implementation class PizzasServlet
 */
@WebServlet("/pizzas.htm")
public class PizzasServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/pizzas.jsp";
	private static final String PIZZAS_REQUESTS = "pizzasRequests";
	private static final PizzaDAO pizzaDAO = new PizzaDAO();

	
	@Override
	public void init() throws ServletException{
		this.getServletContext().setAttribute(PIZZAS_REQUESTS, new AtomicInteger());
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		((AtomicInteger) this.getServletContext().getAttribute(PIZZAS_REQUESTS)).incrementAndGet();
		List<Pizza> pizzas = pizzaDAO.findAll();
		String pizzaFotosPad = this.getServletContext().getRealPath("/pizzafotos");
		Set<Long> pizzaIdsMetFoto = new HashSet<>();
		for (Pizza pizza : pizzas) {
			File file = new File(String.format("%s/%d.jpg", pizzaFotosPad, pizza.getId()));
			if(file.exists()) {
				pizzaIdsMetFoto.add(pizza.getId());
			}
		}
		request.setAttribute("pizzas", pizzaDAO.findAll());
		request.setAttribute("pizzaIdsMetFoto", pizzaIdsMetFoto);
		request.getRequestDispatcher(VIEW).forward(request, response);
	}


}
