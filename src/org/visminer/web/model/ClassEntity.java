/**
 * 
 */
package org.visminer.web.model;

import org.visminer.web.model.MethodEntity;
import java.util.List;

/**
 * @author Fernando Telles
 *
 */
public class ClassEntity {
	
	
	private int idClass;
	private String nameClass;
	private List<MethodEntity> methods;
	
	
	public ClassEntity(){
		
	}
	
	public int getIdClass() {
		return idClass;
	}
	public void setIdClass(int idClass) {
		this.idClass = idClass;
	}
	public String getNameClass() {
		return nameClass;
	}
	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}
	public List<MethodEntity> getMethod() {
		return methods;
	}
	public void setMethod(List<MethodEntity> methods) {
		this.methods = methods;
	}
	
}
