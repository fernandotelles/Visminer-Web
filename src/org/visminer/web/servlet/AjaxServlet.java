package org.visminer.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.visminer.main.Visminer;
import org.visminer.model.business.Commit;
import org.visminer.model.business.Committer;
import org.visminer.model.business.File;
import org.visminer.model.business.Repository;

/**
 * Servlet implementation class AjaxServlet
 */
@WebServlet("/AjaxServlet")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxServlet() {
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
		
		List<File> files = new ArrayList<File>();
		String result="";
		Repository repo = (Repository) vm.getAnalyzer().analyzeRepository();
		repo.start();
		
		Committer committer = new Committer();
		for(Committer c: repo.getCommitters()){
			if(c.getName().equals((String)request.getParameter("user")))
				committer = c;
		}
		
		for(Commit c: repo.getCommitsByCommitter(committer)){
			for(File f: c.getFiles()){
				if(f.getSoftwareEntities().size() == 0)continue;
				files.add(f);
			}
		}
		
		response.setContentType("text/plain");
		
		for(File f: files){
			
			String file[] = f.getPath().split("/");
			
			result += file[file.length-1].concat(",");

		}
		result = result.substring(0, result.length()-1);
		
		response.getWriter().write(result);
	}

}
