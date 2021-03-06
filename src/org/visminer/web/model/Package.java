package org.visminer.web.model;

import java.util.List;

import org.visminer.model.business.File;

public class Package {
	private String name;
	private Package parent;
	private List<File> files;
	
	public Package() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Package getParent() {
		return parent;
	}

	public void setParent(Package parent) {
		this.parent = parent;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
}
