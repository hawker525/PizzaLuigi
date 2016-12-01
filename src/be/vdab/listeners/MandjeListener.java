package be.vdab.listeners;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class MandjeListener
 *
 */
@WebListener
public class MandjeListener implements ServletContextListener, HttpSessionAttributeListener {

	private static final String MANDJE = "mandje";
	private static final String AANTAL_MANDJES = "aantalMandjes";
	
    /**
     * Default constructor. 
     */
    public MandjeListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	if(arg0.getName().equals(MANDJE)) ((AtomicInteger) arg0.getSession().getServletContext().getAttribute(AANTAL_MANDJES)).getAndIncrement();
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	if(arg0.getName().equals(MANDJE)) ((AtomicInteger) arg0.getSession().getServletContext().getAttribute(AANTAL_MANDJES)).getAndDecrement();
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
    	arg0.getServletContext().setAttribute(AANTAL_MANDJES, new AtomicInteger(0));
    }
	
}
