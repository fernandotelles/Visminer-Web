package org.visminer.web.main;

import java.net.URL;

import org.visminer.constant.RemoteServiceType;
import org.visminer.constant.RepositoryType;
import org.visminer.main.Visminer;
import org.visminer.model.business.Repository;

public class VisminerWeb {
	
	private Visminer visminer;
	
	private VisminerWeb(){
		
		
		
		visminer = new Visminer();
		
		//get absolute path and replace with wanted file path.
		URL resource = getClass().getResource("/");
		String path = resource.getPath();
		path = path.replace("WEB-INF/classes/", "config.properties");
		
		URL resourceXML= getClass().getResource("/");
		String metricPath = resourceXML.getPath();
		metricPath = metricPath.replace("WEB-INF/classes/", "metrics.xml");
		
		visminer.configure(path);
		visminer.configureMetrics(metricPath);
		
		visminer.getAnalyzer().setRepositoryName("visminer")
		                      .setRepositoryPath("/home/fernandoteles/Visminer/.git")
		                      .setRepositoryRemoteName("visminer")
		                      .setRepositoryRemoteOwner("fernandotelles")
		                      .setRepositoryRemoteType(RemoteServiceType.GITHUB)
		                      .setRepositoryType(RepositoryType.GIT);
		
		Repository v = visminer.analyze();
		
		
	}
	
	private static VisminerWeb singleton = null;
			
	public static VisminerWeb getInstance(){
		if (singleton == null){
			singleton = new VisminerWeb();
		}
		return singleton;
	}
	
	public Visminer getVisminer(){
		return visminer;
	}

}
