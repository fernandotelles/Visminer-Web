/**
 * 
 */
package org.visminer.web.json;
import java.util.List;
import org.visminer.model.database.File;

/**
 * @author Fernando Telles
 *
 */
public class PackageJSON {
	
	private int idPackage;
	private String namePackage;
	private List<File> files;
	
	public PackageJSON(){
		
	}

	public int getIdPackage() {
		return idPackage;
	}

	public void setIdPackage(int idPackage) {
		this.idPackage = idPackage;
	}

	public String getNamePackage() {
		return namePackage;
	}

	public void setNamePackage(String namePackage) {
		this.namePackage = namePackage;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	
}
