/**
 * 
 */
package org.visminer.web.json;

import org.visminer.model.database.Commit;
import org.visminer.model.database.Committer;
import org.visminer.web.model.ClassEntity;

import java.util.List;

/**
 * @author Fernando Telles
 *
 */
public class FileJSON {
	
	private int idFile;
	private String nameFile;
	private String typeFile;
	private List<ClassEntity> classes;
	private List<Commit> commits;
	private List<Committer> committers;
	
	
	public FileJSON(){
		
	}
	
	public int getIdFile() {
		return idFile;
	}


	public void setIdFile(int idFile) {
		this.idFile = idFile;
	}


	public String getNameFile() {
		return nameFile;
	}


	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}


	public String getTypeFile() {
		return typeFile;
	}


	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}


	public List<ClassEntity> getClasses() {
		return classes;
	}


	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}


	public List<Commit> getCommits() {
		return commits;
	}


	public void setCommits(List<Commit> commits) {
		this.commits = commits;
	}


	public List<Committer> getCommitters() {
		return committers;
	}


	public void setCommitters(List<Committer> committers) {
		this.committers = committers;
	}

	
}
