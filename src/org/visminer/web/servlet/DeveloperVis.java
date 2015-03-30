package org.visminer.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.visminer.main.Visminer;
import org.visminer.model.business.Committer;

/**
 * Servlet implementation class DeveloperVis
 */
@WebServlet("/DeveloperVis")
public class DeveloperVis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeveloperVis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Visminer vm = (Visminer) session.getAttribute("visminer");
		
		List<Committer> committers = vm.getCommitters();
			
		request.setAttribute("committers", committers);
		//request.setAttribute(arg0, arg1);
		request.getRequestDispatcher("/developer.jsp").forward(request,response);
	}

}
