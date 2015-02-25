/**
 * 
 */
package org.visminer.web.json;
import java.util.List;
import org.visminer.model.database.Repository;

/**
 * @author Fernando Telles
 *
 */
public class ProjectJSON {
	
	private int idProject;
	private String nameProject;
	private List<Repository> repo;
	
	public ProjectJSON(){
		
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public List<Repository> getRepo() {
		return repo;
	}

	public void setRepo(List<Repository> repo) {
		this.repo = repo;
	}
	
	
}