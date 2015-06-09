package org.visminer.web.servlet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;

import org.visminer.main.Visminer;
import org.visminer.model.business.Commit;
import org.visminer.model.business.Committer;
import org.visminer.model.business.Repository;

import org.visminer.web.javascript.ExportToJavascript;

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
		
		Repository repo = (Repository) vm.getAnalyzer().analyzeRepository();
		repo.start();
		
		List<Committer> committers = repo.getCommitters();
		//get absolute path and replace with wanted file path.
		URL resource = getClass().getResource("/");
		String path = resource.getPath();
		path = path.replace("WEB-INF/classes/", "flare10.json");
		
		populateJson(path,committers,vm);
		
		request.setAttribute("committers", committers);
		request.getRequestDispatcher("/developer.jsp").forward(request,response);
	}

	private void populateJson(String path,List<Committer> committers,Visminer visminer) {
		//montar o arquivo flare.json
			//Get Committers and for each committer, get their commits and the count of commits
			/*String textJson = "{"
			 		+ "\"name\": \"flare\","+
			 		"\"children\": ["+
			 
			 		"{ "+
			 			 "\"name\": \"analytics\"," +
			 			"\"children\": [ "+
				    "{"+
				     	"\"name\": \"cluster\","+
				     	"\"children\": [" +
						      "{\"name\": \"Renato1\", \"value\": 3938},"+
						      "{\"name\": \"Fernando2\", \"value\": 3812},"+
						      "{\"name\": \""+ committers.get(0).getName() +"\", \"value\": 6714},"+
						      "{\"name\": \"Johnny\", \"value\": 743}"+
						"]"+
				     "},"+
				    "{"+
				     "\"name\": \"optimization\","+
				     "\"children\": ["+
				      "{\"name\": \"Fernando5\", \"value\": 1000}"+
				     "]"+
				    "}"+
				   "]"+
				  "}"+
				 "]"+
				"}";*/
			 	
		
			String textJson = "[";
			int size = 1;
			for(Committer c: committers){
				int i = 0;
				
				for(Commit cm : visminer.getCommitsByCommitter(c)){
					 i++;
				}
				
				textJson += i;
				if(size < committers.size())
					textJson += ", ";
				
				size++;
			}
			textJson += "]";
			
			System.out.println(size);
			System.out.println(committers.size());
			/*for(Committer c: committers){
				textJson += "\"name\": \"" + c.getName()+"\"";
				for(Commit cm : visminer.getCommitsByCommitter(c)){
					if(cm.equals(null)){
						textJson += ",";
						continue;
					}
					
					textJson += "\"children\":[";

				}
			}*/
			
			
			// atualizar flare.json com essa string. 
			
			try {
				File file = new File(path);
				file.delete();
				FileWriter f = new FileWriter(file);
				f.write(textJson);
				f.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		
	}

}
