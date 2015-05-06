package org.visminer.web.main;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.apache.commons.configuration.ConfigurationException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.visminer.constant.RemoteServiceType;
import org.visminer.constant.RepositoryType;
import org.visminer.constant.RemoteServiceType;
import org.visminer.constant.RepositoryType;
//import org.visminer.main.VisMiner;
import org.visminer.main.Visminer;
import org.visminer.web.model.Configuration;

public class Viz {
	
	//Remote repository properties
	private String REMOTE_REPOSITORY_GIT;
    private String REMOTE_REPOSITORY_USER;
    private String REMOTE_REPOSITORY_PASSWORD;
    
	//Local repository properties
	private String LOCAL_REPOSITORY_PATH;
    private String LOCAL_REPOSITORY_NAME;
    private String LOCAL_REPOSITORY_OWNER;
    
    //Persistence properties using in database connection
    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String JDBC_URL = "jdbc:mysql://localhost/visminer3";
	private String JDBC_USER;
	private String JDBC_PASSWORD;
	private String JDBC_GENERATION = "drop-and-create-tables";
	private String JDBC_LOGGING = "off"; 
	
    private boolean createTableFlag; //flag to indicate whether to create tables or not.
    
    //instance of VisMiner API
    //protected VisMiner visminer;
    
    /**
	 * 
	 * @throws IOException
	 * @throws GitAPIException
	 */
    public Viz(){
    	this.createTableFlag = false;
    }
    
    /**
	 * 
	 * @param createTableFlag flag to indicate whether to create tables or not.
	 * @throws IOException
	 * @throws GitAPIException
	 */
    public Viz(Configuration cfg){
    	REMOTE_REPOSITORY_GIT	= cfg.getRemoteRepositoryGit();
    	REMOTE_REPOSITORY_USER	= cfg.getRemoteRepositoryLogin();
    	REMOTE_REPOSITORY_PASSWORD = cfg.getRemoteRepositoryPassword();
    	LOCAL_REPOSITORY_PATH 	= cfg.getLocalRepositoryPath();
        LOCAL_REPOSITORY_NAME 	= cfg.getLocalRepositoryName();
        LOCAL_REPOSITORY_OWNER  = cfg.getLocalRepositoryOwner();
    	JDBC_USER				= cfg.getJdbc_user();
    	JDBC_PASSWORD			= cfg.getJdbc_password();
    	createTableFlag 		= cfg.isCreateTableFlag();
	}
	
	/****************************************************************
	 *                    SETS AND GETS METHODS
	 ***************************************************************/
	
    /**
     * 
     * @return a variable of type VisMiner.
     * @throws ConfigurationException 
     */
	public Visminer getVisminer() throws IOException, GitAPIException{		
		
		/*
		Map<String, String> props = new HashMap<String, String>();
		props.put(VisMiner.JDBC_DRIVER, JDBC_DRIVER);
    	props.put(VisMiner.JDBC_URL, JDBC_URL);
    	props.put(VisMiner.JDBC_USER, JDBC_USER);
    	props.put(VisMiner.JDBC_PASSWORD, JDBC_PASSWORD); 
		props.put(VisMiner.DB_NAME, "visminer");
    	//IF is new repository SET flag for create tables
    	if(this.createTableFlag){
    		props.put(VisMiner.DDL_GENERATION, "create-tables");
		}

    	Map<Integer, String> local_cfg = new HashMap<Integer, String>();
		local_cfg.put(VisMiner.LOCAL_REPOSITORY_PATH ,LOCAL_REPOSITORY_PATH );
		local_cfg.put(VisMiner.LOCAL_REPOSITORY_NAME ,LOCAL_REPOSITORY_NAME );
		local_cfg.put(VisMiner.LOCAL_REPOSITORY_OWNER,LOCAL_REPOSITORY_OWNER);

		Map<Integer, Object> remote_cfg = new HashMap<Integer, Object>();
		remote_cfg.put(VisMiner.REMOTE_REPOSITORY_LOGIN,REMOTE_REPOSITORY_USER);
		remote_cfg.put(VisMiner.REMOTE_REPOSITORY_PASSWORD, REMOTE_REPOSITORY_PASSWORD);
		remote_cfg.put(VisMiner.REMOTE_REPOSITORY_GIT,new GitHub());
		*/
		
		URL resource = getClass().getResource("/");
		String configPath = resource.getPath();
		configPath = configPath.replace("WEB-INF/classes/", "config.properties");
		
		
		URL resourceXML= getClass().getResource("/");
		String metricPath = resourceXML.getPath();
		metricPath = metricPath.replace("WEB-INF/classes/", "metrics.xml");
		
		//Set database configuration properties
		Configuration dbConfig = new Configuration();
		
		
		try {
			//resource = dbConfig.updateConfig(this.JDBC_DRIVER, this.JDBC_URL, this.JDBC_USER, this.JDBC_PASSWORD, this.JDBC_GENERATION, this.JDBC_LOGGING);
			
			//Visminer Class Instantiation
			Visminer visminer = new Visminer();
			visminer.configure(configPath);
			visminer.configureMetrics(metricPath);
			
			visminer.getAnalyzer().setRepositoryName(this.LOCAL_REPOSITORY_NAME)
			                      .setRepositoryPath(this.LOCAL_REPOSITORY_PATH)
			                      .setRepositoryRemoteName(this.LOCAL_REPOSITORY_NAME)
			                      .setRepositoryRemoteOwner(this.REMOTE_REPOSITORY_USER)
			                      .setRepositoryRemoteType(RemoteServiceType.GITHUB)
			                      .setRepositoryType(RepositoryType.GIT);
			
			return visminer;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String getREMOTE_REPOSITORY_GIT() {
		return REMOTE_REPOSITORY_GIT;
	}

	public String getREMOTE_REPOSITORY_USER() {
		return REMOTE_REPOSITORY_USER;
	}

	public String getREMOTE_REPOSITORY_PASSWORD() {
		return REMOTE_REPOSITORY_PASSWORD;
	}

	/**
	 * 
	 * @return The local repository path
	 */
	public String getLocalRepositoryPath() {
		return LOCAL_REPOSITORY_PATH;
	}

	/**
	 * 
	 * @return The local repository name
	 */
	public String getLocalRepositoryName() {
		return LOCAL_REPOSITORY_NAME;
	}

	/**
	 * 
	 * @return The local repository owner
	 */
	public String getLocalRepositoryOwner() {
		return LOCAL_REPOSITORY_OWNER;
	}

	@Override
	public String toString() {
		return "Viz [REMOTE_REPOSITORY_GIT=" + REMOTE_REPOSITORY_GIT
				+ ", REMOTE_REPOSITORY_USER=" + REMOTE_REPOSITORY_USER
				+ ", REMOTE_REPOSITORY_PASSWORD=" + REMOTE_REPOSITORY_PASSWORD
				+ ", LOCAL_REPOSITORY_PATH=" + LOCAL_REPOSITORY_PATH
				+ ", LOCAL_REPOSITORY_NAME=" + LOCAL_REPOSITORY_NAME
				+ ", LOCAL_REPOSITORY_OWNER=" + LOCAL_REPOSITORY_OWNER
				+ ", JDBC_DRIVER=" + JDBC_DRIVER + ", JDBC_URL=" + JDBC_URL
				+ ", JDBC_USER=" + JDBC_USER + ", JDBC_PASSWORD="
				+ JDBC_PASSWORD + ", createTableFlag=" + createTableFlag + "]";
				//+ ", visminer=" + visminer + "]";
	}
	
}