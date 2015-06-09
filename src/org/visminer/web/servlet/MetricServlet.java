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
import org.visminer.model.business.File;
import org.visminer.model.business.Metric;
import org.visminer.model.business.Repository;
import org.visminer.web.model.MetricValue;
import org.visminer.web.model.Graphic;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/MetricServlet")
public class MetricServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** 
	 * @see HttpServlet#HttpServlet()
	 */
	public MetricServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Visminer vm = (Visminer)session.getAttribute("visminer");
		
		Repository repo = (Repository) vm.getAnalyzer().analyzeRepository();
		repo.start();
		
		String metricChosen = request.getParameter("m");
		if(metricChosen == null){
			metricChosen = "LOC";
		}
		String chartChosen = request.getParameter("c");
		if(chartChosen == null){
			chartChosen = "bubbleChart";
		}
		String relatedtoChosen = request.getParameter("r");
		if(relatedtoChosen == null){
			relatedtoChosen = "file";
		}

		//getting the chosen metric
		
		MetricValue mv = new MetricValue();
		List<Metric> metrics = mv.getMetricValue(metricChosen,vm,repo);

		//Get json with datas
		Graphic g = new Graphic("chart.json");
		String values = g.generateChart(metrics ,relatedtoChosen);
		//Setting the selected chart 
		request.setAttribute("selectedChart",chartChosen);	
		request.setAttribute("relatedto",relatedtoChosen);	
		request.setAttribute("values",values);	
		request.setAttribute("greater",mv.getGreater()+"");
		request.setAttribute("metrics",vm.getMetrics());
		request.setAttribute("metricName",mv.getMetric().getName());
		request.setAttribute("metricDescription",mv.getMetric().getDescription());	
		request.getRequestDispatcher("/metric.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
